package com.escuela.escuela_api.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "profesores")
public class Profesor extends Persona {

    public Profesor() {
        this.rol = "ROLE_PROFESOR";
    }

    private String profesion;
    
    @OneToMany(mappedBy = "profesor")
    private Set<Curso> cursos;
}
