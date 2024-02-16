package com.vedruna.webapp.service;

import java.util.Optional;

import com.vedruna.webapp.persistence.model.Usuario;
import com.vedruna.webapp.persistence.model.followers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vedruna.webapp.persistence.repository.FollowersRepository;

/**
 * Servicio que gestiona operaciones relacionadas con las relaciones de
 * seguimiento (followers).
 */
@Service
public class FollowersService {

	@Autowired
	private FollowersRepository linkRepository;

	/**
	 * Agrega una relación de seguimiento entre un usuario seguido y un usuario
	 * seguidor.
	 *
	 * @param followed Usuario seguido.
	 * @param follower Usuario seguidor.
	 */
	public void add(Usuario followed, Usuario follower) {
		// Verifica si la relación ya existe
		Optional<followers> optionaLink = linkRepository.findByFollowedIdFollowerId(followed.getId(), follower.getId());

		// Si la relación no existe, la crea y la guarda en el repositorio
		if (!optionaLink.isPresent()) {
			followers link = new followers(followed, follower);
			followed.getLinks().add(link);
			linkRepository.save(link);
		}
	}

}
