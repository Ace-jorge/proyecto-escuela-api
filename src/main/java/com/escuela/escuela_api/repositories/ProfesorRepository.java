package com.escuela.escuela_api.repositories;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.escuela.escuela_api.models.Profesor;
public interface ProfesorRepository extends JpaRepository<Profesor, Long> {
    Optional<Profesor> findByEmail(String email);
    Optional<Profesor> findByMatricula(String matricula);
}