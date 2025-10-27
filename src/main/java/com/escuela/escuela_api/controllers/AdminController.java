package com.escuela.escuela_api.controllers;

import com.escuela.escuela_api.dto.AdminPasswordRequest;
import com.escuela.escuela_api.dto.CreateProfesorRequest;
import com.escuela.escuela_api.dto.ProfesorResponse;
import com.escuela.escuela_api.dto.UpdateProfesorRequest;
import com.escuela.escuela_api.dto.CreateAlumnoRequest;
import com.escuela.escuela_api.dto.AlumnoResponse;
import com.escuela.escuela_api.dto.UpdateAlumnoRequest;
import com.escuela.escuela_api.dto.CreateMateriaRequest;
import com.escuela.escuela_api.dto.MateriaResponse;
import com.escuela.escuela_api.dto.UpdateMateriaRequest;
import com.escuela.escuela_api.dto.CreateCarreraRequest;
import com.escuela.escuela_api.dto.CarreraResponse;
import com.escuela.escuela_api.dto.UpdateCarreraRequest;
import com.escuela.escuela_api.dto.CreateCursoRequest;
import com.escuela.escuela_api.dto.CursoResponse;
import com.escuela.escuela_api.dto.UpdateCursoRequest;
import com.escuela.escuela_api.dto.CalificacionResponse;
import com.escuela.escuela_api.dto.UpdateCalificacionRequest;
import com.escuela.escuela_api.models.*;
import com.escuela.escuela_api.services.AdminService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ADMIN')") // Toda esta clase está protegida solo para Admins
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;
    
    // ===================================================
    // ===          ENDPOINTS DE CREACIÓN (Create)     ===
    // ===================================================

    @PostMapping("/alumnos")
    public AlumnoResponse crearAlumno(@Valid @RequestBody CreateAlumnoRequest alumno) {
        return adminService.crearAlumno(alumno);
    }

    @PostMapping("/profesores")
    public ProfesorResponse crearProfesor(@Valid @RequestBody CreateProfesorRequest profesor) {
        return adminService.crearProfesor(profesor);
    }

    @PostMapping("/carreras")
    public CarreraResponse crearCarrera(@Valid @RequestBody CreateCarreraRequest carrera) {
        return adminService.crearCarrera(carrera);
    }

    @PostMapping("/materias")
    public MateriaResponse crearMateria(@Valid @RequestBody CreateMateriaRequest materia) {
        return adminService.crearMateria(materia);
    }

    @PostMapping("/cursos")
    public CursoResponse crearCurso(@Valid @RequestBody CreateCursoRequest curso) {
        return adminService.crearCurso(curso);
    }

    // ===================================================
    // ===          ENDPOINTS DE LECTURA (Read)        ===
    // ===================================================

    @GetMapping("/alumnos")
    public List<AlumnoResponse> getAlumnos() {
        return adminService.getAlumnos();
    }

    @GetMapping("/alumnos/{id}")
    public AlumnoResponse getAlumno(@PathVariable Long id) {
        return adminService.getAlumnoResponseById(id);
    }

    @GetMapping("/profesores/{id}")
    public ProfesorResponse getProfesor(@PathVariable Long id) {
        return adminService.getProfesorById(id);
    }

    @GetMapping("/materias/{id}")
    public MateriaResponse getMateria(@PathVariable Long id) {
        return adminService.getMateriaById(id);
    }

    @GetMapping("/carreras/{id}")
    public CarreraResponse getCarrera(@PathVariable Long id) {
        return adminService.getCarreraById(id);
    }

    @GetMapping("/cursos/{id}")
    public CursoResponse getCurso(@PathVariable Long id) {
        return adminService.getCursoById(id);
    }

    @GetMapping("/profesores")
    public List<ProfesorResponse> getProfesores() {
        return adminService.getProfesores();
    }

    @GetMapping("/carreras")
    public List<CarreraResponse> getCarreras() {
        return adminService.getCarreras();
    }

    @GetMapping("/materias")
    public List<MateriaResponse> getMaterias() {
        return adminService.getMaterias();
    }

    @GetMapping("/cursos")
    public List<CursoResponse> getCursos() {
        return adminService.getCursos();
    }

    @GetMapping("/carreras/exists")
    public ResponseEntity<Boolean> checkCarreraExists(@RequestParam String nombre, @RequestParam String codigo) {
        return ResponseEntity.ok(adminService.carreraExists(nombre, codigo));
    }

    // ===================================================
    // ===       ENDPOINTS DE ACTUALIZACIÓN (Update)   ===
    // ===================================================

    @PutMapping("/alumnos/{id}")
    public AlumnoResponse updateAlumno(@PathVariable Long id, @Valid @RequestBody UpdateAlumnoRequest alumnoDetails) {
        return adminService.updateAlumno(id, alumnoDetails);
    }

    @PutMapping("/profesores/{id}")
    public ProfesorResponse updateProfesor(@PathVariable Long id, @Valid @RequestBody UpdateProfesorRequest details) {
        return adminService.updateProfesor(id, details);
    }

    @PutMapping("/cursos/{id}")
    public CursoResponse updateCurso(@PathVariable Long id, @Valid @RequestBody UpdateCursoRequest details) {
        return adminService.updateCurso(id, details);
    }

    @PutMapping("/materias/{id}")
    public MateriaResponse updateMateria(@PathVariable Long id, @Valid @RequestBody UpdateMateriaRequest details) {
        return adminService.updateMateria(id, details);
    }

    @PutMapping("/carreras/{id}")
    public CarreraResponse updateCarrera(@PathVariable Long id, @Valid @RequestBody UpdateCarreraRequest details) {
        return adminService.updateCarrera(id, details);
    }

    @PutMapping("/calificaciones/{id}")
    public CalificacionResponse updateCalificacion(@PathVariable Long id, @Valid @RequestBody UpdateCalificacionRequest details) {
        return adminService.updateCalificacion(id, details);
    }

    // ===================================================
    // ===          ENDPOINTS DE BORRADO (Delete)      ===
    // ===================================================

    @DeleteMapping("/alumnos/{id}")
    public ResponseEntity<Void> deleteAlumno(@PathVariable Long id) {
        adminService.deleteAlumno(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/profesores/{id}")
    public ResponseEntity<Void> deleteProfesor(@PathVariable Long id) {
        adminService.deleteProfesor(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/cursos/{id}")
    public ResponseEntity<Void> deleteCurso(@PathVariable Long id) {
        adminService.deleteCurso(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/materias/{id}")
    public ResponseEntity<Void> deleteMateria(@PathVariable Long id) {
        adminService.deleteMateria(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/carreras/{id}")
    public ResponseEntity<Void> deleteCarrera(@PathVariable Long id) {
        adminService.deleteCarrera(id);
        return ResponseEntity.noContent().build();
    }

    // ===================================================
    // ===          ENDPOINTS DE RELACIONES            ===
    // ===================================================

    @PutMapping("/alumnos/{alumnoId}/carrera/{carreraId}")
    public AlumnoResponse asignarCarrera(@PathVariable Long alumnoId, @PathVariable Long carreraId) {
        return adminService.asignarCarreraAAlumno(alumnoId, carreraId);
    }

    @PostMapping("/carreras/{carreraId}/materias/{materiaId}")
    public CarreraResponse agregarMateriaACarrera(@PathVariable Long carreraId, @PathVariable Long materiaId) {
        return adminService.agregarMateriaACarrera(carreraId, materiaId);
    }

    @PostMapping("/cursos/{cursoId}/inscribir/{alumnoId}")
    public CursoResponse inscribirAlumno(@PathVariable Long cursoId, @PathVariable Long alumnoId) {
        return adminService.inscribirAlumnoEnCurso(cursoId, alumnoId);
    }

    @DeleteMapping("/cursos/{cursoId}/baja/{alumnoId}")
    public CursoResponse darDeBajaAlumno(@PathVariable Long cursoId, @PathVariable Long alumnoId) {
        return adminService.darDeBajaAlumnoDeCurso(cursoId, alumnoId);
    }

    @DeleteMapping("/alumnos/{alumnoId}/carrera")
    public AlumnoResponse quitarCarreraDeAlumno(@PathVariable Long alumnoId) {
        return adminService.quitarCarreraDeAlumno(alumnoId);
    }

    @GetMapping("/materias/{id}/carreras")
    public List<CarreraResponse> getCarrerasDeMateria(@PathVariable Long id) {
        return adminService.getCarrerasByMateriaId(id);
    }

    @PutMapping("/materias/{id}/carreras")
    public MateriaResponse updateCarrerasDeMateria(@PathVariable Long id, @RequestBody List<Long> carreraIds) {
        return adminService.updateCarrerasForMateria(id, carreraIds);
    }

    // ===================================================
    // ===       ENDPOINTS DE CAMBIO DE CONTRASEÑA     ===
    // ===================================================

    @PatchMapping("/alumnos/{id}/password")
    public ResponseEntity<Void> updateAlumnoPassword(@PathVariable Long id, @RequestBody AdminPasswordRequest request) {
        adminService.adminUpdatePassword(id, request.getNewPassword(), "ALUMNO");
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/profesores/{id}/password")
    public ResponseEntity<Void> updateProfesorPassword(@PathVariable Long id, @RequestBody AdminPasswordRequest request) {
        adminService.adminUpdatePassword(id, request.getNewPassword(), "PROFESOR");
        return ResponseEntity.ok().build();
    }

    // (Opcional: añadir endpoint para cambiar contraseña de Admin)
    @PatchMapping("/admins/{id}/password")
    public ResponseEntity<Void> updateAdminPassword(@PathVariable Long id, @RequestBody AdminPasswordRequest request) {
        adminService.adminUpdatePassword(id, request.getNewPassword(), "ADMIN");
        return ResponseEntity.ok().build();
    }
}