package com.forrest.cinema.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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

import com.forrest.cinema.entities.Genre;
import com.forrest.cinema.repos.GenreRepository;
import com.forrest.cinema.service.GenreServiceImpl;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class GenreServiceTest {

	@InjectMocks
    GenreServiceImpl genreService;

    @Mock
    GenreRepository genreRepository;
	
    @Test
    public void saveGenreTest() {
        Genre g = new Genre();
        g.setNameGenre("Action");
        g.setDescriptionGenre("");
        // Configurer le comportement du mock pour retourner g lorsque la méthode save est appelée
        Mockito.when(genreRepository.save(g)).thenReturn(g);

        Genre result = genreService.saveGenre(g);

        // Vérifier que la méthode save a été appelée une fois
        verify(genreRepository, times(1)).save(g);
        // Vérifier que le résultat est égal à g
        assertEquals(g, result);
    }

    @Test
    public void saveAllGenreTest() {
        List<Genre> genres = new ArrayList<>();
        Genre g1 = new Genre();
        g1.setNameGenre("Action");
        genres.add(g1);
        Genre g2 = new Genre();
        g2.setNameGenre("Comedy");
        genres.add(g2);

        // Configurer le comportement du mock pour retourner genres lorsque la méthode saveAll est appelée
        when(genreRepository.saveAll(genres)).thenReturn(genres);

        List<Genre> result = genreService.saveAllGenre(genres);

        // Vérifier que la méthode saveAll a été appelée une fois
        verify(genreRepository, times(1)).saveAll(genres);
        // Vérifier que le résultat est égal à genres
        assertEquals(genres, result);
    }
    
    @Test
    public void updateGenreTest() {
        // Initialiser un objet Genre
        Genre genre = new Genre();
        genre.setNameGenre("action");
        // Configurer le comportement du mock genreRepository pour qu'il retourne l'objet genre lorsque la méthode save() est appelée avec genre en paramètre
        Mockito.when(genreRepository.save(genre)).thenReturn(genre);
        // Appeler la méthode updateGenre()
        Genre updatedGenre = genreService.updateGenre(genre);
        // Vérifier que la méthode save() du mock genreRepository a bien été appelée
        Mockito.verify(genreRepository).save(genre);
        // Vérifier que la méthode updateGenre() retourne bien l'objet genre
        assertEquals(genre, updatedGenre);
    }

    @Test
    public void deleteGenreTest() {
        // Initialiser un objet Genre
        Genre genre = new Genre();
        genre.setNameGenre("action");
        // Appeler la méthode deleteGenre()
        genreService.deleteGenre(genre);
        // Vérifier que la méthode delete() du mock genreRepository a bien été appelée avec genre en paramètre
        Mockito.verify(genreRepository).delete(genre);
    }
    
    @Test
    public void deleteGenreByIdTest() {
        // Initialiser un genre
        Genre genre = new Genre("genre1");
        genre.setIdGenre(1L);
        // Configurer le comportement du mock pour qu'il ne lève pas d'exception lorsque la méthode deleteById() est appelée
        Mockito.doNothing().when(genreRepository).deleteById(1L);
        // Appeler la méthode deleteGenreById()
        genreService.deleteGenreById(1L);
        // Vérifier que la méthode deleteById() a bien été appelée sur le mock
        Mockito.verify(genreRepository).deleteById(1L);
    }
    
    @Test
    public void getGenreTest() {
        // Créer un genre de test
        Genre genre = new Genre();
        genre.setNameGenre("testGenre");
        // Configurer le comportement du mock genreRepository pour qu'il retourne ce genre lorsque la méthode findById() est appelée avec l'id 1
        Mockito.when(genreRepository.findById(1L)).thenReturn(Optional.of(genre));
        // Appeler la méthode getGenre()
        Genre result = genreService.getGenre(1L);
        // Vérifier que le résultat est égal au genre de test
        assertEquals(genre, result);
    }

    @Test
    public void getAllGenresTest() {
        // Configurer le comportement du repository mock pour qu'il retourne une liste de genres lorsque la méthode findAll() est appelée
    	Genre genre1 = new Genre("Action");
    	genre1.setIdGenre(1L);
    	Genre genre2 = new Genre("Comedy");
    	genre2.setIdGenre(2L);
        List<Genre> genres = new ArrayList<>();
        genres.add(genre1);
        genres.add(genre2);
        Mockito.when(genreRepository.findAll()).thenReturn(genres);
        // Appeler la méthode getAllGenres()
        List<Genre> result = genreService.getAllGenres();
        // Vérifier que la liste de genres retournée par la méthode est bien celle configurée pour le repository mock
        assertEquals(genres, result);
    }

    @Test
    public void getAllNameGenreTest() {
        // Initialisation des données de test
        List<String> expectedGenreNames = Arrays.asList("Action", "Comedy", "Drama");
        // Configurer le comportement du mock de genreRepository pour qu'il retourne les noms de genres attendus lorsque la méthode findAllNameGenre est appelée
        Mockito.when(genreRepository.findAllNameGenre()).thenReturn(expectedGenreNames);
        // Appeler la méthode à tester
        List<String> actualGenreNames = genreService.getAllNameGenre();
        // Vérifier que la méthode retourne les noms de genres attendus
        assertEquals(expectedGenreNames, actualGenreNames);
    }

    @Test
    public void findGenreByNameTest() {

        // Créer un objet Genre mocké
        Genre genre = new Genre("action");
        // Configurer le comportement de la méthode findByNameGenre pour qu'elle retourne l'objet Genre mocké lorsqu'elle est appelée avec "action" en paramètre
        when(genreRepository.findByNameGenre("action")).thenReturn(genre);
        
		// Appeler la méthode findGenreByName avec "action" en paramètre
        Genre result = genreService.findGenreByName("action");
        // Vérifier que la méthode retourne l'objet Genre mocké
        assertEquals(genre, result);
    }

}
