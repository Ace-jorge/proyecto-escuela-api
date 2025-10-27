package com.escuela.escuela_api.dto;

import lombok.Data;

@Data
public class CursoResponse {
    private Long id;
    private String grupo;
    private String nombreMateria;
    private String nombreProfesor;
}
