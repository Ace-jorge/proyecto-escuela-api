package com.escuela.escuela_api.dto;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class CalificacionResponse {
    private Long id;
    private BigDecimal valor;
    private String nombreAlumno;
    private String nombreMateria;
    private String grupoCurso;
}
