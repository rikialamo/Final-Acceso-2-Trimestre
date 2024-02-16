package com.vedruna;

import com.vedruna.webapp.persistence.model.Publication;
import com.vedruna.webapp.persistence.model.User;
import com.vedruna.webapp.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.vedruna.webapp.persistence.repository.PublicationRepository;
import com.vedruna.webapp.persistence.repository.UserRepository;

/**
 * Clase principal de la aplicación Spring Boot. Implementa CommandLineRunner,
 * lo que significa que el método run se ejecutará automáticamente al iniciar la
 * aplicación.
 */

@SpringBootApplication
public class Main implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private LinkService linkService;

	@Autowired
	private PublicationRepository publicationRepository;

	/**
	 * Método principal que se ejecuta al iniciar la aplicación.
	 *
	 */
	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

	/**
	 * Método run que se ejecuta automáticamente al iniciar la aplicación. Se
	 * encarga de crear y almacenar usuarios y publicaciones de ejemplo, así como
	 * establecer relaciones de seguimiento entre usuarios.
	 *
	 * @param args Argumentos de línea de comandos (no se utilizan en este caso).
	 */
	@Override
	public void run(String... args) {

		// Creación y almacenamiento de usuarios y publicaciones de ejemplo

		User user1 = new User("root", "root@root.es", "root", "root", "2024-02-15");
		userRepository.save(user1);
		Publication publication1 = new Publication(user1, "root", "2024-02-15", "2024-02-15");
		user1.addPublication(publication1);
		publicationRepository.save(publication1);

		User user2 = new User("Jose Luis", "joselu@joselu.es", "4321", "El mejor", "2024-02-15");
		userRepository.save(user2);
		Publication publication2 = new Publication(user2, "Flutter es lo peor", "2024-02-02", "2024-02-05");
		user2.addPublication(publication2);
		publicationRepository.save(publication2);
		Publication publication3 = new Publication(user2, "Primer día en ferretería Puri", "2024-03-02", "2024-03-05");
		user2.addPublication(publication3);
		publicationRepository.save(publication3);

		User user3 = new User("Ricardo", "ricardo@ricardo.es", "1234", "Volando alto", "2024-02-15");
		userRepository.save(user3);
		Publication publication4 = new Publication(user1, "Siempre volando alto", "2024-02-15", "2024-02-15");
		user2.addPublication(publication4);
		publicationRepository.save(publication4);
		Publication publication5 = new Publication(user3, "Top 3 ....", "2024-02-15", "2024-02-15");
		user2.addPublication(publication5);
		publicationRepository.save(publication5);
		Publication publication6 = new Publication(user3, "Joaquín apruebame por favor", "2024-02-16", "2024-02-16");
		user2.addPublication(publication6);
		publicationRepository.save(publication6);

		linkService.add(user3, user1);
		linkService.add(user3, user2);
		linkService.add(user2, user3);
		linkService.add(user1, user3);
		linkService.add(user2, user1);
	}
}