package com.escuela.escuela_api.dto.mappers;

import com.escuela.escuela_api.dto.AlumnoResponse;
import com.escuela.escuela_api.dto.CreateAlumnoRequest;
import com.escuela.escuela_api.models.Alumno;
import org.springframework.stereotype.Component;

@Component
public class AlumnoMapper {

    public Alumno toAlumno(CreateAlumnoRequest request) {
        if (request == null) {
            return null;
        }
        Alumno alumno = new Alumno();
        alumno.setNombre(request.getNombre());
        alumno.setApellido(request.getApellido());
        alumno.setMatricula(request.getMatricula());
        alumno.setEmail(request.getEmail());
        alumno.setPassword(request.getPassword());
        alumno.setFechaNacimiento(request.getFechaNacimiento());
        alumno.setGenero(request.getGenero());
        if (request.getSemestre() != 0) {
            alumno.setSemestre(request.getSemestre());
        } else {
            alumno.setSemestre(1); // Default to 1
        }
        // Carrera will be handled in the service
        return alumno;
    }

    public AlumnoResponse toAlumnoResponse(Alumno alumno) {
        if (alumno == null) {
            return null;
        }
        AlumnoResponse response = new AlumnoResponse();
        response.setId(alumno.getId());
        response.setNombre(alumno.getNombre());
        response.setApellido(alumno.getApellido());
        response.setMatricula(alumno.getMatricula());
        response.setEmail(alumno.getEmail());
        response.setFechaNacimiento(alumno.getFechaNacimiento());
        response.setGenero(alumno.getGenero());
        response.setSemestre(alumno.getSemestre());
        if (alumno.getCarrera() != null) {
            response.setNombreCarrera(alumno.getCarrera().getNombre());
        }
        return response;
    }
}
