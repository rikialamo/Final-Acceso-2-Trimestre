package com.vedruna.security.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.vedruna.security.jwt.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;

/**
 * Configuración de la cadena de filtros de seguridad.
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityFilterChainConfiguration {

	@Autowired
	private final JwtAuthenticationFilter jwtAuthenticationFilter;
	@Autowired
	private final AuthenticationProvider authenticationProvider;

	/**
	 * Configura la cadena de filtros de seguridad
	 *
	 * @param httpSecurityFilterChainBuilder Configurador de la cadena de filtros de
	 *                                       seguridad HTTP.
	 * @return Configuración de la cadena de filtros de seguridad.
	 * @throws Exception Si hay un error en la configuración de seguridad.
	 */
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurityFilterChainBuilder) throws Exception {
		return httpSecurityFilterChainBuilder.csrf(csrfConfigurer -> csrfConfigurer.disable())

				.authorizeHttpRequests(registry -> registry.requestMatchers("/auth/**", // Nuestro
						"/api/user/username/{username}", // Nuestro
						"/api/user/{id}/publications", // Nuestro
						"/v2/api-docs", // Los que siguen son para Swagger
						"/swagger-resources", "/swagger-resources/**", "/configuration/ui", "/configuration/security",
						"/swagger-ui.html", "/webjars/**",
						// -- Swagger UI v3 (OpenAPI)
						"/v3/api-docs/**", "/swagger-ui/**").permitAll().anyRequest().authenticated())

				.authenticationProvider(authenticationProvider)

				.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)

				.build();
	}

}
