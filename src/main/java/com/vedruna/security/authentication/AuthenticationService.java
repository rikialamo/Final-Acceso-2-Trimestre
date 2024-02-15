package com.vedruna.security.authentication;

import com.vedruna.security.authentication.dto.AuthenticationResponseDTO;
import com.vedruna.security.authentication.dto.LoginRequestDTO;
import com.vedruna.security.authentication.dto.RegisterRequestDTO;
import com.vedruna.webapp.persistence.model.Role;
import com.vedruna.webapp.persistence.model.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.vedruna.security.jwt.JwtService;
import com.vedruna.webapp.persistence.repository.UserRepository;
import lombok.RequiredArgsConstructor;

/**
 * Servicio que gestiona la autenticación de usuarios.
 */
@Service
@RequiredArgsConstructor
public class AuthenticationService {

	private final UserRepository userRepository;
	private final JwtService jwtService;
	private final PasswordEncoder passwordEncoder;
	private final AuthenticationManager authenticationManager;

	/**
	 * Maneja la solicitud de inicio de sesión.
	 *
	 * @param requestData DTO que contiene la información de inicio de sesión.
	 * @return Respuesta con el token de autenticación generado.
	 */
	public AuthenticationResponseDTO login(LoginRequestDTO requestData) {
		Authentication authentication = new UsernamePasswordAuthenticationToken(requestData.getUsername(),
				requestData.getPassword());
		authenticationManager.authenticate(authentication);
		UserDetails user = userRepository.findByUsername(requestData.getUsername()).orElseThrow();

		String token = jwtService.getToken(user);
		return new AuthenticationResponseDTO(token);
	}

	/**
	 * Maneja la solicitud de registro de usuario.
	 *
	 * @param requestData DTO que contiene la información de registro.
	 * @return Respuesta con el token de autenticación generado.
	 */
	public AuthenticationResponseDTO register(RegisterRequestDTO requestData) {

		User user = new User(requestData.getUsername(), requestData.getEmail(),
				passwordEncoder.encode(requestData.getPassword()), requestData.getDescription(),
				requestData.getCreationDate(), Role.USER);

		userRepository.save(user);

		String token = jwtService.getToken(user);
		return new AuthenticationResponseDTO(token);

	}

}
