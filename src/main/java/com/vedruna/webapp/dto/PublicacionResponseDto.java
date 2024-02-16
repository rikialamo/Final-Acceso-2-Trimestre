package com.vedruna.webapp.dto;

/**
 * Interfaz de transferencia de datos (DTO) para obtener información de
 * publicaciones. Define métodos para acceder a los atributos de una
 * publicación.
 */
public interface PublicacionResponseDto {

	/**
	 * Obtiene el identificador único de la publicación.
	 *
	 * @return El identificador único de la publicación.
	 */
	Long getId();

	/**
	 * Obtiene el identificador único del usuario que realizó la publicación.
	 *
	 * @return El identificador único del usuario.
	 */
	Long getUserId();

	/**
	 * Obtiene el nombre de usuario del usuario que realizó la publicación.
	 *
	 * @return El nombre de usuario del usuario.
	 */
	String getUserUsername();

	/**
	 * Obtiene el texto de la publicación.
	 *
	 * @return El texto de la publicación.
	 */
	String getText();

	/**
	 * Obtiene la fecha de creación de la publicación.
	 *
	 * @return La fecha de creación de la publicación.
	 */
	String getCreationDate();

	/**
	 * Obtiene la fecha de edición de la publicación.
	 *
	 * @return La fecha de edición de la publicación.
	 */
	String getEditionDate();
}
