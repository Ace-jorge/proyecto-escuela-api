package com.escuela.escuela_api.services;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.escuela.escuela_api.dto.SelfPasswordChangeRequest;
import com.escuela.escuela_api.models.Admin;
import com.escuela.escuela_api.models.Alumno;
import com.escuela.escuela_api.models.Profesor;
import com.escuela.escuela_api.repositories.AdminRepository;
import com.escuela.escuela_api.repositories.AlumnoRepository;
import com.escuela.escuela_api.repositories.ProfesorRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    // Necesitamos los 3 repos de usuarios
    private final AlumnoRepository alumnoRepo;
    private final ProfesorRepository profRepo;
    private final AdminRepository adminRepo;
    private final PasswordEncoder passwordEncoder;

    public void changeMyPassword(SelfPasswordChangeRequest request) {
        
        // 1. Obtener el UserDetails (Admin, Alumno o Profesor) del usuario logueado
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails user = (UserDetails) principal;

        // 2. Verificar la contraseña antigua
        if (!passwordEncoder.matches(request.getOldPassword(), user.getPassword())) {
            throw new RuntimeException("Contraseña anterior incorrecta");
        }

        // 3. Encriptar la nueva contraseña
        String newEncodedPassword = passwordEncoder.encode(request.getNewPassword());

        // 4. Guardar la nueva contraseña en la tabla correcta
        if (user instanceof Alumno) {
            Alumno alumno = (Alumno) user;
            alumno.setPassword(newEncodedPassword);
            alumnoRepo.save(alumno);
        } else if (user instanceof Profesor) {
            Profesor profesor = (Profesor) user;
            profesor.setPassword(newEncodedPassword);
            profRepo.save(profesor);
        } else if (user instanceof Admin) {
            Admin admin = (Admin) user;
            admin.setPassword(newEncodedPassword);
            adminRepo.save(admin);
        }
    }
}