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
	
	public void getAllTMDBGenres() {
		
		System.out.println("Salut");
		
		Mono<String> genresStrMono = restTMDBController.getAllTMDBGenres();
		String reponse = genresStrMono.block();
		System.out.println(reponse);
		Gson gson = new Gson();
		GenresTMDB genres = gson.fromJson(genresStrMono.block(), GenresTMDB.class);
		System.out.println(genres.toString());
		List<Genre> genresList = new ArrayList<>();
		for (GenreTMDB genreTMDB : genres.getGenresTMDB()) {
			Genre genre = new Genre();
			genre.setNameGenre(genreTMDB.getName());
			genresList.add(genre);
		}
		
		this.saveAllGenre(genresList);
		
	}
	
	public List<String> getAllNameGenre() {
		return genreRepository.findAllNameGenre();
	}
	

	public Genre findGenreByName(String name) {
		return genreRepository.findByNameGenre(name);
	}

}
