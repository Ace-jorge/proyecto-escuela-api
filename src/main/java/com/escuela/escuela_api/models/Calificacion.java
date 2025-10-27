package com.escuela.escuela_api.models;
import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
@Data @Entity @Table(name = "calificaciones")
public class Calificacion {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(precision = 4, scale = 2)
    private BigDecimal valor;
    @ManyToOne @JoinColumn(name = "alumno_id", nullable = false)
    private Alumno alumno;
    @ManyToOne @JoinColumn(name = "curso_id", nullable = false)
    private Curso curso;
}