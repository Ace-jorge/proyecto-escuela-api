package com.escuela.escuela_api.services;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.escuela.escuela_api.models.Admin;
import com.escuela.escuela_api.models.Alumno;
import com.escuela.escuela_api.models.Calificacion;
import com.escuela.escuela_api.models.Carrera;
import com.escuela.escuela_api.models.Curso;
import com.escuela.escuela_api.models.Materia;
import com.escuela.escuela_api.models.Profesor;
import com.escuela.escuela_api.repositories.AdminRepository;
import com.escuela.escuela_api.repositories.AlumnoRepository;
import com.escuela.escuela_api.repositories.CalificacionRepository;
import com.escuela.escuela_api.repositories.CarreraRepository;
import com.escuela.escuela_api.repositories.CursoRepository;
import com.escuela.escuela_api.repositories.MateriaRepository;
import com.escuela.escuela_api.repositories.ProfesorRepository;
import com.escuela.escuela_api.dto.CreateProfesorRequest;
import com.escuela.escuela_api.dto.ProfesorResponse;
import com.escuela.escuela_api.dto.UpdateProfesorRequest;
import com.escuela.escuela_api.dto.mappers.ProfesorMapper;
import com.escuela.escuela_api.dto.CreateAlumnoRequest;
import com.escuela.escuela_api.dto.AlumnoResponse;
import com.escuela.escuela_api.dto.UpdateAlumnoRequest;
import com.escuela.escuela_api.dto.mappers.AlumnoMapper;
import com.escuela.escuela_api.dto.CreateMateriaRequest;
import com.escuela.escuela_api.dto.MateriaResponse;
import com.escuela.escuela_api.dto.UpdateMateriaRequest;
import com.escuela.escuela_api.dto.mappers.MateriaMapper;
import com.escuela.escuela_api.dto.CreateCarreraRequest;
import com.escuela.escuela_api.dto.CarreraResponse;
import com.escuela.escuela_api.dto.UpdateCarreraRequest;
import com.escuela.escuela_api.dto.mappers.CarreraMapper;
import com.escuela.escuela_api.dto.CreateCursoRequest;
import com.escuela.escuela_api.dto.CursoResponse;
import com.escuela.escuela_api.dto.UpdateCursoRequest;
import com.escuela.escuela_api.dto.mappers.CursoMapper;
import com.escuela.escuela_api.dto.CalificacionResponse;
import com.escuela.escuela_api.dto.UpdateCalificacionRequest;
import com.escuela.escuela_api.dto.mappers.CalificacionMapper;
import com.escuela.escuela_api.exceptions.ConflictException;
import com.escuela.escuela_api.exceptions.ResourceNotFoundException;
import java.util.stream.Collectors;


import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor // Usamos Lombok para inyectar las dependencias
public class AdminService {

    // --- REPOSITORIOS (Inyectados por el constructor de Lombok) ---
    private final AlumnoRepository alumnoRepository;
    private final ProfesorRepository profesorRepository;
    private final MateriaRepository materiaRepository;
    private final CursoRepository cursoRepository;
    private final CarreraRepository carreraRepository;
    private final CalificacionRepository calificacionRepository;
    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;
    private final ProfesorMapper profesorMapper;
    private final AlumnoMapper alumnoMapper;
    private final MateriaMapper materiaMapper;
    private final CarreraMapper carreraMapper;
    private final CursoMapper cursoMapper;
    private final CalificacionMapper calificacionMapper;

    // ===================================================
    // ===          MÉTODOS DE CREACIÓN (Create)       ===
    // ===================================================

    @Transactional
    public AlumnoResponse crearAlumno(CreateAlumnoRequest request) {
        // Validar que no exista
        if (alumnoRepository.findByMatricula(request.getMatricula()).isPresent() ||
            alumnoRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new ConflictException("Un alumno con esta matrícula o email ya existe");
        }
        
        Alumno alumno = alumnoMapper.toAlumno(request);
        
        // Encriptar contraseña
        alumno.setPassword(passwordEncoder.encode(request.getPassword()));

        // Asignar carrera si se proporcionó
        if (request.getCarreraId() != null) {
            Carrera carrera = carreraRepository.findById(request.getCarreraId())
                .orElseThrow(() -> new ResourceNotFoundException("Carrera no encontrada con ID: " + request.getCarreraId()));
            alumno.setCarrera(carrera);
        }
        
        Alumno savedAlumno = alumnoRepository.save(alumno);
        return alumnoMapper.toAlumnoResponse(savedAlumno);
    }

    @Transactional
    public ProfesorResponse crearProfesor(CreateProfesorRequest request) {
        if (profesorRepository.findByMatricula(request.getMatricula()).isPresent() ||
            profesorRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new ConflictException("Un profesor con esta matrícula o email ya existe");
        }
        Profesor profesor = profesorMapper.toProfesor(request);
        profesor.setPassword(passwordEncoder.encode(request.getPassword()));
        
        Profesor savedProfesor = profesorRepository.save(profesor);
        return profesorMapper.toProfesorResponse(savedProfesor);
    }
    
    @Transactional
    public CarreraResponse crearCarrera(CreateCarreraRequest request) {
        if (carreraRepository.findByNombre(request.getNombre()).isPresent() || carreraRepository.findByCodigo(request.getCodigo()).isPresent()) {
            throw new ConflictException("Una carrera con este nombre o código ya existe");
        }
        Carrera carrera = carreraMapper.toCarrera(request);
        Carrera savedCarrera = carreraRepository.save(carrera);
        return carreraMapper.toCarreraResponse(savedCarrera);
    }

    @Transactional
    public MateriaResponse crearMateria(CreateMateriaRequest request) {
        if (materiaRepository.findByNombre(request.getNombre()).isPresent()) {
            throw new ConflictException("Una materia con este nombre ya existe");
        }
        Materia materia = materiaMapper.toMateria(request);
        Materia savedMateria = materiaRepository.save(materia);
        return materiaMapper.toMateriaResponse(savedMateria);
    }

    @Transactional
    public CursoResponse crearCurso(CreateCursoRequest request) {
        Profesor profesor = profesorRepository.findById(request.getProfesorId())
            .orElseThrow(() -> new ResourceNotFoundException("Profesor no encontrado con ID: " + request.getProfesorId()));
        Materia materia = materiaRepository.findById(request.getMateriaId())
            .orElseThrow(() -> new ResourceNotFoundException("Materia no encontrada con ID: " + request.getMateriaId()));

        // Validar que no exista un curso idéntico
        if (cursoRepository.findByMateriaAndProfesorAndGrupo(materia, profesor, request.getGrupo()).isPresent()) {
            throw new ConflictException("Ya existe un curso con la misma materia, profesor y grupo");
        }

        Curso curso = cursoMapper.toCurso(request);
        curso.setProfesor(profesor);
        curso.setMateria(materia);
        
        Curso savedCurso = cursoRepository.save(curso);
        return cursoMapper.toCursoResponse(savedCurso);
    }

    // ===================================================
    // ===          MÉTODOS DE LECTURA (Read)          ===
    // ===================================================

    public List<AlumnoResponse> getAlumnos() {
        return alumnoRepository.findAll()
                .stream()
                .map(alumnoMapper::toAlumnoResponse)
                .collect(Collectors.toList());
    }
    public List<ProfesorResponse> getProfesores() {
        return profesorRepository.findAll()
                .stream()
                .map(profesorMapper::toProfesorResponse)
                .collect(Collectors.toList());
    }
    public List<CarreraResponse> getCarreras() {
        return carreraRepository.findAll()
                .stream()
                .map(carreraMapper::toCarreraResponse)
                .collect(Collectors.toList());
    }
    public List<MateriaResponse> getMaterias() {
        return materiaRepository.findAll()
                .stream()
                .map(materiaMapper::toMateriaResponse)
                .collect(Collectors.toList());
    }
    public List<CursoResponse> getCursos() {
        return cursoRepository.findAll()
                .stream()
                .map(cursoMapper::toCursoResponse)
                .collect(Collectors.toList());
    }
    public Alumno getAlumnoById(Long id) {
        return alumnoRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Alumno no encontrado con ID: " + id));
    }

    public AlumnoResponse getAlumnoResponseById(Long id) {
        Alumno alumno = getAlumnoById(id);
        return alumnoMapper.toAlumnoResponse(alumno);
    }

    public ProfesorResponse getProfesorById(Long id) {
        Profesor profesor = profesorRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Profesor no encontrado con ID: " + id));
        return profesorMapper.toProfesorResponse(profesor);
    }

    public MateriaResponse getMateriaById(Long id) {
        Materia materia = materiaRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Materia no encontrada con ID: " + id));
        return materiaMapper.toMateriaResponse(materia);
    }

    public CarreraResponse getCarreraById(Long id) {
        Carrera carrera = carreraRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Carrera no encontrada con ID: " + id));
        return carreraMapper.toCarreraResponse(carrera);
    }

    public CursoResponse getCursoById(Long id) {
        Curso curso = cursoRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Curso no encontrado con ID: " + id));
        return cursoMapper.toCursoResponse(curso);
    }

    public boolean carreraExists(String nombre, String codigo) {
        if (nombre != null && carreraRepository.findByNombre(nombre).isPresent()) {
            return true;
        }
        if (codigo != null && carreraRepository.findByCodigo(codigo).isPresent()) {
            return true;
        }
        return false;
    }
    // (Puedes añadir más métodos GetById... para Profesor, Curso, etc.)


    // ===================================================
    // ===       MÉTODOS DE ACTUALIZACIÓN (Update)     ===
    // ===================================================

    @Transactional
    public AlumnoResponse updateAlumno(Long id, UpdateAlumnoRequest details) {
        Alumno alumno = getAlumnoById(id); // Reutilizamos el método de lectura
        
        if (details.getNombre() != null) {
            alumno.setNombre(details.getNombre());
        }
        if (details.getApellido() != null) {
            alumno.setApellido(details.getApellido());
        }
        if (details.getMatricula() != null) {
            alumno.setMatricula(details.getMatricula());
        }
        if (details.getEmail() != null) {
            alumno.setEmail(details.getEmail());
        }
        if (details.getFechaNacimiento() != null) {
            alumno.setFechaNacimiento(details.getFechaNacimiento());
        }
        if (details.getGenero() != null) {
            alumno.setGenero(details.getGenero());
        }
        if (details.getSemestre() != null) {
            alumno.setSemestre(details.getSemestre());
        }
        if (details.getCarreraId() != null) {
            Carrera carrera = carreraRepository.findById(details.getCarreraId())
                .orElseThrow(() -> new ResourceNotFoundException("Carrera no encontrada con ID: " + details.getCarreraId()));
            alumno.setCarrera(carrera);
        }
        
        Alumno updatedAlumno = alumnoRepository.save(alumno);
        return alumnoMapper.toAlumnoResponse(updatedAlumno);
    }

    @Transactional
    public ProfesorResponse updateProfesor(Long id, UpdateProfesorRequest details) {
        Profesor prof = profesorRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Profesor no encontrado"));
            
        // Actualizar solo los campos que no son nulos en el request
        if (details.getNombre() != null) {
            prof.setNombre(details.getNombre());
        }
        if (details.getApellido() != null) {
            prof.setApellido(details.getApellido());
        }
        if (details.getMatricula() != null) {
            prof.setMatricula(details.getMatricula());
        }
        if (details.getEmail() != null) {
            prof.setEmail(details.getEmail());
        }
        if (details.getProfesion() != null) {
            prof.setProfesion(details.getProfesion());
        }
        if (details.getFechaNacimiento() != null) {
            prof.setFechaNacimiento(details.getFechaNacimiento());
        }
        if (details.getGenero() != null) {
            prof.setGenero(details.getGenero());
        }
        
        Profesor updatedProfesor = profesorRepository.save(prof);
        return profesorMapper.toProfesorResponse(updatedProfesor);
    }

    @Transactional
    public CursoResponse updateCurso(Long id, UpdateCursoRequest details) {
        Curso curso = cursoRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Curso no encontrado con ID: " + id));
            
        if (details.getGrupo() != null) {
            curso.setGrupo(details.getGrupo());
        }
        if (details.getProfesorId() != null) {
            Profesor prof = profesorRepository.findById(details.getProfesorId())
                .orElseThrow(() -> new ResourceNotFoundException("Profesor no encontrado con ID: " + details.getProfesorId()));
            curso.setProfesor(prof);
        }
        if (details.getMateriaId() != null) {
            Materia mat = materiaRepository.findById(details.getMateriaId())
                .orElseThrow(() -> new ResourceNotFoundException("Materia no encontrada con ID: " + details.getMateriaId()));
            curso.setMateria(mat);
        }
        
        Curso updatedCurso = cursoRepository.save(curso);
        return cursoMapper.toCursoResponse(updatedCurso);
    }

    @Transactional
    public MateriaResponse updateMateria(Long id, UpdateMateriaRequest details) {
        Materia materia = materiaRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Materia no encontrada con ID: " + id));
        
        if (details.getNombre() != null) {
            materia.setNombre(details.getNombre());
        }
        
        Materia updatedMateria = materiaRepository.save(materia);
        return materiaMapper.toMateriaResponse(updatedMateria);
    }

    @Transactional
    public CarreraResponse updateCarrera(Long id, UpdateCarreraRequest details) {
        Carrera carrera = carreraRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Carrera no encontrada con ID: " + id));
        
        if (details.getNombre() != null) {
            carrera.setNombre(details.getNombre());
        }
        if (details.getCodigo() != null) {
            carrera.setCodigo(details.getCodigo());
        }
        
        Carrera updatedCarrera = carreraRepository.save(carrera);
        return carreraMapper.toCarreraResponse(updatedCarrera);
    }

    @Transactional
    public CalificacionResponse updateCalificacion(Long id, UpdateCalificacionRequest details) {
        Calificacion cal = calificacionRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Calificación no encontrada con ID: " + id));
        
        if (details.getValor() != null) {
            cal.setValor(details.getValor());
        }
        
        Calificacion updatedCalificacion = calificacionRepository.save(cal);
        return calificacionMapper.toCalificacionResponse(updatedCalificacion);
    }

    // ===================================================
    // ===          MÉTODOS DE BORRADO (Delete)        ===
    // ===================================================

    public void deleteAlumno(Long id) {
        // (En un sistema real, primero deberías desinscribirlo de todos los cursos)
        alumnoRepository.deleteById(id);
    }
    public void deleteProfesor(Long id) {
        // (En un sistema real, primero deberías reasignar sus cursos)
        profesorRepository.deleteById(id);
    }
    public void deleteCurso(Long id) {
        // (En un sistema real, primero deberías eliminar inscripciones y calificaciones)
        cursoRepository.deleteById(id);
    }

    public void deleteMateria(Long id) {
        materiaRepository.deleteById(id);
    }

    public void deleteCarrera(Long id) {
        carreraRepository.deleteById(id);
    }

    // ===================================================
    // ===        MÉTODOS DE RELACIONES                ===
    // ===================================================

    @Transactional
    public AlumnoResponse asignarCarreraAAlumno(Long alumnoId, Long carreraId) {
        Alumno alumno = getAlumnoById(alumnoId);
        Carrera carrera = carreraRepository.findById(carreraId)
            .orElseThrow(() -> new ResourceNotFoundException("Carrera no encontrada con ID: " + carreraId));
        
        alumno.setCarrera(carrera);
        alumno.setSemestre(1); // Asignamos semestre 1 al inscribir
        Alumno savedAlumno = alumnoRepository.save(alumno);
        return alumnoMapper.toAlumnoResponse(savedAlumno);
    }
    
    @Transactional
    public CarreraResponse agregarMateriaACarrera(Long carreraId, Long materiaId) {
        Carrera carrera = carreraRepository.findById(carreraId)
            .orElseThrow(() -> new ResourceNotFoundException("Carrera no encontrada con ID: " + carreraId));
        Materia materia = materiaRepository.findById(materiaId)
            .orElseThrow(() -> new ResourceNotFoundException("Materia no encontrada con ID: " + materiaId));
        
        carrera.getMaterias().add(materia); // Agrega al plan de estudios
        Carrera savedCarrera = carreraRepository.save(carrera);
        return carreraMapper.toCarreraResponse(savedCarrera);
    }

    @Transactional
    public CursoResponse inscribirAlumnoEnCurso(Long cursoId, Long alumnoId) {
        Curso curso = cursoRepository.findById(cursoId)
            .orElseThrow(() -> new ResourceNotFoundException("Curso no encontrado con ID: " + cursoId));
        Alumno alumno = getAlumnoById(alumnoId);
        
        // (Lógica de validación: ¿carrera compatible? ¿cupo?)
        
        curso.getAlumnos().add(alumno); // Inscribe al alumno
        Curso savedCurso = cursoRepository.save(curso);
        return cursoMapper.toCursoResponse(savedCurso);
    }

    @Transactional
    public CursoResponse darDeBajaAlumnoDeCurso(Long cursoId, Long alumnoId) {
        Curso curso = cursoRepository.findById(cursoId)
            .orElseThrow(() -> new ResourceNotFoundException("Curso no encontrado con ID: " + cursoId));
        Alumno alumno = getAlumnoById(alumnoId);
        
        curso.getAlumnos().remove(alumno); // Da de baja al alumno
        Curso savedCurso = cursoRepository.save(curso);
        return cursoMapper.toCursoResponse(savedCurso);
    }

    @Transactional
    public AlumnoResponse quitarCarreraDeAlumno(Long alumnoId) {
        Alumno alumno = getAlumnoById(alumnoId);
        alumno.setCarrera(null);
        Alumno savedAlumno = alumnoRepository.save(alumno);
        return alumnoMapper.toAlumnoResponse(savedAlumno);
    }

    public List<CarreraResponse> getCarrerasByMateriaId(Long materiaId) {
        Materia materia = materiaRepository.findById(materiaId)
            .orElseThrow(() -> new ResourceNotFoundException("Materia no encontrada con ID: " + materiaId));
        return materia.getCarreras().stream()
            .map(carreraMapper::toCarreraResponse)
            .collect(Collectors.toList());
    }

    @Transactional
    public MateriaResponse updateCarrerasForMateria(Long materiaId, List<Long> carreraIds) {
        Materia materia = materiaRepository.findById(materiaId)
            .orElseThrow(() -> new ResourceNotFoundException("Materia no encontrada con ID: " + materiaId));

        materia.getCarreras().clear(); // Limpiar asignaciones existentes

        if (carreraIds != null && !carreraIds.isEmpty()) {
            List<Carrera> nuevasCarreras = carreraRepository.findAllById(carreraIds);
            if (nuevasCarreras.size() != carreraIds.size()) {
                // Esto significa que algunos IDs de carrera no se encontraron
                throw new ResourceNotFoundException("Una o más carreras proporcionadas no fueron encontradas.");
            }
            materia.getCarreras().addAll(nuevasCarreras);
        }

        Materia updatedMateria = materiaRepository.save(materia);
        return materiaMapper.toMateriaResponse(updatedMateria);
    }

    // ===================================================
    // ===     MÉTODOS DE CAMBIO DE CONTRASEÑA (Admin) ===
    // ===================================================

    @Transactional
    public void adminUpdatePassword(Long userId, String newPassword, String role) {
        String encodedPassword = passwordEncoder.encode(newPassword);

        switch (role.toUpperCase()) {
            case "ALUMNO" -> {
                Alumno alumno = getAlumnoById(userId);
                alumno.setPassword(encodedPassword);
                alumnoRepository.save(alumno);
            }
            case "PROFESOR" -> {
                Profesor profesor = profesorRepository.findById(userId)
                        .orElseThrow(() -> new ResourceNotFoundException("Profesor no encontrado"));
                profesor.setPassword(encodedPassword);
                profesorRepository.save(profesor);
            }
            case "ADMIN" -> {
                Admin admin = adminRepository.findById(userId)
                        .orElseThrow(() -> new ResourceNotFoundException("Admin no encontrado"));
                admin.setPassword(encodedPassword);
                adminRepository.save(admin);
            }
            default -> throw new IllegalArgumentException("Rol de usuario no válido");
        }
    }
}