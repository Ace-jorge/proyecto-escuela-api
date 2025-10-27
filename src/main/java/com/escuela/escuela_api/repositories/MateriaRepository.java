package com.escuela.escuela_api.repositories;
import com.escuela.escuela_api.models.Materia;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
public interface MateriaRepository extends JpaRepository<Materia, Long>{
    Optional<Materia> findByNombre(String nombre);
}