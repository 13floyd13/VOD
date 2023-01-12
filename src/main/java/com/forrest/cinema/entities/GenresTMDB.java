/**
 * 
 */
package com.forrest.cinema.entities;

import java.util.List;

/**
 * @author martin
 *
 */
public class GenresTMDB {

	
	/**
	 * 
	 */
	public GenresTMDB() {
		super();
	}

	private List<GenreTMDB> genres;

	/**
	 * @return the genresTMDB
	 */
	public List<GenreTMDB> getGenresTMDB() {
		return genres;
	}

	/**
	 * @param genresTMDB the genresTMDB to set
	 */
	public void setGenresTMDB(List<GenreTMDB> genres) {
		this.genres = genres;
	}

	@Override
	public String toString() {
		return "GenresTMDB [genresTMDB=" + genres + "]";
	}
	
	
	
	

}
