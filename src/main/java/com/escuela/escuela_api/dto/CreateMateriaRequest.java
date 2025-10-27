package com.escuela.escuela_api.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateMateriaRequest {

    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombre;
}
