package com.vedruna.webapp.dto;

/**
 * Interfaz de transferencia de datos (DTO) para obtener información de
 * usuarios. Define métodos para acceder a los atributos esenciales de un
 * usuario.
 */
public interface UsuarioResponseDto {

	/**
	 * Obtiene el identificador único del usuario.
	 *
	 * @return El identificador único del usuario.
	 */
	Long getId();

	/**
	 * Obtiene el nombre de usuario del usuario.
	 *
	 * @return El nombre de usuario del usuario.
	 */
	String getUsername();

	/**
	 * Obtiene el correo electrónico del usuario.
	 *
	 * @return El correo electrónico del usuario.
	 */
	String getEmail();

	/**
	 * Obtiene la descripción del usuario.
	 *
	 * @return La descripción del usuario.
	 */
	String getDescription();

	/**
	 * Obtiene la fecha de creación del usuario.
	 *
	 * @return La fecha de creación del usuario.
	 */
	String getCreationDate();
}
