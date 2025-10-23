package com.abpro.proyecto.controller;

import com.abpro.proyecto.config.WebConfig;
import com.abpro.proyecto.model.Persona;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

/**
 * Pruebas unitarias para PersonaController
 *
 * Estas pruebas verifican que:
 * - Los endpoints respondan correctamente
 * - Las validaciones funcionen como se espera
 * - Los datos se procesen correctamente
 *
 * @author Equipo de Desarrollo ABPRO
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {WebConfig.class})
@WebAppConfiguration
public class PersonaControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(this.webApplicationContext)
                .build();
        this.objectMapper = new ObjectMapper();
    }

    @Test
    @DisplayName("Test 1: Health check debe retornar 200 OK")
    public void testHealthCheck() throws Exception {
        mockMvc.perform(get("/personas/health"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").value("API REST funcionando correctamente"));
    }

    @Test
    @DisplayName("Test 2: Listar todas las personas (inicialmente vacío)")
    public void testListarTodasPersonasVacio() throws Exception {
        mockMvc.perform(get("/personas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data").isArray());
    }

    @Test
    @DisplayName("Test 3: Crear persona válida debe retornar 201 Created")
    public void testCrearPersonaValida() throws Exception {
        Persona persona = new Persona("Juan Pérez", 30);
        String personaJson = objectMapper.writeValueAsString(persona);

        mockMvc.perform(post("/personas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(personaJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.nombre").value("Juan Pérez"))
                .andExpect(jsonPath("$.data.edad").value(30))
                .andExpect(jsonPath("$.data.id").exists());
    }

    @Test
    @DisplayName("Test 4: Crear persona con nombre vacío debe retornar error de validación")
    public void testCrearPersonaNombreVacio() throws Exception {
        Persona persona = new Persona("", 25);
        String personaJson = objectMapper.writeValueAsString(persona);

        mockMvc.perform(post("/personas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(personaJson))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.message").value("Error de validación en los datos enviados"))
                .andExpect(jsonPath("$.data.nombre").exists());
    }

    @Test
    @DisplayName("Test 5: Crear persona con edad negativa debe retornar error de validación")
    public void testCrearPersonaEdadNegativa() throws Exception {
        Persona persona = new Persona("María García", -5);
        String personaJson = objectMapper.writeValueAsString(persona);

        mockMvc.perform(post("/personas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(personaJson))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.data.edad").exists());
    }

    @Test
    @DisplayName("Test 6: Crear persona con edad mayor a 150 debe retornar error de validación")
    public void testCrearPersonaEdadExcesiva() throws Exception {
        Persona persona = new Persona("Pedro López", 200);
        String personaJson = objectMapper.writeValueAsString(persona);

        mockMvc.perform(post("/personas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(personaJson))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.data.edad").exists());
    }

    @Test
    @DisplayName("Test 7: Obtener persona por ID no existente debe retornar 404")
    public void testObtenerPersonaNoExistente() throws Exception {
        mockMvc.perform(get("/personas/999"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.success").value(false));
    }
}
