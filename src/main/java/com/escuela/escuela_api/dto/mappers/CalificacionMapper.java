package com.escuela.escuela_api.dto.mappers;

import com.escuela.escuela_api.dto.CalificacionResponse;
import com.escuela.escuela_api.models.Calificacion;
import org.springframework.stereotype.Component;

@Component
public class CalificacionMapper {

    public CalificacionResponse toCalificacionResponse(Calificacion calificacion) {
        if (calificacion == null) {
            return null;
        }
        CalificacionResponse response = new CalificacionResponse();
        response.setId(calificacion.getId());
        response.setValor(calificacion.getValor());
        if (calificacion.getAlumno() != null) {
            response.setNombreAlumno(calificacion.getAlumno().getNombre() + " " + calificacion.getAlumno().getApellido());
        }
        if (calificacion.getCurso() != null) {
            if (calificacion.getCurso().getMateria() != null) {
                response.setNombreMateria(calificacion.getCurso().getMateria().getNombre());
            }
            response.setGrupoCurso(calificacion.getCurso().getGrupo());
        }
        return response;
    }
}
