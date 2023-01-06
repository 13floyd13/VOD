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

import com.forrest.cinema.entities.Film;
import com.forrest.cinema.repos.FilmRepository;
import com.forrest.cinema.utils.CinemaUtilities;

/**
 * @author martin
 *
 */

@Service
public class FilmServiceImpl implements FilmService {
	
	@Autowired
	FilmRepository filmRepository;
	
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
	public List<Film> getAllFilmsByGenre(String genre) {
		return filmRepository.findAllFilmsByGenre(genre);
	}

	@Override
	public List<Film> saveAllFilms(List<Film> films) {
		return filmRepository.saveAll(films);
	}
	
	@Override
	public List<File> getAllFilesInRepository() {
		return CinemaUtilities.listOfFiles(filmRepoPath);
	}
	
	@Override
	public List<File> getNewFilesInRepository() {

		List<File> filmFilesList = this.getAllFilesInRepository();
		List<String> titleFilmsList = filmRepository.findAllFilmsTitle();
		List<File> newFilmsFiles = new ArrayList<>();
		
		for (File filmFile : filmFilesList) {
			if (!titleFilmsList.contains(filmFile.getName())) {
				newFilmsFiles.add(filmFile);
			}
		}
		
		return newFilmsFiles;
	}
	
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
	
	public void saveAllNewFilms() {
		
		List<File> files = this.getNewFilesInRepository();
		List<Film> newFilms = this.fileToFilm(files);
		this.getIdImdb(newFilms);
		this.saveAllFilms(newFilms);		
	}
	
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
				String officialTitleFilm = headline.text();
				String url = headline.absUrl("href");
				String idImdb = this.getIdImdbInURL(url);
				
				// for detect if we get wrong film
				int distance = CinemaUtilities.getLevenshteinDistance(titleFilm, officialTitleFilm);
				
				film.setOfficialTitleFilm(officialTitleFilm);
				film.setIdImdb(idImdb);
				film.setDistanceTitleToOfficialTitle(distance);
				
				updatedFilm.add(film);
				
				// Sleep 10 sec between every connection for avoid too much request in a short time
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			} catch ( IOException ioe ) {
				ioe.printStackTrace();
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
			return url;
		}
		
	}
	
	

}
