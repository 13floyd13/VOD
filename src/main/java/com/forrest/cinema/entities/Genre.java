/**
 * 
 */
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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

/**
 * @author martin
 *
 */

@Entity
@Table(name = "genre")
public class Genre implements Serializable {
	
	private static final long serialVersionUID = 5594374522174974600L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_genre")
	private Long idGenre;
	private String nameGenre;
	private String descriptionGenre;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(
			name = "genre_film",
			joinColumns = @JoinColumn(name = "id_genre"),
			inverseJoinColumns = @JoinColumn(name = "id_film")
	)
	private List<Film> films = new ArrayList<>();

	
	
	public Genre() {
		super();
	}

	public Genre(String nameGenre) {
		super();
		this.nameGenre = nameGenre;
	}

	/**
	 * @return the idGenre
	 */
	public Long getIdGenre() {
		return idGenre;
	}

	/**
	 * @param idGenre the idGenre to set
	 */
	public void setIdGenre(Long idGenre) {
		this.idGenre = idGenre;
	}

	/**
	 * @return the nameGenre
	 */
	public String getNameGenre() {
		return nameGenre;
	}

	/**
	 * @param nameGenre the nameGenre to set
	 */
	public void setNameGenre(String nameGenre) {
		this.nameGenre = nameGenre;
	}

	/**
	 * @return the descriptionGenre
	 */
	public String getDescriptionGenre() {
		return descriptionGenre;
	}

	/**
	 * @param descriptionGenre the descriptionGenre to set
	 */
	public void setDescriptionGenre(String descriptionGenre) {
		this.descriptionGenre = descriptionGenre;
	}
	
	
	
	

}
