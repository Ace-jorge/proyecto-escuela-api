package com.escuela.escuela_api.repositories;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.escuela.escuela_api.models.Curso;
import com.escuela.escuela_api.models.Horario;
public interface HorarioRepository extends JpaRepository<Horario, Long> {
    List<Horario> findByCursoIn(List<Curso> cursos);
}