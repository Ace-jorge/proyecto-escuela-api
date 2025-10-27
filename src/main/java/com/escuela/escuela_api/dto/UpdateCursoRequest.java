package com.escuela.escuela_api.dto;

import lombok.Data;

@Data
public class UpdateCursoRequest {

    private String grupo;

    private Long materiaId;

    private Long profesorId;
}
