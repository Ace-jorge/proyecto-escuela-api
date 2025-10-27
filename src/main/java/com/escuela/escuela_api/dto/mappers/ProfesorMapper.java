package com.escuela.escuela_api.dto.mappers;

import com.escuela.escuela_api.dto.CreateProfesorRequest;
import com.escuela.escuela_api.dto.ProfesorResponse;
import com.escuela.escuela_api.models.Profesor;
import org.springframework.stereotype.Component;

@Component
public class ProfesorMapper {

    public Profesor toProfesor(CreateProfesorRequest request) {
        if (request == null) {
            return null;
        }
        Profesor profesor = new Profesor();
        profesor.setNombre(request.getNombre());
        profesor.setApellido(request.getApellido());
        profesor.setMatricula(request.getMatricula());
        profesor.setEmail(request.getEmail());
        profesor.setPassword(request.getPassword());
        profesor.setProfesion(request.getProfesion());
        profesor.setFechaNacimiento(request.getFechaNacimiento());
        profesor.setGenero(request.getGenero());
        return profesor;
    }

    public ProfesorResponse toProfesorResponse(Profesor profesor) {
        if (profesor == null) {
            return null;
        }
        ProfesorResponse response = new ProfesorResponse();
        response.setId(profesor.getId());
        response.setNombre(profesor.getNombre());
        response.setApellido(profesor.getApellido());
        response.setMatricula(profesor.getMatricula());
        response.setEmail(profesor.getEmail());
        response.setProfesion(profesor.getProfesion());
        response.setFechaNacimiento(profesor.getFechaNacimiento());
        response.setGenero(profesor.getGenero());
        return response;
    }
}
