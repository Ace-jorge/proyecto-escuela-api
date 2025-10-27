package com.escuela.escuela_api.models;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data; // Importar Set
import lombok.EqualsAndHashCode;
@Data @Entity @Table(name = "materias")
public class Materia {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String nombre;

    @ManyToMany(mappedBy = "materias")
    @EqualsAndHashCode.Exclude
    private Set<Carrera> carreras;
}
