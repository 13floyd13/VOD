/**
 * 
 */
package com.forrest.cinema;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.forrest.cinema.entities.Genre;
import com.forrest.cinema.repos.GenreRepository;

/**
 * @author martin
 *
 */

@SpringBootTest
public class GenreTest {

	@Autowired
	GenreRepository genreRepository;
	
	@Test
	public void testCreateGenre() {
		String genreName = "Action";
		Genre genre = new Genre(genreName);
		genreRepository.save(genre);
	}
}
