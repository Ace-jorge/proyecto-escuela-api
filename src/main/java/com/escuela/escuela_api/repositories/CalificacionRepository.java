package com.escuela.escuela_api.repositories;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.escuela.escuela_api.models.Alumno;
import com.escuela.escuela_api.models.Calificacion;
public interface CalificacionRepository extends JpaRepository<Calificacion, Long> {
    List<Calificacion> findByAlumno(Alumno alumno);
}