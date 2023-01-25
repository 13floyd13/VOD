package com.forrest.cinema.entities.tmdb;

public class CastTMDB {

	private int id;
    private String character;
    private String credit_id;
    private String name;
    private String profile_path;
    
    
	public CastTMDB() {
		super();
	}


	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}


	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}


	/**
	 * @return the character
	 */
	public String getCharacter() {
		return character;
	}


	/**
	 * @param character the character to set
	 */
	public void setCharacter(String character) {
		this.character = character;
	}


	/**
	 * @return the credit_id
	 */
	public String getCredit_id() {
		return credit_id;
	}


	/**
	 * @param credit_id the credit_id to set
	 */
	public void setCredit_id(String credit_id) {
		this.credit_id = credit_id;
	}


	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}


	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}


	/**
	 * @return the profile_path
	 */
	public String getProfile_path() {
		return profile_path;
	}


	/**
	 * @param profile_path the profile_path to set
	 */
	public void setProfile_path(String profile_path) {
		this.profile_path = profile_path;
	}

	
}
