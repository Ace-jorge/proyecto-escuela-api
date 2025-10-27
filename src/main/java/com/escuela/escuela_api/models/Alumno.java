package com.escuela.escuela_api.models;

import java.util.List;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "alumnos")
public class Alumno extends Persona {

    public Alumno() {
        this.rol = "ROLE_ALUMNO";
    }

    @Column(name = "semestre")
    private int semestre; // El nivel en el que está el alumno (ej: 5)

    @ManyToOne
    @JoinColumn(name = "carrera_id") // Esta es la llave foránea
    private Carrera carrera;

    @ManyToMany(mappedBy = "alumnos")
    private Set<Curso> cursos;

    @OneToMany(mappedBy = "alumno")
    private List<Calificacion> calificaciones;
}