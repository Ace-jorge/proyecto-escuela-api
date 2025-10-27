package com.escuela.escuela_api.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.escuela.escuela_api.models.Calificacion;
import com.escuela.escuela_api.models.Curso;
import com.escuela.escuela_api.models.Profesor;
import com.escuela.escuela_api.repositories.CalificacionRepository;
import com.escuela.escuela_api.repositories.CursoRepository;

@Service
public class CalificacionService {
    @Autowired private CalificacionRepository calificacionRepository;
    @Autowired private CursoRepository cursoRepository;

    public Calificacion guardarCalificacion(Calificacion calificacion) {
        // 1. Obtener al profesor que está haciendo la petición
        Profesor profesorLogueado = (Profesor) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
        // 2. Obtener el curso que se quiere calificar
        Curso curso = cursoRepository.findById(calificacion.getCurso().getId())
                .orElseThrow(() -> new RuntimeException("Curso no encontrado"));

        // 3. ¡VERIFICACIÓN DE SEGURIDAD!
        // ¿Es este profesor el dueño del curso?
        if (!curso.getProfesor().getId().equals(profesorLogueado.getId())) {
            throw new AccessDeniedException("No tienes permiso para calificar este curso");
        }
        
        // 4. (Opcional) Verificar que el alumno esté en el curso
        boolean alumnoInscrito = curso.getAlumnos().stream()
                .anyMatch(a -> a.getId().equals(calificacion.getAlumno().getId()));
        if (!alumnoInscrito) {
            throw new RuntimeException("El alumno no está inscrito en este curso");
        }

        // 5. Guardar la calificación
        return calificacionRepository.save(calificacion);
    }
}