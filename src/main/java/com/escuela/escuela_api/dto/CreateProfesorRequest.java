package com.escuela.escuela_api.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CreateProfesorRequest extends CreatePersonaRequest {

    private String profesion;
}