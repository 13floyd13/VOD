package com.forrest.cinema.entities.tmdb;

import java.util.List;

public class CreditsTMDB {
	
	private List<CastTMDB> cast;
    private List<CrewTMDB> crew;

	public CreditsTMDB() {
		super();
	}

	/**
	 * @return the cast
	 */
	public List<CastTMDB> getCast() {
		return cast;
	}

	/**
	 * @param cast the cast to set
	 */
	public void setCast(List<CastTMDB> cast) {
		this.cast = cast;
	}

	/**
	 * @return the crew
	 */
	public List<CrewTMDB> getCrew() {
		return crew;
	}

	/**
	 * @param crew the crew to set
	 */
	public void setCrew(List<CrewTMDB> crew) {
		this.crew = crew;
	}
	
	
	

}
