package com.escuela.escuela_api.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class UpdateProfesorRequest extends UpdatePersonaRequest {

    private String profesion;
}