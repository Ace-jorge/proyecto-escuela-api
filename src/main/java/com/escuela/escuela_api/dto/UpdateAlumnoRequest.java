package com.escuela.escuela_api.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class UpdateAlumnoRequest extends UpdatePersonaRequest {

    private Integer semestre;

    private Long carreraId;
}