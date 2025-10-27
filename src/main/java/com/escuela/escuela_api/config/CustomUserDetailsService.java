package com.escuela.escuela_api.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.escuela.escuela_api.repositories.AdminRepository;
import com.escuela.escuela_api.repositories.AlumnoRepository;
import com.escuela.escuela_api.repositories.ProfesorRepository;
@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired private AdminRepository adminRepository;
    @Autowired private ProfesorRepository profesorRepository;
    @Autowired private AlumnoRepository alumnoRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // 1. Buscar en Admins
        UserDetails user = adminRepository.findByEmail(email).orElse(null);
        if (user != null) return user;
        // 2. Buscar en Profesores
        user = profesorRepository.findByEmail(email).orElse(null);
        if (user != null) return user;
        // 3. Buscar en Alumnos
        user = alumnoRepository.findByEmail(email).orElse(null);
        if (user != null) return user;
        // 4. Si no se encuentra
        throw new UsernameNotFoundException("Usuario no encontrado con email: " + email);
    }
}