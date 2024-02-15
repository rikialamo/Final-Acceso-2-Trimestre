package com.vedruna.security.authentication;

import com.vedruna.security.authentication.dto.AuthenticationResponseDTO;
import com.vedruna.security.authentication.dto.LoginRequestDTO;
import com.vedruna.security.authentication.dto.RegisterRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

/**
 * Controlador que maneja las operaciones relacionadas con la autenticación.
 */
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

	@Autowired
	private final AuthenticationService authentificationService;

	/**
	 * Maneja la solicitud de inicio de sesión.
	 *
	 * @param request DTO que contiene la información de inicio de sesión.
	 * @return Respuesta con el token de autenticación generado.
	 */
	@PostMapping("login")
	public ResponseEntity<AuthenticationResponseDTO> login(@RequestBody LoginRequestDTO request) {
		return ResponseEntity.ok(authentificationService.login(request));
	}

	/**
	 * Maneja la solicitud de registro de usuario.
	 *
	 * @param request DTO que contiene la información de registro.
	 * @return Respuesta con el token de autenticación generado.
	 */
	@PostMapping("register")
	public ResponseEntity<AuthenticationResponseDTO> register(@RequestBody RegisterRequestDTO request) {
		return ResponseEntity.ok(authentificationService.register(request));
	}

}
