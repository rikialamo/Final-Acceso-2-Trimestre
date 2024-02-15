package com.vedruna.webapp.service;

import java.util.List;
import java.util.Optional;

import com.vedruna.webapp.exceptions.PublicationIncorrectAuthorException;
import com.vedruna.webapp.persistence.model.Publication;
import com.vedruna.webapp.persistence.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vedruna.webapp.dto.PublicationRequestDto;
import com.vedruna.webapp.dto.PublicationResponseDto;
import com.vedruna.webapp.exceptions.PublicationNotFoundException;
import com.vedruna.webapp.persistence.repository.PublicationRepository;
import com.vedruna.webapp.persistence.repository.UserRepository;

/**
 * Servicio que gestiona operaciones relacionadas con las publicaciones.
 */
@Service
public class PublicationService {

	@Autowired
	private PublicationRepository publicationRepository;

	@Autowired
	private UserRepository userRepository;

	/**
	 * Obtiene todas las publicaciones en formato DTO.
	 *
	 * @return Lista de DTO de publicaciones.
	 */
	public List<PublicationResponseDto> findAll() {
		return publicationRepository.findAllPublicationsResponseDto();
	}

	/**
	 * Obtiene una publicación por su identificador en formato DTO.
	 *
	 * @param id Identificador de la publicación.
	 * @return DTO de la publicación.
	 * @throws PublicationNotFoundException si no se encuentra la publicación.
	 */
	public PublicationResponseDto findById(Long id) {
		Optional<PublicationResponseDto> optionalPublicationGetDto = publicationRepository
				.findPublicationResponseDtoById(id);
		if (optionalPublicationGetDto.isPresent()) {
			return optionalPublicationGetDto.get();
		} else {
			throw new PublicationNotFoundException(id);
		}
	}

	/**
	 * Elimina una publicación por su identificador.
	 *
	 * @param id Identificador de la publicación.
	 */
	public void deleteById(Long id) {
		Optional<Publication> optionalPublication = publicationRepository.findById(id);
		if (optionalPublication.isPresent()) {
			Publication publication = optionalPublication.get();
			User user = publication.getUser();
			user.removePublication(publication);
			publicationRepository.deleteById(id);
		} else {
			throw new PublicationNotFoundException(id);
		}
	}

	/**
	 * Inserta una nueva publicación.
	 *
	 * @param publicationPostDto DTO con la información de la nueva publicación.
	 * @throws PublicationNotFoundException si no se encuentra el usuario asociado a
	 *                                      la publicación.
	 */
	public void insert(PublicationRequestDto publicationRequestDto) {
		Long id = publicationRequestDto.getUserId();
		Optional<User> optionalUser = userRepository.findById(id);
		if (optionalUser.isPresent()) {
			User user = optionalUser.get();
			Publication publication = new Publication(user, publicationRequestDto.getText(),
					publicationRequestDto.getCreationDate(), publicationRequestDto.getEditionDate());
			publicationRepository.save(publication);
		} else {
			throw new PublicationNotFoundException(id);
		}
	}

	/**
	 * Actualiza una publicación existente.
	 *
	 * @param publicationPutDto DTO con la información actualizada de la
	 *                          publicación.
	 * @throws PublicationNotFoundException        si no se encuentra la
	 *                                             publicación.
	 * @throws PublicationIncorrectAuthorException si el autor de la publicación no
	 *                                             coincide.
	 */
	public void update(long id, PublicationRequestDto publicationRequestDto) {
		Optional<Publication> optionalPublication = publicationRepository.findById(id);
		if (optionalPublication.isPresent()) {
			Publication publication = optionalPublication.get();
			if (publication.getUser().getId().equals(publicationRequestDto.getUserId())) {
				publication.setText(publicationRequestDto.getText());
				publication.setCreationDate(publicationRequestDto.getCreationDate());
				publication.setEditionDate(publicationRequestDto.getEditionDate());
				publicationRepository.save(publication);
			} else {
				throw new PublicationIncorrectAuthorException(id);
			}
		} else {
			throw new PublicationNotFoundException(id);
		}
	}

}
