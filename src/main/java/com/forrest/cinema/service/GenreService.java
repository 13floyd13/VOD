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
	void deleteGenreById(Long id);
	Genre getGenre(Long id);
	List<Genre> getAllGenres();
	List<String> getAllNameGenre();
	Genre findGenreByName(String name);
	List<Genre> getAllTMDBGenres();

}
