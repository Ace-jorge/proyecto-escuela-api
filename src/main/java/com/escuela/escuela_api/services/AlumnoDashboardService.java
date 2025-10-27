package com.escuela.escuela_api.services;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.escuela.escuela_api.models.Alumno;
import com.escuela.escuela_api.models.Calificacion;
import com.escuela.escuela_api.models.Horario;
import com.escuela.escuela_api.repositories.CalificacionRepository;
import com.escuela.escuela_api.repositories.HorarioRepository;

@Service
public class AlumnoDashboardService {
    @Autowired private CalificacionRepository calificacionRepository;
    @Autowired private HorarioRepository horarioRepository;

    private Alumno getAlumnoLogueado() {
        return (Alumno) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public List<Calificacion> getMisCalificaciones() {
        Alumno alumno = getAlumnoLogueado();
        return calificacionRepository.findByAlumno(alumno);
    }

    public List<Horario> getMiHorario() {
        Alumno alumno = getAlumnoLogueado();
        // Extraemos la lista de cursos del alumno
        List<com.escuela.escuela_api.models.Curso> misCursos = List.copyOf(alumno.getCursos());
        // Buscamos todos los horarios que pertenecen a esos cursos
        return horarioRepository.findByCursoIn(misCursos);
    }
}