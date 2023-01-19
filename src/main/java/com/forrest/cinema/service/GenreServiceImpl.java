/**
 * 
 */
package com.forrest.cinema.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.forrest.cinema.controller.RestTMDBController;
import com.forrest.cinema.entities.Genre;
import com.forrest.cinema.entities.GenreTMDB;
import com.forrest.cinema.entities.GenresTMDB;
import com.forrest.cinema.repos.GenreRepository;
import com.forrest.cinema.utils.CinemaUtilities;
import com.google.gson.Gson;

import reactor.core.publisher.Mono;

/**
 * @author martin
 *
 */
@Service
public class GenreServiceImpl implements GenreService {
	
	@Autowired
	GenreRepository genreRepository;
	
	@Autowired
	private RestTMDBController restTMDBController;

	@Override
	public Genre saveGenre(Genre g) {
		return genreRepository.save(g);
	}
	
	public List<Genre> saveAllGenre(List<Genre> genres) {
		return genreRepository.saveAll(genres);
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
	public void deleteGenreById(Long id) {
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
	
	@Override
	public List<String> getAllNameGenre() {
		return genreRepository.findAllNameGenre();
	}
	
	@Override
	public Genre findGenreByName(String name) {
		return genreRepository.findByNameGenre(name);
	}
	
	@Override
	public List<Genre> getAllTMDBGenres() {
		
		List<Genre> genresList = new ArrayList<>();
		Mono<String> genresStrMono = restTMDBController.getAllTMDBGenres();
		Gson gson = new Gson();
		GenresTMDB genres = gson.fromJson(genresStrMono.block(), GenresTMDB.class);

		for (GenreTMDB genreTMDB : genres.getGenresTMDB()) {
			Genre genre = new Genre();
			genre.setNameGenre(CinemaUtilities.getStringLimited(genreTMDB.getName(), 255));
			genresList.add(genre);
		}
		
		return this.saveAllGenre(genresList);		
	}
	
}
