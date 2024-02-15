package com.vedruna.webapp.service;

import java.util.Optional;

import com.vedruna.webapp.persistence.model.Link;
import com.vedruna.webapp.persistence.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vedruna.webapp.persistence.repository.LinkRepository;

/**
 * Servicio que gestiona operaciones relacionadas con las relaciones de
 * seguimiento (Link).
 */
@Service
public class LinkService {

	@Autowired
	private LinkRepository linkRepository;

	/**
	 * Agrega una relación de seguimiento entre un usuario seguido y un usuario
	 * seguidor.
	 *
	 * @param followed Usuario seguido.
	 * @param follower Usuario seguidor.
	 */
	public void add(User followed, User follower) {
		// Verifica si la relación ya existe
		Optional<Link> optionaLink = linkRepository.findByFollowedIdFollowerId(followed.getId(), follower.getId());

		// Si la relación no existe, la crea y la guarda en el repositorio
		if (!optionaLink.isPresent()) {
			Link link = new Link(followed, follower);
			followed.getLinks().add(link);
			linkRepository.save(link);
		}
	}

}
