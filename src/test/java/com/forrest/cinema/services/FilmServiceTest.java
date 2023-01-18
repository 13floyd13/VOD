package com.forrest.cinema.services;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.forrest.cinema.entities.Film;
import com.forrest.cinema.entities.Genre;
import com.forrest.cinema.repos.FilmRepository;
import com.forrest.cinema.service.FilmServiceImpl;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class FilmServiceTest {

    @InjectMocks
    FilmServiceImpl filmService;

    @Mock
    FilmRepository filmRepository;

    @Test
    public void saveFilmTest() {
        Film film = new Film();
        film.setTitleFilm("Film Title");
        film.setDirectorFilm("Film Director");
        // Configure le comportement du mock pour qu'il retourne l'objet film lorsque la méthode save() est appelée
        Mockito.when(filmRepository.save(film)).thenReturn(film);
        // Appeler la méthode saveFilm() qui utilise le mock
        Film savedFilm = filmService.saveFilm(film);
        // Vérifie que le service retourne le film attendu
        assertEquals(film, savedFilm);
    }

    @Test
    public void updateFilmTest() {
        Film film = new Film();
        film.setIdFilm(1L);
        film.setTitleFilm("Film Title");
        film.setDirectorFilm("Film Director");
        // Configure le comportement du mock pour qu'il retourne l'objet film lorsque la méthode save() est appelée
        Mockito.when(filmRepository.save(film)).thenReturn(film);
        // Appeler la méthode updateFilm() qui utilise le mock
        Film updatedFilm = filmService.updateFilm(film);
        // Vérifie que le service retourne le film attendu
        assertEquals(film, updatedFilm);
    }

    @Test
    public void deleteFilmTest() {
        Film film = new Film();
        film.setIdFilm(1L);
        film.setTitleFilm("Film Title");
        film.setDirectorFilm("Film Director");
        // Appeler la méthode deleteFilm() qui utilise le mock
        filmService.deleteFilm(film);
        // Vérifie que le mock a bien reçu l'appel de la méthode delete() avec l'objet film en paramètre
        Mockito.verify(filmRepository, Mockito.times(1)).delete(film);
    }

    @Test
    public void deleteFilmByIdTest() {
        Long id = 1L;
        // Appeler la méthode deleteFilmById() qui utilise le mock
        filmService.deleteFilmById(id);
        // Vérifie que le mock a bien reçu l'appel de la méthode deleteById() avec l'id en paramètre
        Mockito.verify(filmRepository, Mockito.times(1)).deleteById(id);
    }
    
    @Test
    public void getFilmTest() {
        Long id = 1L;
        Film film = new Film();
        film.setIdFilm(id);
        film.setTitleFilm("Film Title");
        film.setDirectorFilm("Film Director");
        // Configure le comportement du mock pour qu'il retourne l'objet film lorsque la méthode findById() est appelée
        Optional<Film> optionalFilm = Optional.of(film);
        Mockito.when(filmRepository.findById(id)).thenReturn(optionalFilm);
        // Appeler la méthode getFilm() qui utilise le mock
        Film returnedFilm = filmService.getFilm(id);
        // Vérifie que le service retourne le film attendu
        assertEquals(film, returnedFilm);
    }
    
    @Test
    public void getAllFilmsTest() {
        List<Film> films = new ArrayList<>();
        films.add(new Film());
        films.add(new Film());
        films.add(new Film());
        Mockito.when(filmRepository.findAll()).thenReturn(films);
        assertEquals(3, filmService.getAllFilms().size());
    }
    
    @Test
    public void getAllTitlesFilmTest() {
        List<String> titles = Arrays.asList("Film Title 1", "Film Title 2");
        // Configurer le comportement du mock pour qu'il retourne la liste de titres de films lorsque la méthode findAllFilmsTitle() est appelée
        Mockito.when(filmRepository.findAllFilmsTitle()).thenReturn(titles);
        // Appeler la méthode getAllTitlesFilm() qui utilise le mock
        List<String> returnedTitles = filmService.getAllTitlesFilm();
        // Vérifie que le service retourne la liste de titres attendue
        assertEquals(titles, returnedTitles);
    }
    
    @Test
    public void getAllFilmsByGenreTest() {
        String genreName = "Action";
        Genre genre = new Genre();
        genre.setNameGenre(genreName);
        List<Film> films = new ArrayList<>();
        Film film1 = new Film();
        film1.setIdFilm(1L);
        film1.setTitleFilm("Film Title 1");
        film1.setDirectorFilm("Film Director 1");
        film1.add(genre);
        films.add(film1);

        // Configurer le comportement du mock pour qu'il retourne la liste de films de genre "genre" lorsque la méthode findAllFilmsByGenre() est appelée avec "genre" en paramètre
        Mockito.when(filmRepository.findAllFilmsByGenre(genreName)).thenReturn(films);
        // Appeler la méthode getAllFilmsByGenre() qui utilise le mock
        List<Film> returnedFilms = filmService.getAllFilmsByGenre(genreName);
        // Vérifie que le service retourne la liste de films de genre "genre" attendue
        assertEquals(films, returnedFilms);
    }

    @Test
    public void saveAllFilmsTest() {
        List<Film> films = new ArrayList<>();
        Film film1 = new Film();
        film1.setIdFilm(1L);
        film1.setTitleFilm("Film Title 1");
        film1.setDirectorFilm("Film Director 1");
        films.add(film1);
        Film film2 = new Film();
        film2.setIdFilm(2L);
        film2.setTitleFilm("Film Title 2");
        film2.setDirectorFilm("Film Director 2");
        films.add(film2);
        // Configurer le comportement du mock pour qu'il retourne la liste de films lorsque la méthode saveAll() est appelée avec cette liste en paramètre
        Mockito.when(filmRepository.saveAll(films)).thenReturn(films);
        // Appeler la méthode saveAllFilms() qui utilise le mock
        List<Film> returnedFilms = filmService.saveAllFilms(films);
        // Vérifie que le service retourne la liste de films attendue
        assertEquals(films, returnedFilms);
    }
    
    @Test
    public void testGetIdImdbInURL() {
        FilmServiceImpl filmService = new FilmServiceImpl();
        String url = "https://www.imdb.com/title/tt0295736/?ref_=fn_al_tt_1";
        String expectedId = "tt0295736";
        assertEquals(expectedId, filmService.getIdImdbInURL(url));
    }
}
