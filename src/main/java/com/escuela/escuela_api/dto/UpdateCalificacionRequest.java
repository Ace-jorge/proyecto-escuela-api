package com.escuela.escuela_api.dto;

import java.math.BigDecimal;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.DecimalMax;
import lombok.Data;

@Data
public class UpdateCalificacionRequest {

    @NotNull(message = "El valor de la calificación no puede ser nulo")
    @DecimalMin(value = "0.0", inclusive = true, message = "La calificación debe ser como mínimo 0.0")
    @DecimalMax(value = "10.0", inclusive = true, message = "La calificación debe ser como máximo 10.0")
    private BigDecimal valor;
}
