package com.escuela.escuela_api.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ProfesorResponse extends PersonaResponse {
    private String profesion;
}