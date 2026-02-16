# CRUD MVC con Thymeleaf — RA3

## 1) Datos del alumno/a
- Producto (título, descripción, precio, estado)

## 2) Repositorio (fork) y gestión de versiones
- Repositorio base: https://github.com/profeInformatica101/tareasSpringBoot
- Enlace a MI fork: https://github.com/alvaroooom/tareasSpringBoot.git
- Nº de commits realizados: 8, debido a que tuve fallos con Git

## 3) Arquitectura
Explica brevemente cómo has organizado:
- Controller: Gestiona las rutas MVC (GET/POST), recibe peticiones del usuario, valida datos y devuelve vistas Thymeleaf.
- Service: Contiene la lógica de negocio (crear, listar, actualizar y borrar productos) y actúa como intermediario entre Controller y Repository.
- Repository: Interfaz que extiende JpaRepository para el acceso a datos (CRUD automático con JPA).
- Entity: Clase Producto anotada con @Entity que representa la tabla de la base de datos.

## 4) Base de datos elegida (marca una)
- [ ] H2
- Después migré a MySQL

## 5) Configuración de la base de datos
### 5.1 Dependencias añadidas

<dependency>
   <groupId>com.h2database</groupId>
   <artifactId>h2</artifactId>
   <scope>runtime</scope>
</dependency>

<dependency>
   <groupId>com.mysql</groupId>
   <artifactId>mysql-connector-j</artifactId>
   <scope>runtime</scope>
</dependency>

### 5.2 application.properties / application.yml
   server.port=8084

   spring.datasource.url=jdbc:mysql://localhost:3306/proyectoOptativa?allowPublicKeyRetrieval=true&useSSL=false
   spring.datasource.username=root
   spring.datasource.password=root

   spring.jpa.hibernate.ddl-auto=update

### 5.3 Pasos para crear la BD (si aplica)
- H2 se crea automáticamente en memoria al arrancar la aplicación.
- MySQL crea automaticamente con JPA los datos en la base de datos, solo hay que tener creada dentro de DBeaver como es en mi caso la base de datos

## 6) Cómo ejecutar el proyecto
1. Requisitos (Java versión, Maven/Gradle, DB instalada si aplica)
- Java 17
- Maven
2. Comando de arranque:
   - ./mvnw spring-boot:run   (o equivalente)
3. URL de acceso:
   - http://localhost:8084/productos

## 7) Pantallas / Rutas MVC
- GET /productos (listar)
- GET /productos/nuevo (formulario alta)
- POST /productos (crear)
- GET /productos/{id}/editar (editar)
- POST /productos/{id} (actualizar)
- POST /productos/{id}/borrar (eliminar)


## 8) Mejoras extra (opcional)
- Validaciones
- Estilos Bootstrap
- Búsqueda
- Pruebas
- Paginación
