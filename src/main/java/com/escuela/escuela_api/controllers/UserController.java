package com.escuela.escuela_api.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.escuela.escuela_api.dto.SelfPasswordChangeRequest;
import com.escuela.escuela_api.services.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor // Usa Lombok para inyectar el servicio
public class UserController {

    // Servicio inyectado (debe ser 'final' para que RequiredArgsConstructor funcione)
    private final UserService userService;

    /**
     * Endpoint para que un usuario cambie su propia contraseña.
     * Debe proporcionar su contraseña antigua y la nueva.
     * @param request El DTO (Data Transfer Object) que contiene la contraseña antigua y nueva.
     * @return Una respuesta de éxito.
     */
    @PatchMapping("/change-password")
    @PreAuthorize("isAuthenticated()") // ¡Cualquier usuario logueado puede usar esto!
    public ResponseEntity<String> changeMyPassword(@RequestBody SelfPasswordChangeRequest request) {
        
        // Llama al servicio que contiene la lógica de negocio
        userService.changeMyPassword(request);
        
        // Devuelve una respuesta simple de éxito
        return ResponseEntity.ok("Contraseña cambiada exitosamente");
    }
}