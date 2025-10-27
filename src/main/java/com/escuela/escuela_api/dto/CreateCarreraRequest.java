package com.escuela.escuela_api.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateCarreraRequest {

    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombre;

    @NotBlank(message = "El código no puede estar vacío")
    private String codigo;
}
