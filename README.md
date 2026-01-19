# CRUD MVC con Thymeleaf — RA3

## 1) Datos del alumno/a
- Producto (título, descripción, precio, estado)

## 2) Repositorio (fork) y gestión de versiones
- Repositorio base: https://github.com/profeInformatica101/tareasSpringBoot
- Enlace a MI fork: https://github.com/alvaroooom/tareasSpringBoot.git
- Nº de commits realizados: (mínimo 5)

## 3) Arquitectura
Explica brevemente cómo has organizado:
- Controller: Gestiona las rutas MVC (GET/POST), recibe peticiones del usuario, valida datos y devuelve vistas Thymeleaf.
- Service: Contiene la lógica de negocio (crear, listar, actualizar y borrar productos) y actúa como intermediario entre Controller y Repository.
- Repository: Interfaz que extiende JpaRepository para el acceso a datos (CRUD automático con JPA).
- Entity: Clase Producto anotada con @Entity que representa la tabla de la base de datos.

## 4) Base de datos elegida (marca una)
- [ ] H2

## 5) Configuración de la base de datos
### 5.1 Dependencias añadidas
<dependency>
   <groupId>com.h2database</groupId>
   <artifactId>h2</artifactId>
   <scope>runtime</scope>
</dependency>

### 5.2 application.properties / application.yml
   spring.h2.console.enabled=true
   spring.h2.console.path=/h2
   spring.datasource.url=jdbc:h2:mem:testdb 
   spring.datasource.username=sa
   spring.datasource.password=
   spring.datasource.driver-class-name=org.h2.Driver
   server.port=8090

### 5.3 Pasos para crear la BD (si aplica)
- H2 se crea automáticamente en memoria al arrancar la aplicación.

## 6) Cómo ejecutar el proyecto
1. Requisitos (Java versión, Maven/Gradle, DB instalada si aplica)
- Java 17
- Maven
2. Comando de arranque:
   - ./mvnw spring-boot:run   (o equivalente)
3. URL de acceso:
   - http://localhost:8090/productos

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
