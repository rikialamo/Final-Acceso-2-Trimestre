package com.vedruna.webapp.persistence.repository;

import java.util.List;
import java.util.Optional;

import com.vedruna.webapp.dto.PublicacionResponseDto;
import com.vedruna.webapp.dto.UsuarioResponseDto;
import com.vedruna.webapp.persistence.model.Publicacion;
import com.vedruna.webapp.persistence.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Repositorio de JPA para la entidad Usuario, que gestiona las operaciones de base
 * de datos relacionadas con los usuarios.
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	// Métodos de consulta personalizada para obtener información específica de los
	// usuarios y sus relaciones.

	// Consultas relacionadas con los usuarios y sus relaciones

	@Query("SELECT u FROM Usuario u")
	List<Usuario> findAll();

	@Query("SELECT u FROM Usuario u WHERE u.id=:id")
	Optional<Usuario> findById(Long id);

	@Query("SELECT u FROM Usuario u WHERE u.username=:username")
	Optional<Usuario> findByUsername(String username);

	@Query("SELECT k.follower FROM Usuario u JOIN u.links k WHERE u.id=:id")
	List<Usuario> findFollowerPeopleById(Long id);

	@Query("SELECT k.followed FROM Usuario u JOIN u.links k WHERE k.follower.id=:id")
	List<Usuario> findFollowedPeopleById(Long id);

	// Consultas para obtener DTO específicos de los usuarios

	@Query("SELECT u.id as id, u.username as username, u.email as email, u.description as description, u.creationDate as creationDate FROM Usuario u")
	List<UsuarioResponseDto> findAllUserGetDto();

	@Query("SELECT u.id as id, u.username as username, u.email as email, u.description as description, u.creationDate as creationDate FROM Usuario u"
			+ " WHERE u.id=:id")
	Optional<UsuarioResponseDto> findUserGetDtoById(Long id);

	@Query("SELECT u.id as id, u.username as username, u.email as email, u.description as description, u.creationDate as creationDate FROM Usuario u"
			+ " WHERE u.username=:username")
	Optional<UsuarioResponseDto> findUserGetDtoByUsername(String username);

	@Query("SELECT k.follower.id as id, k.follower.username as username, k.follower.email as email, k.follower.description as description, k.follower.creationDate as creationDate"
			+ " FROM Usuario u JOIN u.links k WHERE u.id=:id")
	List<UsuarioResponseDto> findFollowerPeopleGetDtoById(Long id);

	@Query("SELECT k.followed.id as id, k.followed.username as username, k.followed.email as email, k.followed.description as description, k.followed.creationDate as creationDate"
			+ " FROM Usuario u JOIN u.links k WHERE k.follower.id=:id")
	List<UsuarioResponseDto> findFollowedPeopleGetDtoById(Long id);

	// Consultas relacionadas con las publicaciones y las relaciones de seguimiento

	@Query("SELECT p FROM Usuario u JOIN u.publications p WHERE u.id=:id")
	List<Publicacion> findPublicationsById(Long id);

	@Query("SELECT p FROM Usuario u JOIN u.links k JOIN k.followed.publications p WHERE k.follower.id=:id")
	List<Publicacion> findFollowedPeoplePublicationsById(Long id);

	// Consultas para obtener DTO específicos de las publicaciones

	@Query("SELECT p.id as id, p.user.id as userId, p.text as text,"
			+ " p.creationDate as creationDate, p.editionDate as editionDate," + " u.username as userUsername"
			+ " FROM Usuario u JOIN u.publications p" + " WHERE u.id=:id")
	List<PublicacionResponseDto> findPublicationsGetDtoById(Long id);

	@Query("SELECT p.id as id, p.user.id as userId, p.text as text,"
			+ " p.creationDate as creationDate, p.editionDate as editionDate," + " k.followed.username as userUsername"
			+ " FROM Usuario u JOIN u.links k JOIN k.followed.publications p" + " WHERE k.follower.id=:id"
			+ " ORDER BY p.creationDate DESC")
	List<PublicacionResponseDto> findFollowedPeoplePublicationsGetDtoById(Long id);

}
