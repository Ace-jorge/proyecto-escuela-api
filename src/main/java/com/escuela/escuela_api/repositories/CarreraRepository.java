package com.escuela.escuela_api.repositories;

import com.escuela.escuela_api.models.Carrera;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarreraRepository extends JpaRepository<Carrera, Long>{
    Optional<Carrera> findByNombre(String nombre);
    Optional<Carrera> findByCodigo(String codigo);
}