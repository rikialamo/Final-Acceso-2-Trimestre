package com.vedruna.webapp.persistence.repository;

import java.util.List;
import java.util.Optional;

import com.vedruna.webapp.dto.PublicationResponseDto;
import com.vedruna.webapp.dto.UserResponseDto;
import com.vedruna.webapp.persistence.model.Publication;
import com.vedruna.webapp.persistence.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Repositorio de JPA para la entidad User, que gestiona las operaciones de base
 * de datos relacionadas con los usuarios.
 */
public interface UserRepository extends JpaRepository<User, Long> {

	// Métodos de consulta personalizada para obtener información específica de los
	// usuarios y sus relaciones.

	// Consultas relacionadas con los usuarios y sus relaciones

	@Query("SELECT u FROM User u")
	List<User> findAll();

	@Query("SELECT u FROM User u WHERE u.id=:id")
	Optional<User> findById(Long id);

	@Query("SELECT u FROM User u WHERE u.username=:username")
	Optional<User> findByUsername(String username);

	@Query("SELECT k.follower FROM User u JOIN u.links k WHERE u.id=:id")
	List<User> findFollowerPeopleById(Long id);

	@Query("SELECT k.followed FROM User u JOIN u.links k WHERE k.follower.id=:id")
	List<User> findFollowedPeopleById(Long id);

	// Consultas para obtener DTO específicos de los usuarios

	@Query("SELECT u.id as id, u.username as username, u.email as email, u.description as description, u.creationDate as creationDate FROM User u")
	List<UserResponseDto> findAllUserGetDto();

	@Query("SELECT u.id as id, u.username as username, u.email as email, u.description as description, u.creationDate as creationDate FROM User u"
			+ " WHERE u.id=:id")
	Optional<UserResponseDto> findUserGetDtoById(Long id);

	@Query("SELECT u.id as id, u.username as username, u.email as email, u.description as description, u.creationDate as creationDate FROM User u"
			+ " WHERE u.username=:username")
	Optional<UserResponseDto> findUserGetDtoByUsername(String username);

	@Query("SELECT k.follower.id as id, k.follower.username as username, k.follower.email as email, k.follower.description as description, k.follower.creationDate as creationDate"
			+ " FROM User u JOIN u.links k WHERE u.id=:id")
	List<UserResponseDto> findFollowerPeopleGetDtoById(Long id);

	@Query("SELECT k.followed.id as id, k.followed.username as username, k.followed.email as email, k.followed.description as description, k.followed.creationDate as creationDate"
			+ " FROM User u JOIN u.links k WHERE k.follower.id=:id")
	List<UserResponseDto> findFollowedPeopleGetDtoById(Long id);

	// Consultas relacionadas con las publicaciones y las relaciones de seguimiento

	@Query("SELECT p FROM User u JOIN u.publications p WHERE u.id=:id")
	List<Publication> findPublicationsById(Long id);

	@Query("SELECT p FROM User u JOIN u.links k JOIN k.followed.publications p WHERE k.follower.id=:id")
	List<Publication> findFollowedPeoplePublicationsById(Long id);

	// Consultas para obtener DTO específicos de las publicaciones

	@Query("SELECT p.id as id, p.user.id as userId, p.text as text,"
			+ " p.creationDate as creationDate, p.editionDate as editionDate," + " u.username as userUsername"
			+ " FROM User u JOIN u.publications p" + " WHERE u.id=:id")
	List<PublicationResponseDto> findPublicationsGetDtoById(Long id);

	@Query("SELECT p.id as id, p.user.id as userId, p.text as text,"
			+ " p.creationDate as creationDate, p.editionDate as editionDate," + " k.followed.username as userUsername"
			+ " FROM User u JOIN u.links k JOIN k.followed.publications p" + " WHERE k.follower.id=:id"
			+ " ORDER BY p.creationDate DESC")
	List<PublicationResponseDto> findFollowedPeoplePublicationsGetDtoById(Long id);

}
