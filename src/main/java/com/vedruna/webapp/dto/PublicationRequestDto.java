package com.vedruna.webapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

/**
 * Clase de transferencia de datos (DTO) para la creación de nuevas
 * publicaciones. Contiene información necesaria para crear una nueva
 * publicación.
 */
public class PublicationRequestDto {

	/**
	 * Identificador único del usuario que realiza la publicación.
	 */
	private long userId;

	/**
	 * Texto de la publicación.
	 */
	private String text;

	/**
	 * Fecha de creación de la publicación.
	 */
	private String creationDate;

	/**
	 * Fecha de edición de la publicación.
	 */
	private String editionDate;

}
