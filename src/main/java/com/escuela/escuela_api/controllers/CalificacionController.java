package com.escuela.escuela_api.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.escuela.escuela_api.models.Calificacion;
import com.escuela.escuela_api.services.CalificacionService;
@RestController
@RequestMapping("/api/calificaciones")
public class CalificacionController {
    @Autowired private CalificacionService calificacionService;
    
    @PostMapping
    @PreAuthorize("hasRole('PROFESOR')") // Solo un profesor puede crear notas
    public Calificacion crearCalificacion(@RequestBody Calificacion calificacion) {
        return calificacionService.guardarCalificacion(calificacion);
    }
}