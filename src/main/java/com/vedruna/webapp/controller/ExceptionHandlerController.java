package com.vedruna.webapp.controller;

import com.vedruna.webapp.exceptions.PublicacionNotFoundException;
import com.vedruna.webapp.exceptions.UsuarioNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

/**
 * Clase de controlador de consejos para manejar excepciones globalmente en la
 * API. Proporciona métodos para manejar excepciones específicas y devolver
 * respuestas apropiadas.
 */
@RestControllerAdvice
public class ExceptionHandlerController {

	/**
	 * Maneja el caso en el que la solicitud contiene JSON malformado.
	 *
	 * @param e La excepción lanzada (HttpMessageNotReadableException).
	 * @return Mensaje de error que indica malformación JSON.
	 */
	@ExceptionHandler(HttpMessageNotReadableException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String handleHttpMessageNotReadableException(Exception e) {
		return "JSON Mal formado: " + e.getMessage();
	}

	/**
	 * Maneja el caso en el que se produce una incompatibilidad en el tipo de
	 * argumento del método.
	 *
	 * @param e La excepción lanzada (MethodArgumentTypeMismatchException).
	 * @return Mensaje de error que indica una incompatibilidad en el tipo de
	 *         argumento del método.
	 */
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String handleMethodArgumentTypeMismatchException(Exception e) {
		return "Tipo del argumento del método erróneo: " + e.getMessage();
	}

	/**
	 * Maneja el caso en el que no se encuentra un usuario.
	 *
	 * @param e La excepción lanzada (UsuarioNotFoundException).
	 * @return Mensaje de error que indica que no se encontró al usuario.
	 */
	@ExceptionHandler(UsuarioNotFoundException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String handleUserNotFoundException(Exception e) {
		return e.getMessage();
	}

	/**
	 * Maneja el caso en el que no se encuentra una publicación.
	 *
	 * @param e La excepción lanzada (UsuarioNotFoundException).
	 * @return Mensaje de error que indica que no se encontró al usuario.
	 */
	@ExceptionHandler(PublicacionNotFoundException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String handlePublicationNotFoundException(Exception e) {
		return e.getMessage();
	}

	/**
	 * Maneja el caso en el que se produce una violación de integridad de datos (por
	 * ejemplo, violación de restricción única).
	 *
	 * @param e La excepción lanzada (DataIntegrityViolationException).
	 * @return Mensaje de error que indica la violación de integridad de datos.
	 */
	@ExceptionHandler(DataIntegrityViolationException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public String handleDataIntegrityViolationException(Exception e) {
		return e.getMessage();
	}

	/**
	 * Maneja excepciones genéricas que no son capturadas específicamente por otros
	 * métodos.
	 *
	 * @param e La excepción genérica.
	 * @return Mensaje de error genérico.
	 */
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public String handleException(Exception e) {
		return "Genérico: " + e;
	}

}
