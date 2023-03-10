package com.forrest.cinema;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import com.forrest.cinema.entities.Film;
import com.forrest.cinema.entities.Genre;
import com.forrest.cinema.repos.FilmRepository;
import com.forrest.cinema.repos.GenreRepository;
import com.forrest.cinema.service.FilmService;

@SpringBootTest
class FilmsLibraryApplicationTests {
	
	@Autowired
	private FilmRepository filmRepository;
	
	@Autowired
	private GenreRepository genreRepository;
	
	@Autowired
	private FilmService filmService;

	@Test
	public void testCreateFilm() {
		String titleFilm = "TestTitleFilm";
		Film film = new Film(titleFilm);
		filmRepository.save(film);
	}
	
	@Test
	public void testFindFilm() {
		Film f = filmRepository.findById(1L).get();
		System.out.println(f.getTitleFilm());
		Assert.isTrue(f.getTitleFilm().equals("TestTitleFilm"), "Title must be TestTitleFilm");
	}
	
	@Test
	public void testUpdateFilm() {
		Film f = filmRepository.findById(1L).get();
		f.setYearFilm("2022");
		filmRepository.save(f);
		Assert.isTrue(f.getYearFilm().equals("2022"), "good title");
	}
	
	@Test
	public void testAddGenreToFilm() {
		Film f = filmRepository.findById(1L).get();
		Genre genre = genreRepository.findById(1L).get();
		System.out.println(genre.getNameGenre());
		ArrayList genres = new ArrayList<>();
		genres.add(genre);
		f.setGenres(genres);
		//f.add(genre);
		System.out.println(f.getGenres().get(0).getNameGenre());
		filmRepository.save(f);
	}
	
	@Test
	public void testFindFilmsByGenre() {
		String genre = "Action";
		List<Film> films = filmRepository.findAllFilmsByGenre(genre); //filmService.getAllFilmsByGenre(genre);
		
		for(Film f : films) {
			System.out.println(f.getTitleFilm());
		}
	}
	
	@Test
	public void testGetAllFilmFromRepo() {
		List<File> files = filmService.getAllFilmsInRepository();
	}
	
	@Test
	public void testSaveAllNewFilms() {
		filmRepository.deleteAll();
		filmService.saveAllNewFilms();
		
	}
	
	@Test
	void contextLoads() {
	}

}
