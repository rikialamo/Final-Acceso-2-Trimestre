package com.vedruna.webapp.service;

import java.util.List;
import java.util.Optional;

import com.vedruna.webapp.exceptions.UsuarioNotFoundException;
import com.vedruna.webapp.persistence.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vedruna.webapp.dto.PublicacionResponseDto;
import com.vedruna.webapp.dto.UsuarioResponseDto;
import com.vedruna.webapp.dto.UsuarioRequestDto;
import com.vedruna.webapp.persistence.repository.UsuarioRepository;

/**
 * Servicio que gestiona operaciones relacionadas con los usuarios.
 */

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository userRepository;

	/**
	 * Obtiene un usuario por su identificador en formato DTO.
	 *
	 * @param id Identificador del usuario.
	 * @return DTO del usuario.
	 * @throws UsuarioNotFoundException si no se encuentra el usuario.
	 */
	public UsuarioResponseDto findById(long id) {
		Optional<UsuarioResponseDto> optionalUserGetDto = userRepository.findUserGetDtoById(id);
		if (optionalUserGetDto.isPresent()) {
			return optionalUserGetDto.get();
		} else {
			throw new UsuarioNotFoundException(id);
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
	 * @throws UsuarioNotFoundException si no se encuentra el usuario.
	 */
	public UsuarioResponseDto findByUsername(String username) {
		Optional<UsuarioResponseDto> optionalUserGetDto = userRepository.findUserGetDtoByUsername(username);
		if (optionalUserGetDto.isPresent()) {
			return optionalUserGetDto.get();
		} else {
			throw new UsuarioNotFoundException(username);
		}
	}

	/**
	 * Inserta un nuevo usuario.
	 *
	 * @param userPostDTO DTO con la información del nuevo usuario.
	 */
	public void insert(UsuarioRequestDto userPostDTO) {
		Usuario user = new Usuario(userPostDTO.getUserName(), userPostDTO.getEmail(), userPostDTO.getPassword(),
				userPostDTO.getDescription(), userPostDTO.getCreationDate());
		userRepository.save(user);
	}

	/**
	 * Establece la descripción de un usuario por su identificador.
	 *
	 * @param id          Identificador del usuario.
	 * @param description Nueva descripción del usuario.
	 * @throws UsuarioNotFoundException si no se encuentra el usuario.
	 */
	public void setDescriptionById(Long id, String description) {
		Optional<Usuario> optionalUser = userRepository.findById(id);
		if (optionalUser.isPresent()) {
			Usuario user = optionalUser.get();
			user.setDescription(description);
			userRepository.save(user);
		} else {
			throw new UsuarioNotFoundException(id);
		}
	}

	/**
	 * Obtiene todos los usuarios en formato DTO.
	 *
	 * @return Lista de DTO de usuarios.
	 */
	public List<UsuarioResponseDto> findAll() {
		return userRepository.findAllUserGetDto();
	}

	/**
	 * Obtiene los usuarios seguidores de un usuario por su identificador.
	 *
	 * @param id Identificador del usuario.
	 * @return Lista de DTO de usuarios seguidores.
	 */
	public List<UsuarioResponseDto> findFollowerPeopleById(Long id) {
		return userRepository.findFollowerPeopleGetDtoById(id);
	}

	/**
	 * Obtiene los usuarios seguidos por un usuario por su identificador.
	 *
	 * @param id Identificador del usuario.
	 * @return Lista de DTO de usuarios seguidos.
	 */
	public List<UsuarioResponseDto> findFollowedPeopleById(Long id) {
		return userRepository.findFollowedPeopleGetDtoById(id);
	}

	/**
	 * Obtiene las publicaciones de un usuario por su identificador.
	 *
	 * @param id Identificador del usuario.
	 * @return Lista de DTO de publicaciones del usuario.
	 */
	public List<PublicacionResponseDto> findPublicationsById(Long id) {
		return userRepository.findPublicationsGetDtoById(id);
	}

	/**
	 * Obtiene las publicaciones de los usuarios seguidos por un usuario por su
	 * identificador.
	 *
	 * @param id Identificador del usuario.
	 * @return Lista de DTO de publicaciones de los usuarios seguidos.
	 */
	public List<PublicacionResponseDto> findFollowedPeoplePublicationsById(Long id) {
		return userRepository.findFollowedPeoplePublicationsGetDtoById(id);
	}

}
