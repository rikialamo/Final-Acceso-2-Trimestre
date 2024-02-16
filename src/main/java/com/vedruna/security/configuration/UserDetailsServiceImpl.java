package com.vedruna.security.configuration;

import java.util.Optional;

import com.vedruna.webapp.persistence.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.vedruna.webapp.persistence.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;

/**
 * Implementación personalizada de {@code UserDetailsService} para cargar
 * detalles del usuario.
 */
@Component
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private final UsuarioRepository userRepository;

	/**
	 * Carga los detalles del usuario por su nombre de usuario.
	 *
	 * @param username Nombre de usuario para cargar los detalles.
	 * @return Detalles del usuario si existe.
	 * @throws UsernameNotFoundException Si no se encuentra el usuario.
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Usuario> optionalUser = userRepository.findByUsername(username);
		if (optionalUser.isPresent()) {
			return optionalUser.get();
		}
		throw new UsernameNotFoundException("Usuario not found");
	}

}
