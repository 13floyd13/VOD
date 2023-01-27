/**
 * 
 */
package com.forrest.cinema.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.forrest.cinema.controller.RestTMDBController;
import com.forrest.cinema.entities.Film;
import com.forrest.cinema.entities.Genre;
import com.forrest.cinema.entities.tmdb.CastTMDB;
import com.forrest.cinema.entities.tmdb.CreditsTMDB;
import com.forrest.cinema.entities.tmdb.CrewTMDB;
import com.forrest.cinema.entities.tmdb.MovieTMDB;
import com.forrest.cinema.entities.tmdb.ProductionCompagnyTMDB;
import com.forrest.cinema.entities.tmdb.ProductionCountryTMDB;
import com.forrest.cinema.exceptions.ApiTMDBException;
import com.forrest.cinema.repos.FileRepository;
import com.forrest.cinema.repos.FilmRepository;
import com.forrest.cinema.utils.CinemaUtilities;

import reactor.core.publisher.Mono;

/**
 * @author martin
 *
 */
@Service
public class FilmServiceImpl implements FilmService {

	private static final Logger Logger = LoggerFactory.getLogger(FilmServiceImpl.class);
	
	@Autowired
	FilmRepository filmRepository;
	
	@Autowired
	GenreService genreService;
	
	@Autowired
	FileRepository fileRepository;
	
	@Autowired
	private RestTMDBController restTMDBController;
	
	@Value("${film-repository.path}")
	private String filmRepoPath;
	
	@Value("${imdb.url}")
	private String imdbURL;

	@Override
	public Film saveFilm(Film f) {
		return filmRepository.save(f);
	}

	@Override
	public Film updateFilm(Film f) {
		return filmRepository.save(f);
	}

	@Override
	public void deleteFilm(Film f) {
		filmRepository.delete(f);
	}

	@Override
	public void deleteFilmById(Long id) {
		filmRepository.deleteById(id);
	}

	@Override
	public Film getFilm(Long id) {
		return filmRepository.findById(id).get();
	}

	@Override
	public List<Film> getAllFilms() {
		return filmRepository.findAll();
	}

	@Override
	public List<String> getAllTitlesFilm() {
		return filmRepository.findAllFilmsTitle();
	}
	
	@Override
	public List<Film> getAllFilmsByGenre(String genre) {
		return filmRepository.findAllFilmsByGenre(genre);
	}

	@Override
	public List<Film> saveAllFilms(List<Film> films) {
		return filmRepository.saveAll(films);
	}
	
	@Override
	public List<Film> fileToFilm(List<File> filesFilms) {
		
		List<Film> films = new ArrayList<>();
		
		for (File file : filesFilms) {
			Film film = new Film();
			film.setTitleFilm(CinemaUtilities.removeExtension(CinemaUtilities.getStringLimited(file.getName(), 255)));
			film.setPath(CinemaUtilities.getStringLimited(file.getAbsolutePath(), 500));
			film.setSize(CinemaUtilities.convertSizeFileToGigabytes(file));
			films.add(film);
		}		
		return films;		
	}
	
	@Override
	public List<Film> saveAllNewFilms() {
		
		List<String> actualTitles = this.getAllTitlesFilm();
		List<File> newFiles = fileRepository.getNewFilesInDirectory(filmRepoPath, actualTitles);
		List<Film> newFilms = this.fileToFilm(newFiles);
		//this.getIdImdb(newFilms);
		//this.getTMDBInfos(newFilms);
		//return this.saveAllFilms(newFilms);	
		return newFilms;
	}
	
	@Override
	public List<Film> getIdImdb(List<Film> films) {
		
		List<Film> updatedFilm = new ArrayList<>();
		
		for (Film film : films) {

			String titleFilm = film.getTitleFilm();
			
			//Connection  to imdb webSite with URL find + titleFilm
			Connection connection = Jsoup.connect(imdbURL + titleFilm);
			
			// need userAgent for avoid error 403
			connection.userAgent("Mozilla/5.0");
			
			try {
				Document doc = connection.get();
				
				// get link in html page where find titleFilm and imdb id
				Elements docElements = doc.select(".ipc-metadata-list-summary-item__tc a[href]");
				
				// take the first link in search page
				Element headline = docElements.first();
				if (headline != null) {
					String officialTitleFilm = headline.text();
					String url = headline.absUrl("href");
					String idImdb = this.getIdImdbInURL(url);
					
					// for detect if we get wrong film
					int distance = CinemaUtilities.getLevenshteinDistance(titleFilm, officialTitleFilm);
	
					film.setOfficialTitleFilm(officialTitleFilm);
					film.setIdImdb(idImdb);
					film.setDistanceTitles(distance);
					
					updatedFilm.add(film);
				} else {
					film.setOfficialTitleFilm("ERROR");
					film.setDistanceTitles(999);
					updatedFilm.add(film);
					Logger.warn("Nothing find in imdb search for " + titleFilm);
				}
				
				// Sleep 5 sec between every connection for avoid too much request in a short time
				try {
					Thread.sleep(5000);
				} catch (InterruptedException ie) {
					Logger.error("INTERRUPTED_EXCEPTION_DURING_SLEEP_IN_GET_ID_IMDB", ie);
				}			
			} catch ( IOException ioe ) {
				Logger.error("IO_EXCEPTION_IN_GET_ID_IMDB", ioe);
			}
		}	
		return updatedFilm;
	}
	
	// exemple url return https://www.imdb.com/title/tt0295736/?ref_=fn_al_tt_1
	public String getIdImdbInURL(String url) {

		if (!url.isEmpty()) {
			String[] strs = url.split("/", 6);
			return strs[4];
		} else {
			Logger.error("SPLIT_URL_IMPOSSIBLE");
			return url;
		}		
	}
	
	@Override
	public List<Film> getTMDBInfos(List<Film> films) {

		List<Film> updatedFilms = new ArrayList<>();
		List<Genre> genres = genreService.getAllGenres();
		
		for (Film film : films) {
			List<Genre> newGenres = new ArrayList<>();
			try {
				Mono<MovieTMDB> movieMono = restTMDBController.getInfosFromApiTMDB(film.getIdImdb());		
				MovieTMDB movie = movieMono.block();
				Mono<CreditsTMDB> creditsMono = restTMDBController.getCreditsFilmsTMDB(film.getIdImdb());
				CreditsTMDB credits = creditsMono.block();
				if(movie != null) {
					film.setOriginalTitleFilm(CinemaUtilities.getStringLimited(movie.getOriginal_title(), 255));
					film.setYearFilm(movie.getRelease_date());
					film.setSynopsisFilm(CinemaUtilities.getStringLimited(movie.getOverview(), 1000));
					
					String titleFilm = film.getTitleFilm();
					String poster = movie.getPoster_path();
					film.setPosterFilm(CinemaUtilities.getStringLimited(poster, 255));
					
					String posterPath = restTMDBController.getPoster(poster, titleFilm);
					film.setPosterPath(posterPath);
					film.setTmdbRatingFilm(movie.getVote_average());
					film.setBudget(movie.getBudget());
					film.setRevenue(movie.getRevenue());
					film.setOriginalLanguage(CinemaUtilities.getStringLimited(movie.getOriginal_language(), 255));
					film.setTagline(CinemaUtilities.getStringLimited(movie.getTagline(), 255));
					film.setVoteCount(movie.getVote_count());;
					film.setRuntimeFilm(movie.getRuntime());
					
					String countries = "";
					for (ProductionCountryTMDB productionCountry : movie.getProduction_countries()) {
						countries += productionCountry.getName();
						countries += " / ";
					}
					film.setCountryFilm(CinemaUtilities.getStringLimited(countries, 255));
								
					movie.getGenres().stream().forEach(genreName->{
					    Genre genre = genres.stream().filter(g->g.getNameGenre().equals(genreName.getName())).findFirst().orElse(null);
					    if(genre != null){
					      newGenres.add(genre);
					    }
					});
					
					film.setGenres(newGenres);
					

					//String productions = "";
					List<String> prods = new ArrayList<>();
					for (ProductionCompagnyTMDB prod : movie.getProduction_companies()) {
//						productions += prod.getName();
//						productions += " ";
//						productions += prod.getOrigin_country();
//						productions += " / ";
						prods.add(prod.getName());
					}
						film.setProductionFilm(prods);
				}
				
				if (credits != null) {
					// get only 20 actors
					int limit = 20;
					List<CastTMDB> castList = new ArrayList<>();
					if (credits.getCast().size() > 20) {
						castList = credits.getCast().subList(0, limit);
					} else {
						castList = credits.getCast();
					}
					
					List<String> actors = new ArrayList<>();
					
					for (CastTMDB cast : castList) {
						if (cast.getName() != null) {
							actors.add(cast.getName());
							//film.addActor(cast.getName());
						}					
					}
					film.setActorsFilm(actors);
					
					List<CrewTMDB> crewList = credits.getCrew();
					
					for (CrewTMDB crew : crewList) {
						if (crew.getJob().equalsIgnoreCase("Director") && crew.getDepartment().equalsIgnoreCase("Directing")) {
							film.setDirectorFilm(crew.getName());
						}
						
						if (crew.getJob().equalsIgnoreCase("Story") && crew.getDepartment().equalsIgnoreCase("Writing")) {
							film.setWriterFilm(crew.getName());
						}
					}				
					
				}
				
				
			} catch (ApiTMDBException e){
				Logger.error("API_TMDB_EXCEPTION", e);
			}
			updatedFilms.add(film);
		}
		return updatedFilms;
	}
	
	@Override
	public List<Film> checkPathsFilms() {
		List<Film> updatedFilm = new ArrayList<>();
		List<Film> allFilms = this.getAllFilms();
		List<File> allFiles = fileRepository.getAllFilesInDirectory(filmRepoPath);
		Map<String, File> filesTitle = new HashMap<>();
		
		for (File file : allFiles) {
			filesTitle.put(CinemaUtilities.removeExtension(file.getName()), file);			
		}
		
		List<Film> differentFilms = allFilms.stream()
                .filter(film -> allFiles.stream()
                                    .noneMatch(file -> film.getPath().equals(file.getPath())))
                .collect(Collectors.toList());
		
		for (Film film : differentFilms) {

			File file = filesTitle.get(film.getTitleFilm());

			if (!file.getPath().equals(film.getPath())) {
				Logger.info("Film : " + film.getTitleFilm());
				Logger.info("Ancien path : " + film.getPath());
				Logger.info("Nouveau Path : " + file.getPath());
				
				film.setPath(file.getPath());
				updatedFilm.add(film);
				this.updateFilm(film);
			}	
		}
		return updatedFilm;
	}

	@Override
	public List<Film> findByTitleFilm(String titleFilm) {
		return filmRepository.findByTitleFilm(titleFilm);
	}
	
	public List<Film> findDuplicateFilms() {
		List<String> duplicateTitle = new ArrayList<>();
		List<Film> duplicateFilms = new ArrayList<>();
		duplicateTitle = filmRepository.findDuplicateFilm();
		
		for (String title : duplicateTitle) {
			duplicateFilms.addAll(filmRepository.findByTitleFilm(title));
			Logger.info("DUPLICATE_FILM : " + title);
		}
		
		for (int i = 1 ; i < duplicateFilms.size(); i++) {
			System.out.println(duplicateFilms.get(i).getTitleFilm());
			//this.deleteFilm(duplicateFilms.get(i));
		}

		return duplicateFilms;	
	}
	
	public List<Film> findFilmWithPathDeleted(){
		List<Film> films = this.getAllFilms();
		List<Film> filmsToDelete = new ArrayList<>();
		
		for(Film film : films) {
			File file = new File(film.getPath());
			
			if(!file.exists()) {
				filmsToDelete.add(film);
				System.out.println(film.getTitleFilm());
				//this.deleteFilm(film);
			}
		}
		return filmsToDelete;
	}
}
