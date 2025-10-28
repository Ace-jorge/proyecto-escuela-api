# API REST de Sistema de Gestión Escolar (En Desarrollo)

API RESTful construida con Spring Boot para la administración de una institución educativa. Permite gestionar alumnos, profesores, carreras, materias y cursos, implementando seguridad basada en roles (Admin, Profesor, Alumno).

---

## Estado del Proyecto
**Este proyecto está actualmente en desarrollo.** El objetivo es crear un portafolio robusto demostrando buenas prácticas en el desarrollo backend. La Fase 1 (Módulo de Administración) está mayormente completada.

---

## Stack Tecnológico
Este proyecto está construido con:
* **Lenguaje:** Java 17
* **Framework:** Spring Boot 3
* **Seguridad:** Spring Security 6 (con autenticación por Roles y JWT)
* **Base de Datos:** Spring Data JPA (Hibernate) con MySQL
* **Validación:** Spring Boot Validation
* **Utilidades:** Lombok
* **Build:** Maven
* **Pruebas:** JUnit 5 & MockMvc (Pruebas de Integración)

---

## Cómo Ejecutarlo Localmente

1.  **Clonar el repositorio:**
    ```bash
    git clone [https://github.com/TU_USUARIO/proyecto-escuela-api.git](https://github.com/TU_USUARIO/proyecto-escuela-api.git)
    cd proyecto-escuela-api
    ```

2.  **Base de Datos:**
    * Asegúrate de tener MySQL corriendo en `localhost:3306`.
    * Crea una base de datos llamada `escuela_db`. (El proyecto la creará si no existe gracias a la configuración).

3.  **Configurar Variables de Entorno:**
    * Ve a `src/main/resources/`.
    * Copia el archivo `application.properties.example` y renómbralo a `application.properties`.
    * Abre el nuevo `application.properties` y cambia los valores de `TU_PASSWORD_DE_BD_AQUI` y `TU_SECRET_KEY_JWT_AQUI`.

4.  **Ejecutar la aplicación:**
    ```bash
    mvn spring-boot:run
    ```

5.  **Usuario Administrador por Defecto:**
    * La aplicación crea automáticamente un usuario Admin al iniciar.
    * **Email:** `admin@escuela.com`
    * **Password:** `admin123`

---

## Funcionalidades (Roadmap)

### Fase 1: Módulo de Administración (Casi Completo)
- [x] **Seguridad:** Login y protección de endpoints por rol (Admin, Profesor, Alumno).
- [x] **Gestión (CRUD):** Endpoints de Admin para crear, leer, actualizar y borrar:
    - [x] Alumnos
    - [x] Profesores
    - [x] Carreras
    - [x] Materias
    - [x] Cursos
- [x] **Gestión de Relaciones:**
    - [x] Asignar carrera a un alumno.
    - [x] Agregar materia al plan de estudios de una carrera.
    - [x] Inscribir alumno en un curso.
- [x] **Manejo de Excepciones:** Handler global para excepciones (NotFound, Conflict, etc.).
- [x] **Pruebas:** Pruebas de integración para los endpoints de Admin.

### Fase 2: Portales de Usuario (En Desarrollo)
- [ ] **Dashboard Profesor:**
    - [ ] Ver sus cursos asignados.
    - [ ] Asignar/Actualizar calificaciones de alumnos en sus cursos.
- [ ] **Dashboard Alumno:**
    - [ ] Ver su horario (basado en cursos inscritos).
    - [ ] Ver sus calificaciones.
- [ ] **Pruebas Unitarias:** Añadir más pruebas unitarias y de integración para cubrir los servicios y los nuevos dashboards.
