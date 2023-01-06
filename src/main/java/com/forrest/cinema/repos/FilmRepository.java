package com.forrest.cinema.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.forrest.cinema.entities.Film;

public interface FilmRepository extends JpaRepository<Film, Long> {
	
	@Query("SELECT f FROM Film f LEFT JOIN FETCH f.genres g WHERE g.nameGenre = :genre")
	List<Film> findAllFilmsByGenre(@Param("genre") String genre);
	
	@Query("SELECT f.titleFilm FROM Film f")
	List<String> findAllFilmsTitle();
	
	@Query("SELECT f FROM Film f WHERE idImdb IS NULL OR idImdb = '' ")
	List<Film> findAllFilmsWithoutIdImdb();
}
