/**
 * 
 */
package com.forrest.cinema.service;

import java.util.List;

import com.forrest.cinema.entities.Genre;

/**
 * @author martin
 *
 */
public interface GenreService {
	
	Genre saveGenre(Genre g);
	Genre updateGenre(Genre g);
	void deleteGenre(Genre g);
	void deleteFilmById(Long id);
	Genre getGenre(Long id);
	List<Genre> getAllGenres();

}
