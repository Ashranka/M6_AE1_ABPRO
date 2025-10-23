package com.abpro.proyecto.controller;

import com.abpro.proyecto.dto.ApiResponse;
import com.abpro.proyecto.model.Persona;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Controlador REST para gestionar Personas
 *
 * Endpoints disponibles:
 * - GET    /personas          : Listar todas las personas
 * - GET    /personas/{id}     : Obtener una persona por ID
 * - POST   /personas          : Crear una nueva persona
 * - PUT    /personas/{id}     : Actualizar una persona existente
 * - DELETE /personas/{id}     : Eliminar una persona
 *
 * @RestController: Combina @Controller + @ResponseBody
 * @RequestMapping: Define la ruta base para todos los endpoints
 *
 * @author Equipo de Desarrollo ABPRO
 */
@RestController
@RequestMapping("/personas")
public class PersonaController {

    // Almacenamiento en memoria (simula una base de datos)
    private final List<PersonaConId> personas = new ArrayList<>();
    private final AtomicLong contador = new AtomicLong();

    /**
     * Clase interna para agregar ID a las personas
     */
    private static class PersonaConId extends Persona {
        private Long id;

        public PersonaConId(Long id, String nombre, Integer edad) {
            super(nombre, edad);
            this.id = id;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }
    }

    /**
     * GET /personas
     * Lista todas las personas registradas
     */
    @GetMapping
    public ResponseEntity<ApiResponse<List<PersonaConId>>> listarTodas() {
        return ResponseEntity.ok(
                ApiResponse.success("Lista de personas obtenida exitosamente", personas)
        );
    }

    /**
     * GET /personas/{id}
     * Obtiene una persona específica por su ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PersonaConId>> obtenerPorId(@PathVariable Long id) {
        Optional<PersonaConId> persona = personas.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();

        if (persona.isPresent()) {
            return ResponseEntity.ok(
                    ApiResponse.success("Persona encontrada", persona.get())
            );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.error("Persona con ID " + id + " no encontrada"));
        }
    }

    /**
     * POST /personas
     * Crea una nueva persona
     *
     * @Valid: Activa la validación automática del objeto Persona
     * Las validaciones están definidas en la clase Persona con anotaciones
     */
    @PostMapping
    public ResponseEntity<ApiResponse<PersonaConId>> crear(@Valid @RequestBody Persona persona) {
        Long nuevoId = contador.incrementAndGet();
        PersonaConId nuevaPersona = new PersonaConId(
                nuevoId,
                persona.getNombre(),
                persona.getEdad()
        );

        personas.add(nuevaPersona);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(
                        "Persona creada exitosamente con ID: " + nuevoId,
                        nuevaPersona
                ));
    }

    /**
     * PUT /personas/{id}
     * Actualiza una persona existente
     */
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<PersonaConId>> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody Persona persona) {

        Optional<PersonaConId> personaExistente = personas.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();

        if (personaExistente.isPresent()) {
            PersonaConId personaActualizada = personaExistente.get();
            personaActualizada.setNombre(persona.getNombre());
            personaActualizada.setEdad(persona.getEdad());

            return ResponseEntity.ok(
                    ApiResponse.success("Persona actualizada exitosamente", personaActualizada)
            );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.error("Persona con ID " + id + " no encontrada"));
        }
    }

    /**
     * DELETE /personas/{id}
     * Elimina una persona
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> eliminar(@PathVariable Long id) {
        boolean eliminada = personas.removeIf(p -> p.getId().equals(id));

        if (eliminada) {
            return ResponseEntity.ok(
                    ApiResponse.success("Persona eliminada exitosamente", "ID: " + id)
            );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.error("Persona con ID " + id + " no encontrada"));
        }
    }

    /**
     * GET /personas/health
     * Endpoint de health check para verificar que el servicio está funcionando
     */
    @GetMapping("/health")
    public ResponseEntity<ApiResponse<String>> healthCheck() {
        return ResponseEntity.ok(
                ApiResponse.success(
                        "API REST funcionando correctamente",
                        "Total de personas registradas: " + personas.size()
                )
        );
    }
}
