/**
 * 
 */
package com.forrest.cinema.entities;

import java.util.List;

/**
 * @author martin
 *
 */
public class MovieTMDB {

	private Long budget;
	private List<GenreTMDB> genres;
	private long id;
	private String original_language;
	private String original_title;
	private String overview;
	private Long popularity;
	private String poster_path;
	private List<ProductionCompagnyTMDB> production_companies;
	private String release_date;
	private Long revenue;
	private Integer runTime;
	private String status;
	private String tagline;
	private String title;
	private Long vote_average;
	private Integer vote_count;
	
	
	/**
	 * 
	 */
	public MovieTMDB() {
		super();
	}
	
	/**
	 * @return the budget
	 */
	public Long getBudget() {
		return budget;
	}
	/**
	 * @param budget the budget to set
	 */
	public void setBudget(Long budget) {
		this.budget = budget;
	}
	/**
	 * @return the genres
	 */
	public List<GenreTMDB> getGenres() {
		return genres;
	}
	/**
	 * @param genres the genres to set
	 */
	public void setGenres(List<GenreTMDB> genres) {
		this.genres = genres;
	}
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * @return the original_language
	 */
	public String getOriginal_language() {
		return original_language;
	}
	/**
	 * @param original_language the original_language to set
	 */
	public void setOriginal_language(String original_language) {
		this.original_language = original_language;
	}
	/**
	 * @return the original_title
	 */
	public String getOriginal_title() {
		return original_title;
	}
	/**
	 * @param original_title the original_title to set
	 */
	public void setOriginal_title(String original_title) {
		this.original_title = original_title;
	}
	/**
	 * @return the overview
	 */
	public String getOverview() {
		return overview;
	}
	/**
	 * @param overview the overview to set
	 */
	public void setOverview(String overview) {
		this.overview = overview;
	}
	/**
	 * @return the popularity
	 */
	public Long getPopularity() {
		return popularity;
	}
	/**
	 * @param popularity the popularity to set
	 */
	public void setPopularity(Long popularity) {
		this.popularity = popularity;
	}
	/**
	 * @return the poster_path
	 */
	public String getPoster_path() {
		return poster_path;
	}
	/**
	 * @param poster_path the poster_path to set
	 */
	public void setPoster_path(String poster_path) {
		this.poster_path = poster_path;
	}
	/**
	 * @return the production_compagnies
	 */
	public List<ProductionCompagnyTMDB> getProduction_companies() {
		return production_companies;
	}
	/**
	 * @param production_compagnies the production_compagnies to set
	 */
	public void setProduction_companies(List<ProductionCompagnyTMDB> production_companies) {
		this.production_companies = production_companies;
	}
	/**
	 * @return the release_date
	 */
	public String getRelease_date() {
		return release_date;
	}
	/**
	 * @param release_date the release_date to set
	 */
	public void setRelease_date(String release_date) {
		this.release_date = release_date;
	}
	/**
	 * @return the revenue
	 */
	public Long getRevenue() {
		return revenue;
	}
	/**
	 * @param revenue the revenue to set
	 */
	public void setRevenue(Long revenue) {
		this.revenue = revenue;
	}
	/**
	 * @return the runTime
	 */
	public Integer getRunTime() {
		return runTime;
	}
	/**
	 * @param runTime the runTime to set
	 */
	public void setRunTime(Integer runTime) {
		this.runTime = runTime;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the tagline
	 */
	public String getTagline() {
		return tagline;
	}
	/**
	 * @param tagline the tagline to set
	 */
	public void setTagline(String tagline) {
		this.tagline = tagline;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the vote_average
	 */
	public Long getVote_average() {
		return vote_average;
	}
	/**
	 * @param vote_average the vote_average to set
	 */
	public void setVote_average(Long vote_average) {
		this.vote_average = vote_average;
	}
	/**
	 * @return the vote_count
	 */
	public Integer getVote_count() {
		return vote_count;
	}
	/**
	 * @param vote_count the vote_count to set
	 */
	public void setVote_count(Integer vote_count) {
		this.vote_count = vote_count;
	}

	@Override
	public String toString() {
		return "MovieTMDB [genres=" + genres + ", production_compagnies=" + production_companies + ", title=" + title
				+ "]";
	}
	
}
