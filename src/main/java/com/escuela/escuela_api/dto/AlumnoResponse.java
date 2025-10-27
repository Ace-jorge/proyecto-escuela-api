package com.escuela.escuela_api.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AlumnoResponse extends PersonaResponse {
    private int semestre;
    private String nombreCarrera;
}