package com.escuela.escuela_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateCursoRequest {

    @NotBlank(message = "El grupo no puede estar vacío")
    private String grupo;

    @NotNull(message = "El id de la materia no puede ser nulo")
    private Long materiaId;

    @NotNull(message = "El id del profesor no puede ser nulo")
    private Long profesorId;
}
