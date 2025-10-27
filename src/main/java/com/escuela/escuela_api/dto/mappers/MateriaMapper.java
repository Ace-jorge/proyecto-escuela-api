package com.escuela.escuela_api.dto.mappers;

import com.escuela.escuela_api.dto.CreateMateriaRequest;
import com.escuela.escuela_api.dto.MateriaResponse;
import com.escuela.escuela_api.dto.CarreraResponse; // New import
import com.escuela.escuela_api.models.Materia;
import lombok.RequiredArgsConstructor; // New import
import org.springframework.stereotype.Component;
import java.util.stream.Collectors; // New import

@Component
@RequiredArgsConstructor // For injecting CarreraMapper
public class MateriaMapper {

    private final CarreraMapper carreraMapper; // Injected

    public Materia toMateria(CreateMateriaRequest request) {
        if (request == null) {
            return null;
        }
        Materia materia = new Materia();
        materia.setNombre(request.getNombre());
        return materia;
    }

    public MateriaResponse toMateriaResponse(Materia materia) {
        if (materia == null) {
            return null;
        }
        MateriaResponse response = new MateriaResponse();
        response.setId(materia.getId());
        response.setNombre(materia.getNombre());
        // Populate the new field
        if (materia.getCarreras() != null) {
            response.setCarrerasAsignadas(materia.getCarreras().stream()
                                                .map(carreraMapper::toCarreraResponse)
                                                .collect(Collectors.toList()));
        }
        return response;
    }
}
