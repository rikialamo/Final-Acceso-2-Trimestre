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
 * Clase de transferencia de datos (DTO) para la creación de nuevos usuarios.
 * Contiene información necesaria para crear un nuevo usuario.
 */
public class UserRequestDto {

	/**
	 * Nombre de usuario del nuevo usuario.
	 */
	private String userName;

	/**
	 * Contraseña del nuevo usuario.
	 */
	private String password;

	/**
	 * Correo electrónico del nuevo usuario.
	 */
	private String email;

	/**
	 * Descripción del nuevo usuario.
	 */
	private String description;

	/**
	 * Fecha de creación del nuevo usuario.
	 */
	private String creationDate;

}
