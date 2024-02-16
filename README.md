# API Twitter Proyecto Accesos

## Clase Principal

La clase `Main` es la entrada principal de la aplicación. Implementa la interfaz `CommandLineRunner`, lo que significa que el método `run` se ejecutará automáticamente al iniciar la aplicación.

### Atributos Autowired

- `userRepository`: Repositorio para operaciones relacionadas con usuarios.
- `linkRepository`: Repositorio para operaciones relacionadas con relaciones de seguimiento.
- `linkService`: Servicio para gestionar relaciones de seguimiento entre usuarios.
- `publicationRepository`: Repositorio para operaciones relacionadas con publicaciones.



## Capa de Persistencia

### Model: Clase Link

La clase `followers` representa la relación de seguimiento entre usuarios en la base de datos. Esta clase se utiliza para almacenar información sobre quién sigue a quién.

#### Atributos de la Clase

- `id`: Identificador único de la relación de seguimiento, generado automáticamente.
- `followed`: Usuario que es seguido en la relación (relación muchos a uno).
- `follower`: Usuario que sigue en la relación (relación muchos a uno).

#### Anotaciones JPA

La clase está anotada con las siguientes anotaciones JPA:

- `@Entity`: Indica que la clase es una entidad JPA y está mapeada a una tabla en la base de datos.
- `@Table(name = "followers")`: Especifica el nombre de la tabla en la base de datos.
- `@Id`: Indica el atributo que actúa como clave primaria.
- `@GeneratedValue(strategy = GenerationType.IDENTITY)`: Especifica la estrategia de generación de valores para la clave primaria (en este caso, se utiliza la identidad).

Las relaciones (`@ManyToOne`) y las anotaciones de unión (`@JoinColumn`) se utilizan para establecer la relación entre las entidades `User` y la relación de seguimiento `Link`.

### Model: Clase Publication

La clase `Publication` representa una publicación en la base de datos, almacenando información sobre el usuario que la realizó, el texto y las fechas de creación y edición.

#### Anotaciones JPA

- **`@Entity`**: Indica que la clase es una entidad JPA y está mapeada a una tabla en la base de datos.
- **`@Table(name = "publications")`**: Especifica el nombre de la tabla en la base de datos.
- **`@Id`**: Indica el atributo que actúa como clave primaria.
- **`@GeneratedValue(strategy = GenerationType.IDENTITY)`**: Especifica la estrategia de generación de valores para la clave primaria (en este caso, se utiliza la identidad).
- **`@ManyToOne`**: Indica una relación muchos a uno.
- **`@JoinColumn(name = "user")`**: Especifica la columna en la tabla de base de datos que se utiliza como clave externa en la relación muchos a uno.

#### Atributos de la Clase `Publicacion`

- **`id (Long)`**: Identificador único de la publicación.
- **`user (User)`**: Usuario que realizó la publicación (relación muchos a uno).
- **`text (String)`**: Texto de la publicación.
- **`creationDate (String)`**: Fecha de creación de la publicación.
- **`editionDate (String)`**: Fecha de edición de la publicación.

### Model: Clase Usuario

La clase `User` representa a un usuario en la aplicación y está diseñada para integrarse con la autenticación y autorización de Spring Security.

#### Constructores

- **`User(String username, String email, String password, String description, String creationDate, Role role)`**: Constructor para crear un usuario con el rol especificado.
- **`User(String username, String email, String password, String description, String creationDate)`**: Constructor adicional para crear un usuario con el rol USER por defecto.

#### Métodos

- **`addPublication(Publication publication)`**: Método para agregar una publicación a la lista de publicaciones del usuario.

### Repository: Interfaz LinkRepository

El repositorio `LinkRepository` es una interfaz de Spring Data JPA que gestiona las operaciones de la base de datos relacionadas con las relaciones de seguimiento.

### Repository: Interfaz PublicationRepository

El repositorio `PublicationRepository` es una interfaz de Spring Data JPA que gestiona las operaciones de la base de datos relacionadas con las publicaciones.

### Repository: Interfaz UserRepository

El repositorio `UserRepository` es una interfaz de Spring Data JPA que gestiona las operaciones de la base de datos relacionadas con los usuarios.


### Service: Clase LinkService

El servicio `LinkService` se encarga de gestionar operaciones relacionadas con las relaciones de seguimiento (`followers`) en la aplicación.

## Métodos

#### `add(User followed, User follower)`

Agrega una relación de seguimiento entre un usuario seguido y un usuario seguidor.

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


##### `DELETE /api/publication/{id}`
Elimina una publicación por su identificador.

##### `POST /api/publication`
Crea una nueva publicación.

##### `PUT /api/publication`
Actualiza una publicación existente.

##### `GET /api/publication`
Recupera todas las publicaciones disponibles.


### Controller: Clase UserController

Este controlador (`UserController`) gestiona operaciones relacionadas con usuarios en la API.

#### Endpoints

##### `GET /api/user/`
Recupera todos los usuarios disponibles.
- **Respuesta Exitosa (Código de Estado: 200):**
    - Devuelve una lista de DTOs (`UserResponseDto`) que representan a todos los usuarios.

##### `GET /api/user/{id}`
Recupera un usuario por su identificador.
-
- **Respuesta Exitosa (Código de Estado: 200):**

##### `GET /api/user/username/{username}`
Recupera un usuario por su nombre de usuario.


##### `GET /api/user/{id}/followerpeople`
Recupera las personas que siguen al usuario por su identificador.
- **Respuesta Exitosa (Código de Estado: 200):**

##### `GET /api/user/{id}/followedpeople`
Recupera las personas seguidas por el usuario por su identificador.
- **Parámetros de Ruta:**
    - `id` (Tipo: `Long`) - Identificador del usuario.
- **Respuesta Exitosa (Código de Estado: 200):**

##### `PATCH /api/user/{id}/description/{description}`
Actualiza la descripción de un usuario por su identificador.

##### `GET /api/user/{id}/publications`
Recupera las publicaciones realizadas por el usuario por su identificador.

- **Respuesta Exitosa (Código de Estado: 200):**

##### `GET /api/user/{id}/followedpeople/publications`
Recupera las publicaciones de las personas seguidas por el usuario por su identificador.


### DTO

Los DTOs (Objetos de Transferencia de Datos) son utilizados para transferir datos entre la capa de servicios y los controladores. 

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

### Obtener Clave de Firma

- **Descripción:** Obtiene la clave utilizada para firmar los tokens JWT.
- **Método:** `getKey`
- **Respuesta:**
    - Tipo: `Key`
    - Descripción: Clave de firma.

## Muchas Gracias por su Atención





