package com.escuela.escuela_api.repositories;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.escuela.escuela_api.models.Alumno;
public interface AlumnoRepository extends JpaRepository<Alumno, Long> {
    Optional<Alumno> findByEmail(String email);
    Optional<Alumno> findByMatricula(String matricula);
}