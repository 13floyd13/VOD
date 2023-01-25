package com.forrest.cinema.entities.tmdb;

public class CrewTMDB {

	private int id;
    private String credit_id;
    private String name;
    private String department;
    private String job;
    private String profile_path;
    
    
	public CrewTMDB() {
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
	 * @return the department
	 */
	public String getDepartment() {
		return department;
	}

	/**
	 * @param department the department to set
	 */
	public void setDepartment(String department) {
		this.department = department;
	}

	/**
	 * @return the job
	 */
	public String getJob() {
		return job;
	}

	/**
	 * @param job the job to set
	 */
	public void setJob(String job) {
		this.job = job;
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
