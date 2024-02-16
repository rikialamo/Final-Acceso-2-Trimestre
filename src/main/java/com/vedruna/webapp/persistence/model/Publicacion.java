package com.vedruna.webapp.persistence.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Clase que representa una publicación en la base de datos. Almacena
 * información sobre la publicación, como el usuario que la realizó, el texto, y
 * las fechas de creación y edición.
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "publications")
public class Publicacion {

	/**
	 * Identificador único de la publicación.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * Usuario que realizó la publicación.
	 */
	@ManyToOne
	@JoinColumn(name = "user")
	private Usuario user;

	/**
	 * Texto de la publicación.
	 */
	@Column(name = "text")
	private String text;

	/**
	 * Fecha de creación de la publicación.
	 */
	@Column(name = "creationDate")
	private String creationDate;

	/**
	 * Fecha de edición de la publicación.
	 */
	@Column(name = "editionDate")
	private String editionDate;

	/**
	 * Constructor que acepta el usuario, el texto, y las fechas para crear una
	 * nueva publicación.
	 *
	 * @param user         Usuario que realizó la publicación.
	 * @param text         Texto de la publicación.
	 * @param creationDate Fecha de creación de la publicación.
	 * @param editionDate  Fecha de edición de la publicación.
	 */
	public Publicacion(Usuario user, String text, String creationDate, String editionDate) {
		this.user = user;
		this.text = text;
		this.creationDate = creationDate;
		this.editionDate = editionDate;
	}
}
