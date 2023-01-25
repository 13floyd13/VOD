/**
 * 
 */
package com.forrest.cinema.entities.tmdb;

/**
 * @author martin
 *
 */
public class ProductionCountryTMDB {
	
	private String iso_3166_1;
    private String name;

	/**
	 * 
	 */
	public ProductionCountryTMDB() {
		super();
	}

    public String getIso_3166_1() {
        return iso_3166_1;
    }

    public void setIso_3166_1(String iso_3166_1) {
        this.iso_3166_1 = iso_3166_1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
