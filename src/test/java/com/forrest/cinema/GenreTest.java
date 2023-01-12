/**
 * 
 */
package com.forrest.cinema;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.forrest.cinema.entities.Genre;
import com.forrest.cinema.repos.GenreRepository;
import com.forrest.cinema.service.GenreServiceImpl;

/**
 * @author martin
 *
 */

@SpringBootTest
public class GenreTest {

	@Autowired
	GenreRepository genreRepository;
	
	@Autowired
	GenreServiceImpl genreService;
	
	@Test
	public void testCreateGenre() {
		String genreName = "Action";
		Genre genre = new Genre(genreName);
		genreRepository.save(genre);
	}
	
	@Test
	public void testApiGenre() {
		//genreService.getAllTMDBGenres();
		//System.out.println(genreService.getAllNameGenre());
		
	}
}
