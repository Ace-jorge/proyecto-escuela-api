package com.escuela.escuela_api.dto.mappers;

import com.escuela.escuela_api.dto.CreateCursoRequest;
import com.escuela.escuela_api.dto.CursoResponse;
import com.escuela.escuela_api.models.Curso;
import org.springframework.stereotype.Component;

@Component
public class CursoMapper {

    public Curso toCurso(CreateCursoRequest request) {
        if (request == null) {
            return null;
        }
        Curso curso = new Curso();
        curso.setGrupo(request.getGrupo());
        // Materia and Profesor will be handled in the service
        return curso;
    }

    public CursoResponse toCursoResponse(Curso curso) {
        if (curso == null) {
            return null;
        }
        CursoResponse response = new CursoResponse();
        response.setId(curso.getId());
        response.setGrupo(curso.getGrupo());
        if (curso.getMateria() != null) {
            response.setNombreMateria(curso.getMateria().getNombre());
        }
        if (curso.getProfesor() != null) {
            response.setNombreProfesor(curso.getProfesor().getNombre() + " " + curso.getProfesor().getApellido());
        }
        return response;
    }
}
