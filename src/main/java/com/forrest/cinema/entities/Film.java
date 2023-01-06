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
	
	private String titleFilm;
	private String officialTitleFilm;
	private String originalTitleFilm;
	private String idImdb;
	private String yearFilm;
	private int runtimeFilm;
	private String directorFilm;
	private String writerFilm;
	private ArrayList<String> actorsFilm;
	private String synopsisFilm;
	private String countryFilm;
	private String awardsFilms;
	private String posterFilm;
	private String imdbRatingFilm;
	private String typeFilm;
	private String boxOfficeFilm;
	private String productionFilm;
	private String languagesFilm;
	private int distanceTitleToOfficialTitle;
	private String path;
	private double size;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(
			name = "genre_film",
			joinColumns = @JoinColumn(name = "id_film"),
			inverseJoinColumns = @JoinColumn(name = "id_genre")
	)
	private List<Genre> genres = new ArrayList<>();

	/**
	 * 
	 * @see java.util.List#clear()
	 */
	public void clear() {
		genres.clear();
	}

	public Film() {
		super();
	}
	
	public Film(String titleFilm) {
		super();
		this.titleFilm = titleFilm;
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
	public int getRuntimeFilm() {
		return runtimeFilm;
	}

	/**
	 * @param runtimeFilm the runtimeFilm to set
	 */
	public void setRuntimeFilm(int runtimeFilm) {
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
	public ArrayList<String> getActorsFilm() {
		return actorsFilm;
	}

	/**
	 * @param actorsFilm the actorsFilm to set
	 */
	public void setActorsFilm(ArrayList<String> actorsFilm) {
		this.actorsFilm = actorsFilm;
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
	 * @return the imdbRatingFilm
	 */
	public String getImdbRatingFilm() {
		return imdbRatingFilm;
	}

	/**
	 * @param imdbRatingFilm the imdbRatingFilm to set
	 */
	public void setImdbRatingFilm(String imdbRatingFilm) {
		this.imdbRatingFilm = imdbRatingFilm;
	}

	/**
	 * @return the typeFilm
	 */
	public String getTypeFilm() {
		return typeFilm;
	}

	/**
	 * @param typeFilm the typeFilm to set
	 */
	public void setTypeFilm(String typeFilm) {
		this.typeFilm = typeFilm;
	}

	/**
	 * @return the boxOfficeFilm
	 */
	public String getBoxOfficeFilm() {
		return boxOfficeFilm;
	}

	/**
	 * @param boxOfficeFilm the boxOfficeFilm to set
	 */
	public void setBoxOfficeFilm(String boxOfficeFilm) {
		this.boxOfficeFilm = boxOfficeFilm;
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
	 * @param e
	 * @return
	 * @see java.util.ArrayList#add(java.lang.Object)
	 */
	public boolean add(String e) {
		return actorsFilm.add(e);
	}

	/**
	 * @param e
	 * @return
	 * @see java.util.List#add(java.lang.Object)
	 */
	public boolean add(Genre e) {
		return genres.add(e);
	}

	/**
	 * @return the distanceTitleToOfficialTitle
	 */
	public int getDistanceTitleToOfficialTitle() {
		return distanceTitleToOfficialTitle;
	}

	/**
	 * @param distanceTitleToOfficialTitle the distanceTitleToOfficialTitle to set
	 */
	public void setDistanceTitleToOfficialTitle(int distanceTitleToOfficialTitle) {
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
	
	
}
