package com.vedruna.webapp.exceptions;

/**
 * Excepción personalizada para indicar que una publicación no se ha encontrado.
 * Se lanza cuando se intenta acceder a una publicación que no existe en la base
 * de datos.
 */
public class PublicationNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor que acepta el identificador de la publicación no encontrada.
	 *
	 * @param id El identificador de la publicación no encontrada.
	 */
	public PublicationNotFoundException(long id) {
		super("Publicación no encontrada: id = " + id);
	}
}
