/**
 * 
 */
package com.forrest.cinema.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
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
	
	public List<File> getAllFilmsInRepository() {

		return CinemaUtilities.listOfFiles(filmRepoPath);
	}
	
	public List<File> getNewFilmsFilesInRepository() {

		List<File> filmFilesList = this.getAllFilmsInRepository();
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
		
		List<File> files = this.getNewFilmsFilesInRepository();
		List<Film> newFilms = this.fileToFilm(files);
		//this.getIdImdb(newFilms);
		this.saveAllFilms(newFilms);		
	}
	
	

}
