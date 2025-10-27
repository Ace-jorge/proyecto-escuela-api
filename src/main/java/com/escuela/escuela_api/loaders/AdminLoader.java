package com.escuela.escuela_api.loaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.escuela.escuela_api.models.Admin;
import com.escuela.escuela_api.repositories.AdminRepository;
@Component
public class AdminLoader implements CommandLineRunner {
    @Autowired private AdminRepository adminRepository;
    @Autowired private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (adminRepository.findByEmail("admin@escuela.com").isEmpty()) {
            Admin admin = new Admin();
            admin.setNombre("Admin Principal");
            admin.setEmail("admin@escuela.com");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRol("ROLE_ADMIN");
            adminRepository.save(admin);
            System.out.println(">>> Usuario Admin por defecto creado (admin@escuela.com / admin123) <<<");
        }
    }
}