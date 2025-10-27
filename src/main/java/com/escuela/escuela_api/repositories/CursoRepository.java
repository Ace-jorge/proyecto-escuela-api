package com.escuela.escuela_api.repositories;
import com.escuela.escuela_api.models.Materia;
import com.escuela.escuela_api.models.Profesor;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.escuela.escuela_api.models.Curso;
public interface CursoRepository extends JpaRepository<Curso, Long>{
    Optional<Curso> findByMateriaAndProfesorAndGrupo(Materia materia, Profesor profesor, String grupo);
}