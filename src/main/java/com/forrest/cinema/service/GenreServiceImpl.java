/**
 * 
 */
package com.forrest.cinema.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.forrest.cinema.entities.Genre;
import com.forrest.cinema.repos.GenreRepository;

/**
 * @author martin
 *
 */
public class GenreServiceImpl implements GenreService {
	
	@Autowired
	GenreRepository genreRepository;

	@Override
	public Genre saveGenre(Genre g) {
		return genreRepository.save(g);
	}

	@Override
	public Genre updateGenre(Genre g) {
		return genreRepository.save(g);
	}

	@Override
	public void deleteGenre(Genre g) {
		genreRepository.delete(g);
	}

	@Override
	public void deleteFilmById(Long id) {
		genreRepository.deleteById(id);
	}

	@Override
	public Genre getGenre(Long id) {
		return genreRepository.findById(id).get();
	}

	@Override
	public List<Genre> getAllGenres() {
		return genreRepository.findAll();
	}

}
