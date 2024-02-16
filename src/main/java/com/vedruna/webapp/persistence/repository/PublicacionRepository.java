package com.vedruna.webapp.persistence.repository;

import java.util.List;
import java.util.Optional;

import com.vedruna.webapp.dto.PublicacionResponseDto;
import com.vedruna.webapp.persistence.model.Publicacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Repositorio de JPA para la entidad Publicacion, que gestiona las operaciones
 * de base de datos relacionadas con las publicaciones.
 */
public interface PublicacionRepository extends JpaRepository<Publicacion, Long> {

	/**
	 * Consulta personalizada para obtener una publicación por su identificador.
	 *
	 * @param id Identificador de la publicación.
	 * @return Un Optional que puede contener la publicación si existe.
	 */
	@Query("SELECT p FROM Publicacion p WHERE p.id =:id")
	Optional<Publicacion> findById(Long id);

	/**
	 * Consulta personalizada para obtener todos los DTO de publicaciones.
	 *
	 * @return Lista de DTO de publicaciones.
	 */
	@Query("SELECT p.id as id, p.user.id as userId, p.text as text,"
			+ " p.creationDate as creationDate, p.editionDate as editionDate," + " p.user.username as userUsername"
			+ " FROM Publicacion p JOIN p.user")
	List<PublicacionResponseDto> findAllPublicationsResponseDto();

	/**
	 * Consulta personalizada para obtener un DTO de publicación por su
	 * identificador.
	 *
	 * @param id Identificador de la publicación.
	 * @return Un Optional que puede contener el DTO de la publicación si existe.
	 */
	@Query("SELECT p.id as id, p.user.id as userId, p.text as text,"
			+ " p.creationDate as creationDate, p.editionDate as editionDate," + " p.user.username as userUsername"
			+ " FROM Publicacion p JOIN p.user " + " WHERE p.id =:id")
	Optional<PublicacionResponseDto> findPublicationResponseDtoById(Long id);
}
