package com.escuela.escuela_api.models;
import java.time.DayOfWeek;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
@Data @Entity @Table(name = "horarios")
public class Horario {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private DayOfWeek diaSemana;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    @ManyToOne @JoinColumn(name = "curso_id", nullable = false)
    private Curso curso;
}