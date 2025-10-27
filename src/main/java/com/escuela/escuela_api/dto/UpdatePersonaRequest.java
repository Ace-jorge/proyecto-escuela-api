package com.escuela.escuela_api.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public abstract class UpdatePersonaRequest {

    private String nombre;

    private String apellido;

    private String matricula;

    @Email(message = "El formato del email no es válido")
    private String email;

    private LocalDate fechaNacimiento;

    private Character genero;
}
