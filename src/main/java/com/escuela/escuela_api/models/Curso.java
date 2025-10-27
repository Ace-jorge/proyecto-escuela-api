package com.escuela.escuela_api.models;
import jakarta.persistence.*;
import lombok.Data;
import java.util.List;
import java.util.Set;
@Data @Entity @Table(name = "cursos")
public class Curso {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String grupo;
    @ManyToOne @JoinColumn(name = "materia_id", nullable = false)
    private Materia materia;
    @ManyToOne @JoinColumn(name = "profesor_id", nullable = false)
    private Profesor profesor;
    @ManyToMany
    @JoinTable(
        name = "cursos_alumnos",
        joinColumns = @JoinColumn(name = "curso_id"),
        inverseJoinColumns = @JoinColumn(name = "alumno_id")
    )
    private Set<Alumno> alumnos;
    @OneToMany(mappedBy = "curso")
    private List<Horario> horarios;
    @OneToMany(mappedBy = "curso")
    private List<Calificacion> calificaciones;
}