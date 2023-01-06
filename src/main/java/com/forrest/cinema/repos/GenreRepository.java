/**
 * 
 */
package com.forrest.cinema.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.forrest.cinema.entities.Genre;

/**
 * @author martin
 *
 */
public interface GenreRepository extends JpaRepository<Genre, Long>{

}
