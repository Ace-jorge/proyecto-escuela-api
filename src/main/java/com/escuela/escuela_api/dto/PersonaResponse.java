package com.escuela.escuela_api.dto;

import java.time.LocalDate;
import lombok.Data;

@Data
public abstract class PersonaResponse {
    private Long id;
    private String nombre;
    private String apellido;
    private String matricula;
    private String email;
    private LocalDate fechaNacimiento;
    private Character genero;
}
