package com.escuela.escuela_api.dto.mappers;

import com.escuela.escuela_api.dto.CarreraResponse;
import com.escuela.escuela_api.dto.CreateCarreraRequest;
import com.escuela.escuela_api.models.Carrera;
import org.springframework.stereotype.Component;

@Component
public class CarreraMapper {

    public Carrera toCarrera(CreateCarreraRequest request) {
        if (request == null) {
            return null;
        }
        Carrera carrera = new Carrera();
        carrera.setNombre(request.getNombre());
        carrera.setCodigo(request.getCodigo());
        return carrera;
    }

    public CarreraResponse toCarreraResponse(Carrera carrera) {
        if (carrera == null) {
            return null;
        }
        CarreraResponse response = new CarreraResponse();
        response.setId(carrera.getId());
        response.setNombre(carrera.getNombre());
        response.setCodigo(carrera.getCodigo());
        return response;
    }
}
