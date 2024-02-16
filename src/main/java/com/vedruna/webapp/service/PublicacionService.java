package com.vedruna.webapp.service;

import java.util.List;
import java.util.Optional;

import com.vedruna.webapp.exceptions.PublicacionIncorrectAuthorException;
import com.vedruna.webapp.persistence.model.Publicacion;
import com.vedruna.webapp.persistence.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vedruna.webapp.dto.PublicacionRequestDto;
import com.vedruna.webapp.dto.PublicacionResponseDto;
import com.vedruna.webapp.exceptions.PublicacionNotFoundException;
import com.vedruna.webapp.persistence.repository.PublicacionRepository;
import com.vedruna.webapp.persistence.repository.UsuarioRepository;

/**
 * Servicio que gestiona operaciones relacionadas con las publicaciones.
 */
@Service
public class PublicacionService {

	@Autowired
	private PublicacionRepository publicationRepository;

	@Autowired
	private UsuarioRepository userRepository;

	/**
	 * Obtiene todas las publicaciones en formato DTO.
	 *
	 * @return Lista de DTO de publicaciones.
	 */
	public List<PublicacionResponseDto> findAll() {
		return publicationRepository.findAllPublicationsResponseDto();
	}

	/**
	 * Obtiene una publicación por su identificador en formato DTO.
	 *
	 * @param id Identificador de la publicación.
	 * @return DTO de la publicación.
	 * @throws PublicacionNotFoundException si no se encuentra la publicación.
	 */
	public PublicacionResponseDto findById(Long id) {
		Optional<PublicacionResponseDto> optionalPublicationGetDto = publicationRepository
				.findPublicationResponseDtoById(id);
		if (optionalPublicationGetDto.isPresent()) {
			return optionalPublicationGetDto.get();
		} else {
			throw new PublicacionNotFoundException(id);
		}
	}

	/**
	 * Elimina una publicación por su identificador.
	 *
	 * @param id Identificador de la publicación.
	 */
	public void deleteById(Long id) {
		Optional<Publicacion> optionalPublication = publicationRepository.findById(id);
		if (optionalPublication.isPresent()) {
			Publicacion publication = optionalPublication.get();
			Usuario user = publication.getUser();
			user.removePublication(publication);
			publicationRepository.deleteById(id);
		} else {
			throw new PublicacionNotFoundException(id);
		}
	}

	/**
	 * Inserta una nueva publicación.
	 *
	 * @param publicationPostDto DTO con la información de la nueva publicación.
	 * @throws PublicacionNotFoundException si no se encuentra el usuario asociado a
	 *                                      la publicación.
	 */
	public void insert(PublicacionRequestDto publicationRequestDto) {
		Long id = publicationRequestDto.getUserId();
		Optional<Usuario> optionalUser = userRepository.findById(id);
		if (optionalUser.isPresent()) {
			Usuario user = optionalUser.get();
			Publicacion publication = new Publicacion(user, publicationRequestDto.getText(),
					publicationRequestDto.getCreationDate(), publicationRequestDto.getEditionDate());
			publicationRepository.save(publication);
		} else {
			throw new PublicacionNotFoundException(id);
		}
	}

	/**
	 * Actualiza una publicación existente.
	 *
	 * @param publicationPutDto DTO con la información actualizada de la
	 *                          publicación.
	 * @throws PublicacionNotFoundException        si no se encuentra la
	 *                                             publicación.
	 * @throws PublicacionIncorrectAuthorException si el autor de la publicación no
	 *                                             coincide.
	 */
	public void update(long id, PublicacionRequestDto publicationRequestDto) {
		Optional<Publicacion> optionalPublication = publicationRepository.findById(id);
		if (optionalPublication.isPresent()) {
			Publicacion publication = optionalPublication.get();
			if (publication.getUser().getId().equals(publicationRequestDto.getUserId())) {
				publication.setText(publicationRequestDto.getText());
				publication.setCreationDate(publicationRequestDto.getCreationDate());
				publication.setEditionDate(publicationRequestDto.getEditionDate());
				publicationRepository.save(publication);
			} else {
				throw new PublicacionIncorrectAuthorException(id);
			}
		} else {
			throw new PublicacionNotFoundException(id);
		}
	}

}
