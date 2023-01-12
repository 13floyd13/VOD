/**
 * 
 */
package com.forrest.cinema.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.forrest.cinema.entities.Genre;

/**
 * @author martin
 *
 */

public interface GenreRepository extends JpaRepository<Genre, Long>{

	@Query("SELECT g.nameGenre FROM Genre g")
	List<String> findAllNameGenre();

	@Query("SELECT g FROM Genre g WHERE g.nameGenre = :name")
	Genre findByNameGenre(@Param("name") String name);
}
