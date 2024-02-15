package com.vedruna.webapp.service;

import java.util.List;
import java.util.Optional;

import com.vedruna.webapp.exceptions.UserNotFoundException;
import com.vedruna.webapp.persistence.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vedruna.webapp.dto.PublicationResponseDto;
import com.vedruna.webapp.dto.UserResponseDto;
import com.vedruna.webapp.dto.UserRequestDto;
import com.vedruna.webapp.persistence.repository.UserRepository;

/**
 * Servicio que gestiona operaciones relacionadas con los usuarios.
 */

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	/**
	 * Obtiene un usuario por su identificador en formato DTO.
	 *
	 * @param id Identificador del usuario.
	 * @return DTO del usuario.
	 * @throws UserNotFoundException si no se encuentra el usuario.
	 */
	public UserResponseDto findById(long id) {
		Optional<UserResponseDto> optionalUserGetDto = userRepository.findUserGetDtoById(id);
		if (optionalUserGetDto.isPresent()) {
			return optionalUserGetDto.get();
		} else {
			throw new UserNotFoundException(id);
		}
	}

	/**
	 * Elimina un usuario por su identificador.
	 *
	 * @param id Identificador del usuario.
	 */
	public void deleteById(Long id) {
		userRepository.deleteById(id);
	}

	/**
	 * Obtiene un usuario por su nombre de usuario en formato DTO.
	 *
	 * @param username Nombre de usuario.
	 * @return DTO del usuario.
	 * @throws UserNotFoundException si no se encuentra el usuario.
	 */
	public UserResponseDto findByUsername(String username) {
		Optional<UserResponseDto> optionalUserGetDto = userRepository.findUserGetDtoByUsername(username);
		if (optionalUserGetDto.isPresent()) {
			return optionalUserGetDto.get();
		} else {
			throw new UserNotFoundException(username);
		}
	}

	/**
	 * Inserta un nuevo usuario.
	 *
	 * @param userPostDTO DTO con la información del nuevo usuario.
	 */
	public void insert(UserRequestDto userPostDTO) {
		User user = new User(userPostDTO.getUserName(), userPostDTO.getEmail(), userPostDTO.getPassword(),
				userPostDTO.getDescription(), userPostDTO.getCreationDate());
		userRepository.save(user);
	}

	/**
	 * Establece la descripción de un usuario por su identificador.
	 *
	 * @param id          Identificador del usuario.
	 * @param description Nueva descripción del usuario.
	 * @throws UserNotFoundException si no se encuentra el usuario.
	 */
	public void setDescriptionById(Long id, String description) {
		Optional<User> optionalUser = userRepository.findById(id);
		if (optionalUser.isPresent()) {
			User user = optionalUser.get();
			user.setDescription(description);
			userRepository.save(user);
		} else {
			throw new UserNotFoundException(id);
		}
	}

	/**
	 * Obtiene todos los usuarios en formato DTO.
	 *
	 * @return Lista de DTO de usuarios.
	 */
	public List<UserResponseDto> findAll() {
		return userRepository.findAllUserGetDto();
	}

	/**
	 * Obtiene los usuarios seguidores de un usuario por su identificador.
	 *
	 * @param id Identificador del usuario.
	 * @return Lista de DTO de usuarios seguidores.
	 */
	public List<UserResponseDto> findFollowerPeopleById(Long id) {
		return userRepository.findFollowerPeopleGetDtoById(id);
	}

	/**
	 * Obtiene los usuarios seguidos por un usuario por su identificador.
	 *
	 * @param id Identificador del usuario.
	 * @return Lista de DTO de usuarios seguidos.
	 */
	public List<UserResponseDto> findFollowedPeopleById(Long id) {
		return userRepository.findFollowedPeopleGetDtoById(id);
	}

	/**
	 * Obtiene las publicaciones de un usuario por su identificador.
	 *
	 * @param id Identificador del usuario.
	 * @return Lista de DTO de publicaciones del usuario.
	 */
	public List<PublicationResponseDto> findPublicationsById(Long id) {
		return userRepository.findPublicationsGetDtoById(id);
	}

	/**
	 * Obtiene las publicaciones de los usuarios seguidos por un usuario por su
	 * identificador.
	 *
	 * @param id Identificador del usuario.
	 * @return Lista de DTO de publicaciones de los usuarios seguidos.
	 */
	public List<PublicationResponseDto> findFollowedPeoplePublicationsById(Long id) {
		return userRepository.findFollowedPeoplePublicationsGetDtoById(id);
	}

}
