package com.escuela.escuela_api.dto;

import lombok.Data;
import java.util.List;

@Data
public class MateriaResponse {
    private Long id;
    private String nombre;
    private List<CarreraResponse> carrerasAsignadas;
}
