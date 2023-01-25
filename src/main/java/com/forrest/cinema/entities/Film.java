package com.forrest.cinema.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

/**
 * @author martin
 *
 */
@Entity
@Table(name = "film")
public class Film implements Serializable {

	private static final long serialVersionUID = -3585576009123254031L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_film")
	private Long idFilm;
	
	@Column(length = 255)
	private String titleFilm;
	@Column(length = 255)
	private String officialTitleFilm;
	@Column(length = 255)
	private String originalTitleFilm;
	@Column(length = 255)
	private String idImdb;
	@Column(length = 255)
	private String yearFilm;
	@Column(nullable = true)
	private Integer runtimeFilm;
	@Column(length = 255)
	private String directorFilm;
	@Column(length = 255)
	private String writerFilm;
	@Column(nullable = true)
	private List<String> actorsFilm;
	
	@Column(name = "synopsis_film", length = 1000)
	private String synopsisFilm;
	@Column(length = 255)
	private String countryFilm;
	@Column(length = 255)
	private String awardsFilms;
	@Column(length = 255)
	private String posterFilm;
	@Column(length = 255)
	private Long tmdbRatingFilm;
	@Column(nullable = true)
	private Long budget;
	@Column(nullable = true)
	private Long revenue;
	@Column(length = 255)
	private String originalLanguage;
	@Column(length = 255)
	private String tagline;
	@Column(length = 255)
	private String productionFilm;
	@Column(length = 255)
	private String languagesFilm;
	@Column(nullable = true)
	private Integer distanceTitleToOfficialTitle;
	@Column(length = 500)
	private String path;
	@Column(nullable = true)
	private double size;
	private Integer voteCount;
	@Column(nullable = true)
	private Boolean seen;
	@Column(length = 255)
	private String posterPath;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(
			name = "genre_film",
			joinColumns = @JoinColumn(name = "id_film"),
			inverseJoinColumns = @JoinColumn(name = "id_genre")
	)
	private List<Genre> genres = new ArrayList<>();
	
	public Film() {
		super();
	}
	
	public Film(String titleFilm) {
		super();
		this.titleFilm = titleFilm;
	}

	/**
	 * 
	 * @see java.util.List#clear()
	 */
	public void clear() {
		genres.clear();
	}

	/**
	 * @return the idFilm
	 */
	public Long getIdFilm() {
		return idFilm;
	}

	/**
	 * @param idFilm the idFilm to set
	 */
	public void setIdFilm(Long idFilm) {
		this.idFilm = idFilm;
	}

	/**
	 * @return the titleFilm
	 */
	public String getTitleFilm() {
		return titleFilm;
	}

	/**
	 * @param titleFilm the titleFilm to set
	 */
	public void setTitleFilm(String titleFilm) {
		this.titleFilm = titleFilm;
	}

	/**
	 * @return the officialTitleFilm
	 */
	public String getOfficialTitleFilm() {
		return officialTitleFilm;
	}

	/**
	 * @param officialTitleFilm the officialTitleFilm to set
	 */
	public void setOfficialTitleFilm(String officialTitleFilm) {
		this.officialTitleFilm = officialTitleFilm;
	}

	/**
	 * @return the originalTitleFilm
	 */
	public String getOriginalTitleFilm() {
		return originalTitleFilm;
	}

	/**
	 * @param originalTitleFilm the originalTitleFilm to set
	 */
	public void setOriginalTitleFilm(String originalTitleFilm) {
		this.originalTitleFilm = originalTitleFilm;
	}

	/**
	 * @return the idImdb
	 */
	public String getIdImdb() {
		return idImdb;
	}

	/**
	 * @param idImdb the idImdb to set
	 */
	public void setIdImdb(String idImdb) {
		this.idImdb = idImdb;
	}

	/**
	 * @return the yearFilm
	 */
	public String getYearFilm() {
		return yearFilm;
	}

	/**
	 * @param yearFilm the yearFilm to set
	 */
	public void setYearFilm(String yearFilm) {
		this.yearFilm = yearFilm;
	}

	/**
	 * @return the runtimeFilm
	 */
	public Integer getRuntimeFilm() {
		return runtimeFilm;
	}

	/**
	 * @param runtimeFilm the runtimeFilm to set
	 */
	public void setRuntimeFilm(Integer runtimeFilm) {
		this.runtimeFilm = runtimeFilm;
	}

	/**
	 * @return the directorFilm
	 */
	public String getDirectorFilm() {
		return directorFilm;
	}

	/**
	 * @param directorFilm the directorFilm to set
	 */
	public void setDirectorFilm(String directorFilm) {
		this.directorFilm = directorFilm;
	}

	/**
	 * @return the writerFilm
	 */
	public String getWriterFilm() {
		return writerFilm;
	}

	/**
	 * @param writerFilm the writerFilm to set
	 */
	public void setWriterFilm(String writerFilm) {
		this.writerFilm = writerFilm;
	}

	/**
	 * @return the actorsFilm
	 */
	public List<String> getActorsFilm() {
		return actorsFilm;
	}

	/**
	 * @param actors the actorsFilm to set
	 */
	public void setActorsFilm(List<String> actors) {
		this.actorsFilm = actors;
	}
	

	/**
	 * @param e
	 * @see java.util.ArrayList#add(java.lang.Object)
	 */
	public void addActor(String actor) {
		actorsFilm.add(actor);
	}

	/**
	 * @return the synopsisFilm
	 */
	public String getSynopsisFilm() {
		return synopsisFilm;
	}

	/**
	 * @param synopsisFilm the synopsisFilm to set
	 */
	public void setSynopsisFilm(String synopsisFilm) {
		this.synopsisFilm = synopsisFilm;
	}

	/**
	 * @return the countryFilm
	 */
	public String getCountryFilm() {
		return countryFilm;
	}

	/**
	 * @param countryFilm the countryFilm to set
	 */
	public void setCountryFilm(String countryFilm) {
		this.countryFilm = countryFilm;
	}

	/**
	 * @return the awardsFilms
	 */
	public String getAwardsFilms() {
		return awardsFilms;
	}

	/**
	 * @param awardsFilms the awardsFilms to set
	 */
	public void setAwardsFilms(String awardsFilms) {
		this.awardsFilms = awardsFilms;
	}

	/**
	 * @return the posterFilm
	 */
	public String getPosterFilm() {
		return posterFilm;
	}

	/**
	 * @param posterFilm the posterFilm to set
	 */
	public void setPosterFilm(String posterFilm) {
		this.posterFilm = posterFilm;
	}

	/**
	 * @return the tmdbRatingFilm
	 */
	public Long getTmdbRatingFilm() {
		return tmdbRatingFilm;
	}

	/**
	 * @param tmdbRatingFilm the tmdbRatingFilm to set
	 */
	public void setTmdbRatingFilm(Long tmdbRatingFilm) {
		this.tmdbRatingFilm = tmdbRatingFilm;
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
	 * @return the originalLanguage
	 */
	public String getOriginalLanguage() {
		return originalLanguage;
	}

	/**
	 * @param originalLanguage the originalLanguage to set
	 */
	public void setOriginalLanguage(String originalLanguage) {
		this.originalLanguage = originalLanguage;
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
	 * @return the productionFilm
	 */
	public String getProductionFilm() {
		return productionFilm;
	}

	/**
	 * @param productionFilm the productionFilm to set
	 */
	public void setProductionFilm(String productionFilm) {
		this.productionFilm = productionFilm;
	}

	/**
	 * @return the languagesFilm
	 */
	public String getLanguagesFilm() {
		return languagesFilm;
	}

	/**
	 * @param languagesFilm the languagesFilm to set
	 */
	public void setLanguagesFilm(String languagesFilm) {
		this.languagesFilm = languagesFilm;
	}

	/**
	 * @return the distanceTitleToOfficialTitle
	 */
	public Integer getDistanceTitleToOfficialTitle() {
		return distanceTitleToOfficialTitle;
	}

	/**
	 * @param distanceTitleToOfficialTitle the distanceTitleToOfficialTitle to set
	 */
	public void setDistanceTitleToOfficialTitle(Integer distanceTitleToOfficialTitle) {
		this.distanceTitleToOfficialTitle = distanceTitleToOfficialTitle;
	}

	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * @param path the path to set
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * @return the size
	 */
	public double getSize() {
		return size;
	}

	/**
	 * @param size the size to set
	 */
	public void setSize(double size) {
		this.size = size;
	}

	/**
	 * @return the vote_count
	 */
	public Integer getVoteCount() {
		return voteCount;
	}

	/**
	 * @param vote_count the vote_count to set
	 */
	public void setVoteCount(Integer voteCount) {
		this.voteCount = voteCount;
	}

	/**
	 * @return the genres
	 */
	public List<Genre> getGenres() {
		return genres;
	}

	/**
	 * @param genres the genres to set
	 */
	public void setGenres(List<Genre> genres) {
		this.genres = genres;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @param e
	 * @return
	 * @see java.util.List#add(java.lang.Object)
	 */
	public Boolean add(Genre e) {
		return genres.add(e);
	}

	/**
	 * @return the seen
	 */
	public Boolean isSeen() {
		return seen;
	}

	/**
	 * @param seen the seen to set
	 */
	public void setSeen(Boolean seen) {
		this.seen = seen;
	}
	
	/**
	 * @return the seen
	 */
	public Boolean getSeen() {
		return seen;
	}

	/**
	 * @return the posterPath
	 */
	public String getPosterPath() {
		return posterPath;
	}

	/**
	 * @param posterPath the posterPath to set
	 */
	public void setPosterPath(String posterPath) {
		this.posterPath = posterPath;
	}

	@Override
	public String toString() {
		return "Film [titleFilm=" + titleFilm + "]";
	}
	
	


}
