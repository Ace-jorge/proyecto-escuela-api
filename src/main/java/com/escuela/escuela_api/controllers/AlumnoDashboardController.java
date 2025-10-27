package com.escuela.escuela_api.controllers;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.escuela.escuela_api.models.Calificacion;
import com.escuela.escuela_api.models.Horario;
import com.escuela.escuela_api.services.AlumnoDashboardService;
@RestController
@RequestMapping("/api/alumno")
@PreAuthorize("hasRole('ALUMNO')") // Solo alumnos pueden ver esto
public class AlumnoDashboardController {
    @Autowired private AlumnoDashboardService alumnoDashboardService;

    @GetMapping("/mis-calificaciones")
    public List<Calificacion> getMisCalificaciones() {
        return alumnoDashboardService.getMisCalificaciones();
    }
    @GetMapping("/mi-horario")
    public List<Horario> getMiHorario() {
        return alumnoDashboardService.getMiHorario();
    }
}