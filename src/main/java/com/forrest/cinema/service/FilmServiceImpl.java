/**
 * 
 */
package com.forrest.cinema.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
import com.forrest.cinema.entities.MovieTMDB;
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
			film.setTitleFilm(CinemaUtilities.removeExtension(file.getName()));
			film.setPath(file.getAbsolutePath());
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
		this.getIdImdb(newFilms);
		//this.getTMDBInfos(newFilms);
		return this.saveAllFilms(newFilms);		
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
					film.setDistanceTitleToOfficialTitle(distance);
					
					System.out.println(titleFilm +" -> "+ officialTitleFilm +" -> "+ distance);
					updatedFilm.add(film);
				} else {
					film.setOfficialTitleFilm("ERROR");
					film.setDistanceTitleToOfficialTitle(999);
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
				
				if(movie != null) {
					film.setOriginalTitleFilm(movie.getOriginal_title());
					film.setYearFilm(movie.getRelease_date());
					if (movie.getOverview().length() > 255) {
						film.setSynopsisFilm(movie.getOverview().substring(0, 255));
					}else {
						film.setSynopsisFilm(movie.getOverview());
					}
					
					film.setPosterFilm(movie.getPoster_path());
					film.setTmdbRatingFilm(movie.getVote_average());
					film.setBudget(movie.getBudget());
					film.setRevenue(movie.getRevenue());
					film.setOriginalLanguage(movie.getOriginal_language());
					film.setTagline(movie.getTagline());
					film.setVoteCount(movie.getVote_count());;
								
					movie.getGenres().stream().forEach(genreName->{
					    Genre genre = genres.stream().filter(g->g.getNameGenre().equals(genreName.getName())).findFirst().orElse(null);
					    if(genre != null){
					      newGenres.add(genre);
					    }
					});
					
					film.setGenres(newGenres);
					
					//Add only one production
					if(movie.getProduction_companies().size() > 0) {
						film.setProductionFilm(movie.getProduction_companies().get(0).getName());
					}
				}
			} catch (ApiTMDBException e){
				Logger.error("API_TMDB_EXCEPTION", e);
			}
			updatedFilms.add(film);
		}
		return updatedFilms;
	}
}
