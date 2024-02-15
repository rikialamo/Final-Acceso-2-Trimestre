package com.vedruna.webapp.exceptions;

/**
 * Excepción personalizada para indicar que un usuario no se ha encontrado. Se
 * lanza cuando se intenta acceder a un usuario que no existe en la base de
 * datos.
 */
public class UserNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor que acepta el identificador del usuario no encontrado.
	 *
	 * @param id El identificador del usuario no encontrado.
	 */
	public UserNotFoundException(long id) {
		super("Usuario no encontrado: id = " + id);
	}

	/**
	 * Constructor que acepta el nombre de usuario del usuario no encontrado.
	 *
	 * @param name El nombre de usuario del usuario no encontrado.
	 */
	public UserNotFoundException(String name) {
		super("Usuario no encontrado: username = " + name);
	}
}
