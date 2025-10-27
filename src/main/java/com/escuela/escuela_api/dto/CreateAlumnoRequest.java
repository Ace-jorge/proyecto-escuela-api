package com.escuela.escuela_api.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CreateAlumnoRequest extends CreatePersonaRequest {

    private int semestre;

    private Long carreraId;
}