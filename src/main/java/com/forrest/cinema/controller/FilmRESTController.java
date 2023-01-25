/**
 * 
 */
package com.forrest.cinema.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.forrest.cinema.entities.Film;
import com.forrest.cinema.service.FilmService;

/**
 * @author martin
 *
 */
@RestController
@RequestMapping("/api/films")
@CrossOrigin
public class FilmRESTController {
	
	@Autowired
	FilmService filmService;

	@PostMapping("/createfilm")
	public Film createFilm(@RequestBody Film film) {
		return filmService.saveFilm(film);
	}
	
	@PutMapping
	public Film updateFilm(@RequestBody Film film) {
		return filmService.updateFilm(film);
	}
	
	@DeleteMapping("/{film}")
	public void deleteFilm(@PathVariable("film") Film film) {
		filmService.deleteFilm(film);
	}
	
	@DeleteMapping("/{id}")
	public void deleteFilm(@PathVariable("id") Long id) {
		filmService.deleteFilmById(id);
	}
	
	@GetMapping("/{id}")
	public Film getFilmById(@PathVariable("id") Long id) {
		return filmService.getFilm(id);
	}
	
	@GetMapping("/all")
	public List<Film> getAllFilms() {
		return filmService.getAllFilms();
	}
	
	@GetMapping("/filmsgenre/{genreName}")
	public List<Film> getAllFilmsByGenre(@PathVariable("genreName") String genreName) {
		return filmService.getAllFilmsByGenre(genreName);
	}
	
	@PostMapping
	public List<Film> createFilms(@RequestBody List<Film> films) {
		return filmService.saveAllFilms(films);
	}

}
