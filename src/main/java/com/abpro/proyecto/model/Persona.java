package com.abpro.proyecto.model;

import jakarta.validation.constraints.*;

/**
 * Modelo de datos: Persona
 *
 * Representa una persona con nombre y edad.
 * Incluye validaciones usando Bean Validation (Jakarta Validation)
 *
 * @author Equipo de Desarrollo ABPRO
 */
public class Persona {

    /**
     * Nombre de la persona
     * - No puede ser nulo
     * - No puede estar vacío (sin espacios en blanco)
     * - Debe tener entre 2 y 50 caracteres
     */
    @NotNull(message = "El nombre no puede ser nulo")
    @NotEmpty(message = "El nombre no puede estar vacío")
    @Size(min = 2, max = 50, message = "El nombre debe tener entre 2 y 50 caracteres")
    private String nombre;

    /**
     * Edad de la persona
     * - No puede ser nula
     * - Debe ser mayor o igual a 0
     * - Debe ser menor o igual a 150 (validación de rango razonable)
     */
    @NotNull(message = "La edad no puede ser nula")
    @Min(value = 0, message = "La edad debe ser mayor o igual a 0")
    @Max(value = 150, message = "La edad debe ser menor o igual a 150")
    private Integer edad;

    // Constructor vacío (necesario para Jackson y Spring)
    public Persona() {
    }

    // Constructor con parámetros
    public Persona(String nombre, Integer edad) {
        this.nombre = nombre;
        this.edad = edad;
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "nombre='" + nombre + '\'' +
                ", edad=" + edad +
                '}';
    }
}
