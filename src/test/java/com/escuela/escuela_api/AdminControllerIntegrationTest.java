package com.escuela.escuela_api;

import com.escuela.escuela_api.dto.CreateProfesorRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class AdminControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @WithMockUser(roles = "ADMIN")
    public void deberiaCrearUnProfesor() throws Exception {
        CreateProfesorRequest request = new CreateProfesorRequest();
        request.setNombre("Profesor de Prueba");
        request.setApellido("ApellidoPrueba");
        request.setMatricula("P98765");
        request.setEmail("profesor.prueba@escuela.com");
        request.setPassword("profesor123");
        request.setProfesion("Tester");
        request.setFechaNacimiento(LocalDate.of(1990, 1, 1));
        request.setGenero('M');

        mockMvc.perform(post("/api/admin/profesores")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.nombre").value("Profesor de Prueba"))
                .andExpect(jsonPath("$.email").value("profesor.prueba@escuela.com"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void noDeberiaCrearUnProfesorConEmailDuplicado() throws Exception {
        // First, create a professor
        CreateProfesorRequest request1 = new CreateProfesorRequest();
        request1.setNombre("Profesor Original");
        request1.setApellido("ApellidoOriginal");
        request1.setMatricula("P11111");
        request1.setEmail("profesor.duplicado@escuela.com");
        request1.setPassword("profesor123");
        request1.setProfesion("Original");
        request1.setFechaNacimiento(LocalDate.of(1990, 1, 1));
        request1.setGenero('M');

        mockMvc.perform(post("/api/admin/profesores")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request1)))
                .andExpect(status().isOk());

        // Then, try to create another professor with the same email
        CreateProfesorRequest request2 = new CreateProfesorRequest();
        request2.setNombre("Profesor Duplicado");
        request2.setApellido("ApellidoDuplicado");
        request2.setMatricula("P22222");
        request2.setEmail("profesor.duplicado@escuela.com"); // Same email
        request2.setPassword("profesor456");
        request2.setProfesion("Duplicado");
        request2.setFechaNacimiento(LocalDate.of(1991, 2, 2));
        request2.setGenero('F');

        mockMvc.perform(post("/api/admin/profesores")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request2)))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.message").value("Un profesor con esta matrícula o email ya existe"));
    }
}
