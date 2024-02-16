package com.vedruna.webapp.persistence.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Clase que representa a un usuario en la aplicación. Implementa la interfaz
 * UserDetails de Spring Security para integrarse con la autenticación y
 * autorización.
 */

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "users")
public class Usuario implements UserDetails {

	/**
	 * Identificador único del usuario.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * Nombre de usuario del usuario (debe ser único).
	 */
	@Column(name = "username", unique = true)
	private String username;

	/**
	 * Contraseña del usuario.
	 */
	@Column(name = "password")
	private String password;

	/**
	 * Correo electrónico del usuario (debe ser único).
	 */
	@Column(name = "email", unique = true)
	private String email;

	/**
	 * Descripción del usuario.
	 */
	@Column(name = "description")
	private String description;

	/**
	 * Fecha de registro del usuario.
	 */
	@Column(name = "creationDate")
	private String creationDate;

	/**
	 * Lista de publicaciones realizadas por el usuario.
	 */
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Publicacion> publications;

	/**
	 * Lista de enlaces de seguimiento asociados al usuario.
	 */
	@OneToMany(mappedBy = "followed")
	private List<followers> links;

	/**
	 * Rol del usuario en la aplicación (ADMIN o USER).
	 */
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	Role role;

	/**
	 * Constructor para crear un usuario con el rol especificado.
	 */
	public Usuario(String username, String email, String password, String description, String creationDate, Role role) {
		this.username = username;
		this.email = email;
		this.password = password;
		this.description = description;
		this.creationDate = creationDate;
		this.role = role;
		this.publications = new ArrayList<>();
		this.links = new ArrayList<>();
	}

	/**
	 * Constructor adicional para crear un usuario con el rol USER por defecto.
	 */
	public Usuario(String username, String email, String password, String description, String creationDate) {
		this(username, email, password, description, creationDate, Role.USER);
	}

	/**
	 * Método para agregar una publicación a la lista de publicaciones del usuario.
	 */
	public void addPublication(Publicacion publication) {
		publications.add(publication);
	}

	/**
	 * Método para eliminar una publicación de la lista de publicaciones del
	 * usuario.
	 */
	public void removePublication(Publicacion publication) {
		publications.remove(publication);
	}

	// Implementación de la interfaz UserDetails de Spring Security para integrarse
	// con la autenticación y autorización.

	/**
	 * Retorna una lista con los roles del usuario, representados como instancias de
	 * SimpleGrantedAuthority.
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		//
		return List.of(new SimpleGrantedAuthority(role.name()));
	}

	/**
	 * Indica si la cuenta del usuario ha expirado. En este caso, siempre se retorna
	 * true, indicando que la cuenta no expira.
	 */
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	/**
	 * Indica si la cuenta del usuario está bloqueada. En este caso, siempre se
	 * retorna true, indicando que la cuenta no está bloqueada.
	 */
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	/**
	 * Indica si las credenciales del usuario han expirado. En este caso, siempre se
	 * retorna true, indicando que las credenciales no expiran.
	 */
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	/**
	 * Indica si el usuario está habilitado. En este caso, siempre se retorna true,
	 * indicando que el usuario está habilitado.
	 */
	@Override
	public boolean isEnabled() {
		return true;
	}

}
