/**
 * 
 */
package com.forrest.cinema.service;

import java.io.File;
import java.util.List;

import com.forrest.cinema.entities.Film;

/**
 * @author martin
 *
 */
public interface FilmService {

	Film saveFilm(Film f);
	Film updateFilm(Film f);
	void deleteFilm(Film f);
	void deleteFilmById(Long id);
	Film getFilm(Long id);
	List<Film> getAllFilms();
	List<Film> getAllFilmsByGenre(String genre);
	List<Film> saveAllFilms(List<Film> films);
	List<File> getAllFilesInRepository();
	List<File> getNewFilesInRepository();
	List<Film> fileToFilm(List<File> filesFilms);
	void saveAllNewFilms();
}
