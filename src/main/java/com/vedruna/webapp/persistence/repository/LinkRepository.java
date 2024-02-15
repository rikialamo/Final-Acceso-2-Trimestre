package com.vedruna.webapp.persistence.repository;

import java.util.Optional;

import com.vedruna.webapp.persistence.model.Link;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Repositorio de JPA para la entidad Link, que gestiona las operaciones de base
 * de datos relacionadas con las relaciones de seguimiento.
 */
public interface LinkRepository extends JpaRepository<Link, Long> {

	/**
	 * Consulta personalizada para buscar una relación de seguimiento por el
	 * identificador del usuario seguido y del usuario seguidor.
	 *
	 * @param followedId Identificador del usuario seguido.
	 * @param followerId Identificador del usuario seguidor.
	 * @return Un Optional que puede contener la relación de seguimiento si existe.
	 */
	@Query("SELECT k FROM Link k JOIN k.followed d JOIN k.follower r WHERE d.id =:followedId AND r.id =:followerId")
	Optional<Link> findByFollowedIdFollowerId(Long followedId, Long followerId);

}
