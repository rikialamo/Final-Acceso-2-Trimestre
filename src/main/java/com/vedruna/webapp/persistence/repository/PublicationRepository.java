package com.vedruna.webapp.persistence.repository;

import java.util.List;
import java.util.Optional;

import com.vedruna.webapp.dto.PublicationResponseDto;
import com.vedruna.webapp.persistence.model.Publication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Repositorio de JPA para la entidad Publication, que gestiona las operaciones
 * de base de datos relacionadas con las publicaciones.
 */
public interface PublicationRepository extends JpaRepository<Publication, Long> {

	/**
	 * Consulta personalizada para obtener una publicación por su identificador.
	 *
	 * @param id Identificador de la publicación.
	 * @return Un Optional que puede contener la publicación si existe.
	 */
	@Query("SELECT p FROM Publication p WHERE p.id =:id")
	Optional<Publication> findById(Long id);

	/**
	 * Consulta personalizada para obtener todos los DTO de publicaciones.
	 *
	 * @return Lista de DTO de publicaciones.
	 */
	@Query("SELECT p.id as id, p.user.id as userId, p.text as text,"
			+ " p.creationDate as creationDate, p.editionDate as editionDate," + " p.user.username as userUsername"
			+ " FROM Publication p JOIN p.user")
	List<PublicationResponseDto> findAllPublicationsResponseDto();

	/**
	 * Consulta personalizada para obtener un DTO de publicación por su
	 * identificador.
	 *
	 * @param id Identificador de la publicación.
	 * @return Un Optional que puede contener el DTO de la publicación si existe.
	 */
	@Query("SELECT p.id as id, p.user.id as userId, p.text as text,"
			+ " p.creationDate as creationDate, p.editionDate as editionDate," + " p.user.username as userUsername"
			+ " FROM Publication p JOIN p.user " + " WHERE p.id =:id")
	Optional<PublicationResponseDto> findPublicationResponseDtoById(Long id);
}
