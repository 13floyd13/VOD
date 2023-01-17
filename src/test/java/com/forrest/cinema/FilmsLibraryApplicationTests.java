package com.forrest.cinema;

import java.io.File;
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
import com.forrest.cinema.service.FilmServiceImpl;


@SpringBootTest
class FilmsLibraryApplicationTests {
	
	@Autowired
	private FilmRepository filmRepository;
	
	@Autowired
	private GenreRepository genreRepository;
	
	@Autowired
	private FilmServiceImpl filmService;
	
//	@Autowired
//	private RestTMDBController restTMDBController;

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
		ArrayList<Genre> genres = new ArrayList<>();
		genres.add(genre);
		f.setGenres(genres);
		//f.add(genre);
		System.out.println(f.getGenres().get(0).getNameGenre());
		filmRepository.save(f);
	}
	
	@Test
	public void testFindFilmsByGenre() {
		String genreStr = "Action";
		//Genre genre = genreRepository.findByNameGenre(genreStr);
		List<Film> films = filmRepository.findAllFilmsByGenre(genreStr);
		if (films.isEmpty() || films == null) {
			System.out.println("nope");
		}
		for (Film film : films) {
			System.out.println(film.getTitleFilm());
		}
	}
	
	@SuppressWarnings("unused")
	@Test
	public void testGetAllFilmFromRepo() {
		//List<File> files = filmService.getAllFilesInRepository();
		List<Film> films = filmService.getAllFilms();
	}
	
	@Test
	public void testSaveAllNewFilms() {
		filmRepository.deleteAll();
		filmService.saveAllNewFilms();	
	}
	
	@Test
	void contextLoads() {
	}
	
	@Test
	public void testWebClient() {
		
		List<Film> films = filmRepository.findAll();
		films = filmService.getTMDBInfos(films);
		
		filmService.saveAllFilms(films);
	}

}
