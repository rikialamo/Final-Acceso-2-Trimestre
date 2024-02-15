# API Twitter Proyecto Accesos

## Clase Principal

La clase `Main` es la entrada principal de la aplicación. Implementa la interfaz `CommandLineRunner`, lo que significa que el método `run` se ejecutará automáticamente al iniciar la aplicación.

### Atributos Autowired

- `userRepository`: Repositorio para operaciones relacionadas con usuarios.
- `linkRepository`: Repositorio para operaciones relacionadas con relaciones de seguimiento.
- `linkService`: Servicio para gestionar relaciones de seguimiento entre usuarios.
- `publicationRepository`: Repositorio para operaciones relacionadas con publicaciones.

### Método main

El método `main` inicia la aplicación Spring Boot.

### Método run

El método `run` se ejecuta automáticamente al iniciar la aplicación. Se encarga de crear y almacenar usuarios, publicaciones de ejemplo, así como establecer relaciones de seguimiento entre usuarios.

Cada usuario se crea con su información y se almacena en el repositorio `userRepository`. Se generan publicaciones de ejemplo asociadas a cada usuario y se almacenan en el repositorio `publicationRepository`. Luego, se establecen relaciones de seguimiento utilizando el servicio `linkService`.

Este código es parte esencial de la inicialización de la aplicación y demuestra la interacción con la capa de persistencia y servicios.


## Capa de Persistencia

### Model: Clase Link

La clase `Link` representa la relación de seguimiento entre usuarios en la base de datos. Esta clase se utiliza para almacenar información sobre quién sigue a quién.

#### Atributos de la Clase

- `id`: Identificador único de la relación de seguimiento, generado automáticamente.
- `followed`: Usuario que es seguido en la relación (relación muchos a uno).
- `follower`: Usuario que sigue en la relación (relación muchos a uno).

#### Constructor

La clase tiene un constructor que acepta el usuario seguido y el usuario seguidor para crear una nueva relación de seguimiento.

```
public Link(User followed, User follower) {
    this.followed = followed;
    this.follower = follower;
}
```

#### Anotaciones JPA

La clase está anotada con las siguientes anotaciones JPA:

- `@Entity`: Indica que la clase es una entidad JPA y está mapeada a una tabla en la base de datos.
- `@Table(name = "links")`: Especifica el nombre de la tabla en la base de datos.
- `@Id`: Indica el atributo que actúa como clave primaria.
- `@GeneratedValue(strategy = GenerationType.IDENTITY)`: Especifica la estrategia de generación de valores para la clave primaria (en este caso, se utiliza la identidad).

Las relaciones muchos a uno (`@ManyToOne`) y las anotaciones de unión (`@JoinColumn`) se utilizan para establecer la relación entre las entidades `User` y la relación de seguimiento `Link`.

### Model: Clase Publication

La clase `Publication` representa una publicación en la base de datos, almacenando información sobre el usuario que la realizó, el texto y las fechas de creación y edición.

#### Anotaciones JPA

- **`@Entity`**: Indica que la clase es una entidad JPA y está mapeada a una tabla en la base de datos.
- **`@Table(name = "publications")`**: Especifica el nombre de la tabla en la base de datos.
- **`@Id`**: Indica el atributo que actúa como clave primaria.
- **`@GeneratedValue(strategy = GenerationType.IDENTITY)`**: Especifica la estrategia de generación de valores para la clave primaria (en este caso, se utiliza la identidad).
- **`@ManyToOne`**: Indica una relación muchos a uno.
- **`@JoinColumn(name = "user")`**: Especifica la columna en la tabla de base de datos que se utiliza como clave externa en la relación muchos a uno.

#### Atributos de la Clase `Publication`

- **`id (Long)`**: Identificador único de la publicación.
- **`user (User)`**: Usuario que realizó la publicación (relación muchos a uno).
- **`text (String)`**: Texto de la publicación.
- **`creationDate (String)`**: Fecha de creación de la publicación.
- **`editionDate (String)`**: Fecha de edición de la publicación.

### Model: Clase Role

La enumeración `Role` define roles para usuarios en la aplicación, con posibles valores `ADMIN` o `USER`.

#### Valores de la Enumeración

- **`ADMIN`**: Rol que representa a un administrador.
- **`USER`**: Rol que representa a un usuario estándar.

### Model: Clase User

La clase `User` representa a un usuario en la aplicación y está diseñada para integrarse con la autenticación y autorización de Spring Security.

#### Atributos

- **`id`**: Identificador único del usuario.
- **`username`**: Nombre de usuario del usuario (debe ser único).
- **`password`**: Contraseña del usuario.
- **`email`**: Correo electrónico del usuario (debe ser único).
- **`description`**: Descripción del usuario.
- **`creationDate`**: Fecha de creación del usuario.
- **`publications`**: Lista de publicaciones realizadas por el usuario.
- **`links`**: Lista de enlaces de seguimiento asociados al usuario.
- **`role`**: Rol del usuario en la aplicación (ADMIN o USER).

#### Constructores

- **`User(String username, String email, String password, String description, String creationDate, Role role)`**: Constructor para crear un usuario con el rol especificado.
- **`User(String username, String email, String password, String description, String creationDate)`**: Constructor adicional para crear un usuario con el rol USER por defecto.

#### Métodos

- **`addPublication(Publication publication)`**: Método para agregar una publicación a la lista de publicaciones del usuario.

#### Implementación UserDetails de Spring Security

La clase implementa la interfaz `UserDetails` de Spring Security, proporcionando métodos para integrarse con la autenticación y autorización.

- **`getAuthorities()`**: Retorna una lista con los roles del usuario, representados como instancias de `SimpleGrantedAuthority`.
- **`isAccountNonExpired()`**: Indica si la cuenta del usuario ha expirado.
- **`isAccountNonLocked()`**: Indica si la cuenta del usuario está bloqueada.
- **`isCredentialsNonExpired()`**: Indica si las credenciales del usuario han expirado.
- **`isEnabled()`**: Indica si el usuario está habilitado.

### Repository: Interfaz LinkRepository

El repositorio `LinkRepository` es una interfaz de Spring Data JPA que gestiona las operaciones de la base de datos relacionadas con las relaciones de seguimiento.

#### Métodos

- **`findByFollowedIdFollowerId(Long followedId, Long followerId): Optional<Link>`**: Consulta personalizada para buscar una relación de seguimiento por el identificador del usuario seguido y del usuario seguidor. Retorna un `Optional` que puede contener la relación de seguimiento si existe.

### Repository: Interfaz PublicationRepository

El repositorio `PublicationRepository` es una interfaz de Spring Data JPA que gestiona las operaciones de la base de datos relacionadas con las publicaciones.

#### Métodos

- **`findAllPublicationsResponseDto(): List<PublicationResponseDto>`**: Consulta personalizada para obtener todos los DTO de publicaciones.

- **`findPublicationResponseDtoById(Long id): Optional<PublicationResponseDto>`**: Consulta personalizada para obtener un DTO de publicación por su identificador.

### Repository: Interfaz UserRepository

El repositorio `UserRepository` es una interfaz de Spring Data JPA que gestiona las operaciones de la base de datos relacionadas con los usuarios.

#### Métodos

#### Consultas relacionadas con los usuarios y sus relaciones

- **`findAll(): List<User>`**: Retorna una lista de todos los usuarios.

- **`findById(Long id): Optional<User>`**: Retorna un usuario por su identificador.

- **`findByUsername(String username): Optional<User>`**: Retorna un usuario por su nombre de usuario.

- **`findFollowerPeopleById(Long id): List<User>`**: Retorna la lista de personas que siguen al usuario especificado.

- **`findFollowedPeopleById(Long id): List<User>`**: Retorna la lista de personas a las que sigue el usuario especificado.

#### Consultas para obtener DTO específicos de los usuarios

- **`findAllUserResponseDto(): List<UserResponseDto>`**: Retorna una lista de DTO de usuarios.

- **`findUserResponseDtoById(Long id): Optional<UserResponseDto>`**: Retorna un DTO de usuario por su identificador.

- **`findUserResponseDtoByUsername(String username): Optional<UserResponseDto>`**: Retorna un DTO de usuario por su nombre de usuario.

- **`findFollowerPeopleResponseDtoById(Long id): List<UserResponseDto>`**: Retorna la lista de DTO de personas que siguen al usuario especificado.

- **`findFollowedPeopleResponseDtoById(Long id): List<UserResponseDto>`**: Retorna la lista de DTO de personas a las que sigue el usuario especificado.

#### Consultas relacionadas con las publicaciones y las relaciones de seguimiento

- **`findPublicationsById(Long id): List<Publication>`**: Retorna la lista de publicaciones del usuario especificado.

- **`findFollowedPeoplePublicationsById(Long id): List<Publication>`**: Retorna la lista de publicaciones de las personas a las que sigue el usuario especificado.

#### Consultas para obtener DTO específicos de las publicaciones

- **`findPublicationsResponseDtoById(Long id): List<PublicationResponseDto>`**: Retorna la lista de DTO de publicaciones del usuario especificado.

- **`findFollowedPeoplePublicationsResponseDtoById(Long id): List<PublicationResponseDto>`**: Retorna la lista de DTO de publicaciones de las personas a las que sigue el usuario especificado.

### Service: Clase LinkService

El servicio `LinkService` se encarga de gestionar operaciones relacionadas con las relaciones de seguimiento (`Link`) en la aplicación.

## Métodos

#### `add(User followed, User follower)`

Agrega una relación de seguimiento entre un usuario seguido y un usuario seguidor.

#### Parámetros

- `followed`: Usuario que es seguido.
- `follower`: Usuario que sigue.

### Service: Clase PublicationService

Este servicio gestiona operaciones relacionadas con publicaciones en una aplicación similar a Twitter.

## Métodos

#### findAll
Recupera todas las publicaciones en formato DTO.

#### findById
Recupera una publicación por su ID.

#### deleteById
Elimina una publicación por su ID.

#### insert
Inserta una nueva publicación. Verifica la existencia del usuario asociado.

#### update
Actualiza una publicación existente. Verifica la coincidencia del autor

## Capa de controladores

### Controller: Clase ExceptionHandlerController

Esta clase presenta la anotación **`@RestControllerAdvice`** y se encarga de manejar excepciones globalmente en la API, proporcionando métodos específicos para manejar diferentes tipos de excepciones y devolver respuestas apropiadas.

#### `handleHttpMessageNotReadableException`
Este método maneja el caso en el que la solicitud contiene JSON malformado (lanza `HttpMessageNotReadableException`). Devuelve un mensaje de error indicando la malformación del JSON.

#### `handleMethodArgumentTypeMismatchException`
Maneja el caso en el que se produce una incompatibilidad en el tipo de argumento del método (lanza `MethodArgumentTypeMismatchException`). Devuelve un mensaje de error indicando la incompatibilidad en el tipo de argumento del método.

#### `handleUserNotFoundException`
Maneja el caso en el que no se encuentra un usuario (lanza `UserNotFoundException`). Devuelve un mensaje de error indicando que no se encontró al usuario.

#### `handlePublicationNotFoundException`
Maneja el caso en el que no se encuentra una publicación (lanza `PublicationNotFoundException`). Devuelve un mensaje de error indicando que no se encontró al usuario.

#### `handleDataIntegrityViolationException`
Maneja el caso en el que se produce una violación de integridad de datos, como una violación de restricción única (lanza `DataIntegrityViolationException`). Devuelve un mensaje de error indicando la violación de integridad de datos.

#### `handleException`
Maneja excepciones genéricas que no son capturadas específicamente por otros métodos (lanza `Exception`). Devuelve un mensaje de error genérico.

### Excepciones

En la API, se han definido tres excepciones personalizadas para manejar situaciones específicas durante la ejecución.

#### PublicationIncorrectAuthorException

Esta excepción se utiliza para indicar que el autor de una publicación es incorrecto. Se lanza cuando se intenta realizar una operación que requiere un autor específico en una publicación.

#### PublicationNotFoundException

Esta excepción se lanza cuando se intenta acceder a una publicación que no existe en la base de datos.

#### UserNotFoundException

Esta excepción se utiliza cuando se intenta acceder a un usuario que no existe en la base de datos.

### Controller: Clase PublicationController

Este controlador (`PublicationController`) gestiona operaciones relacionadas con las publicaciones en la API.

#### Endpoints

##### `GET /api/publication/{id}`
Recupera una publicación por su identificador.
- **Parámetros de Ruta:**
    - `id` (Tipo: `long`) - Identificador de la publicación.
- **Respuesta Exitosa (Código de Estado: 200):**
    - Devuelve el DTO (`PublicationResponseDto`) correspondiente a la publicación.

##### `DELETE /api/publication/{id}`
Elimina una publicación por su identificador.
- **Parámetros de Ruta:**
    - `id` (Tipo: `long`) - Identificador de la publicación a eliminar.
- **Respuesta Exitosa (Código de Estado: 200):**
    - La publicación se elimina con éxito.

##### `POST /api/publication`
Crea una nueva publicación.
- **Cuerpo de la Solicitud:**
    - DTO (`PublicationRequestDto`) con información para crear la nueva publicación.
- **Respuesta Exitosa (Código de Estado: 201):**
    - La nueva publicación se crea con éxito.

##### `PUT /api/publication`
Actualiza una publicación existente.
- **Cuerpo de la Solicitud:**
    - DTO (`PublicationRequestDto`) con información para actualizar la publicación.
- **Respuesta Exitosa (Código de Estado: 200):**
    - La publicación se actualiza con éxito.

##### `GET /api/publication`
Recupera todas las publicaciones disponibles.
- **Respuesta Exitosa (Código de Estado: 200):**
    - Devuelve una lista de DTOs (`PublicationResponseDto`) que representan todas las publicaciones.

### Controller: Clase UserController

Este controlador (`UserController`) gestiona operaciones relacionadas con usuarios en la API.

#### Endpoints

##### `GET /api/user/`
Recupera todos los usuarios disponibles.
- **Respuesta Exitosa (Código de Estado: 200):**
    - Devuelve una lista de DTOs (`UserResponseDto`) que representan a todos los usuarios.

##### `GET /api/user/{id}`
Recupera un usuario por su identificador.
- **Parámetros de Ruta:**
    - `id` (Tipo: `Long`) - Identificador del usuario.
- **Respuesta Exitosa (Código de Estado: 200):**
    - Devuelve un DTO (`UserResponseDto`) que representa al usuario correspondiente al identificador.

##### `GET /api/user/username/{username}`
Recupera un usuario por su nombre de usuario.
- **Parámetros de Ruta:**
    - `username` (Tipo: `String`) - Nombre de usuario del usuario.

##### `GET /api/user/{id}/followerpeople`
Recupera las personas que siguen al usuario por su identificador.
- **Parámetros de Ruta:**
    - `id` (Tipo: `Long`) - Identificador del usuario.
- **Respuesta Exitosa (Código de Estado: 200):**
    - Devuelve una lista de DTOs (`UserResponseDto`) que representan a las personas que siguen al usuario.

##### `GET /api/user/{id}/followedpeople`
Recupera las personas seguidas por el usuario por su identificador.
- **Parámetros de Ruta:**
    - `id` (Tipo: `Long`) - Identificador del usuario.
- **Respuesta Exitosa (Código de Estado: 200):**
    - Devuelve una lista de DTOs (`UserResponseDto`) que representan a las personas seguidas por el usuario.

##### `PATCH /api/user/{id}/description/{description}`
Actualiza la descripción de un usuario por su identificador.
- **Parámetros de Ruta:**
    - `id` (Tipo: `Long`) - Identificador del usuario.
    - `description` (Tipo: `String`) - Nueva descripción del usuario.

##### `GET /api/user/{id}/publications`
Recupera las publicaciones realizadas por el usuario por su identificador.
- **Parámetros de Ruta:**
    - `id` (Tipo: `Long`) - Identificador del usuario.
- **Respuesta Exitosa (Código de Estado: 200):**
    - Devuelve una lista de DTOs (`PublicationResponseDto`) que representan las publicaciones del usuario.

##### `GET /api/user/{id}/followedpeople/publications`
Recupera las publicaciones de las personas seguidas por el usuario por su identificador.
- **Parámetros de Ruta:**
    - `id` (Tipo: `Long`) - Identificador del usuario.
- **Respuesta Exitosa (Código de Estado: 200):**
    - Devuelve una lista de DTOs (`PublicationResponseDto`) que representan las publicaciones de las personas seguidas por el usuario.

### DTO

Los DTOs (Objetos de Transferencia de Datos) son utilizados para transferir datos entre la capa de servicios y los controladores. 

Los DTO utilizados en las respuestas son modelados mediante interfaces 
(ver [https://danielme.com/2023/03/23/curso-spring-data-jpa-proyecciones-personalizadas/](https://danielme.com/2023/03/23/curso-spring-data-jpa-proyecciones-personalizadas/)).

# Security

## Authentication: Clase authenticationController

Este controlador maneja las operaciones relacionadas con la autenticación en la API.

### Endpoints

#### 1. Inicio de Sesión

**URL:** `/auth/login`

**Método:** `POST`

**Descripción:** Maneja la solicitud de inicio de sesión.

**Parámetros de Entrada:**
- `request`: DTO que contiene la información de inicio de sesión.

**Respuesta Exitosa:**
- Código de Estado: 200 (OK)
- Cuerpo: DTO que contiene el token de autenticación generado.

#### 2. Registro de Usuario

**URL:** `/auth/register`

**Método:** `POST`

**Descripción:** Maneja la solicitud de registro de usuario.

**Parámetros de Entrada:**
- `request`: DTO que contiene la información de registro.

**Respuesta Exitosa:**
- Código de Estado: 200 (OK)
- Cuerpo: DTO que contiene el token de autenticación generado.

#### DTOs Utilizados

1. `LoginRequestDTO`: DTO que contiene la información de inicio de sesión.
2. `RegisterRequestDTO`: DTO que contiene la información de registro.

#### Dependencias

- `AuthenticationService`: Servicio de autenticación utilizado para gestionar las operaciones.

## Authentication: Clase authenticationService

Este controlador maneja las operaciones relacionadas con la autenticación en la API.

### Métodos

#### 1. Inicio de Sesión

- **Descripción:** Maneja la solicitud de inicio de sesión.
- **Ruta:** No aplicable (es un servicio interno).
- **Método:** No aplicable (es un servicio interno).
- **Parámetros de Entrada:**
    - `requestData`: DTO que contiene la información de inicio de sesión.
- **Respuesta:**
    - Tipo: `AuthenticationResponseDTO`
    - Descripción: Respuesta con el token de autenticación generado.

#### 2. Registro de Usuario

- **Descripción:** Maneja la solicitud de registro de usuario.
- **Ruta:** No aplicable (es un servicio interno).
- **Método:** No aplicable (es un servicio interno).
- **Parámetros de Entrada:**
    - `requestData`: DTO que contiene la información de registro.
- **Respuesta:**
    - Tipo: `AuthenticationResponseDTO`
    - Descripción: Respuesta con el token de autenticación generado.

#### Dependencias

- `UserRepository`: Repositorio que proporciona métodos para acceder y manipular datos de usuarios.
- `JwtService`: Servicio encargado de generar tokens JWT para la autenticación.
- `PasswordEncoder`: Codificador de contraseñas utilizado para almacenar contraseñas de usuarios de manera segura.
- `AuthenticationManager`: Gestor de autenticación utilizado para autenticar usuarios durante el inicio de sesión.

## Configuration: Clase AuthenticationManagerConfiguration

Este componente proporciona la configuración del administrador de autenticación en la aplicación.

### Método

- **Descripción:** Configura y proporciona el administrador de autenticación.
- **Método:** `authenticationManager`
- **Parámetros de Entrada:**
    - `authenticationConfiguration`: Configuración de autenticación.
- **Respuesta:**
    - Tipo: `AuthenticationManager`
    - Descripción: Administrador de autenticación configurado.

### Dependencias

- `AuthenticationConfiguration`: Configuración de autenticación utilizada para obtener el administrador de autenticación.

## Configuration: Clase AuthenticationProviderConfiguration

Este componente proporciona la configuración del proveedor de autenticación en la aplicación.

### Método

- **Descripción:** Configura y proporciona el proveedor de autenticación.
- **Método:** `authenticationProvider`
- **Respuesta:**
    - Tipo: `AuthenticationProvider`
    - Descripción: Proveedor de autenticación configurado.

### Dependencias

- `UserDetailsService`: Servicio que carga detalles específicos del usuario.
- `PasswordEncoder`: Codificador de contraseñas utilizado para gestionar las contraseñas de los usuarios.

## Configuration: Clase PasswordEncoderConfiguration

Este componente proporciona la configuración del codificador de contraseñas en la aplicación, utilizando BCrypt para el cifrado de contraseñas.

### Método

- **Descripción:** Configura y proporciona un codificador de contraseñas BCrypt.
- **Método:** `passwordEncoder`
- **Respuesta:**
    - Tipo: `PasswordEncoder`
    - Descripción: Codificador de contraseñas BCrypt configurado.

## Configuration: Clase SecurityFilterChainConfiguration

### Método

- **Descripción:** Configura el filtro de seguridad de la cadena.
- **Método:** `securityFilterChain`
- **Parámetros:**
    - `httpSecurityFilterChainBuilder` - Configurador de la cadena de filtros de seguridad HTTP.
- **Respuesta:**
    - Tipo: `SecurityFilterChain`
    - Descripción: Configuración de la cadena de filtros de seguridad.

## Configuración Adicional

- Deshabilita CSRF.
- Autoriza las solicitudes HTTP para los siguientes patrones propios de la aplicación
    - `/auth/**`
    - `/api/user/username/{username}`
    - `/api/user/{id}/publications`
- Autoriza las solicitudes HTTP para permitir el funcionamiento de Swagger
(ver [https://stackoverflow.com/a/48971992](https://stackoverflow.com/a/48971992))
    - `/v2/api-docs`
    - `/swagger-resources"`
    - `/swagger-resources/**`
    - `/configuration/ui`
    - `/configuration/security`
    - `/swagger-ui.html`
    - `/webjars/**`
    - `/v3/api-docs/**`
    - `/swagger-ui/**"`
- Cualquier otra solicitud requiere autenticación.
- Utiliza un proveedor de autenticación.
- Agrega el filtro de autenticación JWT antes del filtro de autenticación de nombre de usuario y contraseña.

## Configuration: Clase UserDetailsServiceImpl

Esta clase proporciona una implementación personalizada de `UserDetailsService` para cargar detalles del usuario en la aplicación.

### Método

- **Descripción:** Carga los detalles del usuario por su nombre de usuario.
- **Interfaz:** `UserDetailsService`
- **Método:** `loadUserByUsername`
- **Parámetros:**
    - `username` - Nombre de usuario para cargar los detalles.
- **Respuesta:**
    - Tipo: `UserDetails`
    - Descripción: Detalles del usuario si existe.
- **Excepciones:**
    - `UsernameNotFoundException` - Si no se encuentra el usuario.

### Configuración Adicional

- La clase es un componente de Spring (`@Component`).
- Utiliza la inyección de dependencias para acceder al repositorio de usuarios (`UserRepository`).

## JWT

## JWT: Clase JwtAuthenticationFilter

Esta clase representa un filtro de seguridad que maneja la autenticación basada en tokens JWT.

## Métodos

- **Descripción:** Obtiene el token del encabezado de autorización de la solicitud.
- **Método:** `getTokenFromRequest`
- **Parámetros:**
    - `request` - La solicitud HTTP.
- **Respuesta:**
    - Tipo: `String`
    - Descripción: El token JWT si existe en el encabezado de autorización, de lo contrario, null.

### Lógica de Filtrado Interno

- **Descripción:** Realiza la lógica de filtrado para autenticar la solicitud mediante JWT.
- **Método:** `doFilterInternal`
- **Parámetros:**
    - `request` - La solicitud HTTP.
    - `response` - La respuesta HTTP.
    - `filterChain` - La cadena de filtros.
- **Excepciones:**
    - `ServletException` - Si ocurre un error en el filtro.
    - `IOException` - Si ocurre un error de entrada/salida.

## Configuración Adicional

- La clase es un componente de Spring (`@Component`).
- Utiliza la inyección de dependencias para acceder a servicios relacionados con JWT (`JwtService` y `UserDetailsService`).

## JWT: Clase JwtService

Esta clase representa un servicio encargado de gestionar tokens JWT para la autenticación.

## Métodos

### Generar Token JWT

- **Descripción:** Genera un token JWT para el usuario proporcionado.
- **Método:** `getToken`
- **Parámetros:**
    - `user` - Detalles del usuario.
- **Respuesta:**
    - Tipo: `String`
    - Descripción: Token JWT generado.

### Verificar Validez del Token

- **Descripción:** Verifica si un token JWT es válido para los detalles del usuario proporcionados.
- **Método:** `isTokenValid`
- **Parámetros:**
    - `token` - Token JWT a verificar.
    - `userDetails` - Detalles del usuario.
- **Respuesta:**
    - Tipo: `boolean`
    - Descripción: `true` si el token es válido, `false` en caso contrario.

### Obtener Nombre de Usuario desde el Token

- **Descripción:** Obtiene el nombre de usuario almacenado en un token JWT.
- **Método:** `getUsernameFromToken`
- **Parámetros:**
    - `token` - Token JWT.
- **Respuesta:**
    - Tipo: `String`
    - Descripción: Nombre de usuario.

### Obtener Claims del Token

- **Descripción:** Obtiene los reclamos (claims) de un token JWT.
- **Método:** `getClaims`
- **Parámetros:**
    - `token` - Token JWT.
- **Respuesta:**
    - Tipo: `Claims`
    - Descripción: Claims del token.

### Obtener Clave de Firma

- **Descripción:** Obtiene la clave utilizada para firmar los tokens JWT.
- **Método:** `getKey`
- **Respuesta:**
    - Tipo: `Key`
    - Descripción: Clave de firma.

### Verificar Expiración del Token

- **Descripción:** Verifica si un token JWT ha expirado.
- **Método:** `isTokenExpired`
- **Parámetros:**
    - `token` - Token JWT.
- **Respuesta:**
    - Tipo: `boolean`
    - Descripción: `true` si el token ha expirado, `false` en caso contrario.

## Configuración Adicional

- La clase utiliza una clave secreta (`SECRET_KEY`) para firmar los tokens JWT.
- Utiliza la biblioteca `io.jsonwebtoken` para la manipulación de tokens JWT.

