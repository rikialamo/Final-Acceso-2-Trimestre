package com.vedruna.webapp.controller;

import java.util.List;

import com.vedruna.webapp.dto.PublicationRequestDto;
import com.vedruna.webapp.service.PublicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.vedruna.webapp.dto.PublicationResponseDto;

@RequestMapping("/api/publication")
@RestController
@CrossOrigin

/**
 * Controlador para gestionar operaciones relacionadas con las publicaciones en
 * la API.
 */
public class PublicationController {

	@Autowired
	private PublicationService publicationService;

	/**
	 * Recupera una publicación por su identificador.
	 *
	 * @param id El identificador de la publicación.
	 * @return La publicación correspondiente al identificador.
	 */
	@GetMapping("/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public PublicationResponseDto findById(@PathVariable long id) {
		return publicationService.findById(id);
	}

	/**
	 * Elimina una publicación por su identificador.
	 *
	 * @param id El identificador de la publicación a eliminar.
	 */
	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public void deleteById(@PathVariable long id) {
		publicationService.deleteById(id);
	}

	/**
	 * Crea una nueva publicación.
	 *
	 * @param publicationPostDto DTO que contiene la información para crear la nueva
	 *                           publicación.
	 */
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public void insert(@RequestBody PublicationRequestDto publicationRequestDto) {
		publicationService.insert(publicationRequestDto);
	}

	/**
	 * Actualiza una publicación existente.
	 *
	 * @param publicationPutDto DTO que contiene la información para actualizar la
	 *                          publicación.
	 */
	@PutMapping("/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public void update(@PathVariable long id, @RequestBody PublicationRequestDto publicationRequestDto) {
		publicationService.update(id, publicationRequestDto);
	}

	/**
	 * Recupera todas las publicaciones disponibles.
	 *
	 * @return Lista de DTOs que representan todas las publicaciones.
	 */
	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public List<PublicationResponseDto> getAll() {
		return publicationService.findAll();
	}

}
