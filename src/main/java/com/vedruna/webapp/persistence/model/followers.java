package com.vedruna.webapp.persistence.model;

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
 * Clase que representa la relación de seguimiento entre usuarios en la base de
 * datos. Se utiliza para almacenar información sobre quién sigue a quién.
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "followers")
public class followers {

	/**
	 * Identificador único de la relación de seguimiento.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	/**
	 * Usuario que es seguido en la relación(muchos a uno).
	 */
	@ManyToOne
	@JoinColumn(name = "followed")
	private User followed;

	/**
	 * Usuario que sigue en la relación(muchos a uno).
	 */
	@ManyToOne
	@JoinColumn(name = "follower")
	private User follower;

	/**
	 * Constructor que acepta el usuario seguido y el usuario seguidor para crear
	 * una nueva relación de seguimiento.
	 *
	 * @param followed Usuario que es seguido en la relación.
	 * @param follower Usuario que sigue en la relación.
	 */
	public followers(User followed, User follower) {
		this.followed = followed;
		this.follower = follower;
	}
}
