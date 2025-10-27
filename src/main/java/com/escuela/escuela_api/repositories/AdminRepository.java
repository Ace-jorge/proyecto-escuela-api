package com.escuela.escuela_api.repositories;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.escuela.escuela_api.models.Admin;
public interface AdminRepository extends JpaRepository<Admin, Long> {
    Optional<Admin> findByEmail(String email);
}