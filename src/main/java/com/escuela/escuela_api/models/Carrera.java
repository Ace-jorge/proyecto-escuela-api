package com.escuela.escuela_api.models;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode; // Importar EqualsAndHashCode
import java.util.Set;

@Data
@Entity
@Table(name = "carreras")
public class Carrera {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nombre;

    @Column(unique = true)
    private String codigo;

    // Relación: Una carrera tiene muchos alumnos
    @OneToMany(mappedBy = "carrera")
    @EqualsAndHashCode.Exclude
    private Set<Alumno> alumnos;

    // Relación: Una carrera tiene muchas materias (Plan de Estudios)
    @ManyToMany
    @JoinTable(
        name = "carreras_materias",
        joinColumns = @JoinColumn(name = "carrera_id"),
        inverseJoinColumns = @JoinColumn(name = "materia_id")
    )
    @EqualsAndHashCode.Exclude
    private Set<Materia> materias;
}