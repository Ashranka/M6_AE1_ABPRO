package com.abpro.proyecto.model;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas unitarias para el modelo Persona
 *
 * Verifica que las validaciones del modelo funcionen correctamente
 *
 * @author Equipo de Desarrollo ABPRO
 */
public class PersonaTest {

    private static Validator validator;

    @BeforeAll
    public static void setupValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    @DisplayName("Test 1: Persona válida no debe tener errores de validación")
    public void testPersonaValida() {
        Persona persona = new Persona("Ana María", 25);
        Set<ConstraintViolation<Persona>> violations = validator.validate(persona);
        assertTrue(violations.isEmpty(), "Una persona válida no debe tener errores de validación");
    }

    @Test
    @DisplayName("Test 2: Nombre nulo debe generar error de validación")
    public void testNombreNulo() {
        Persona persona = new Persona(null, 30);
        Set<ConstraintViolation<Persona>> violations = validator.validate(persona);
        assertFalse(violations.isEmpty(), "Nombre nulo debe generar error");
        assertTrue(violations.stream()
                .anyMatch(v -> v.getPropertyPath().toString().equals("nombre")));
    }

    @Test
    @DisplayName("Test 3: Nombre vacío debe generar error de validación")
    public void testNombreVacio() {
        Persona persona = new Persona("", 30);
        Set<ConstraintViolation<Persona>> violations = validator.validate(persona);
        assertFalse(violations.isEmpty(), "Nombre vacío debe generar error");
    }

    @Test
    @DisplayName("Test 4: Nombre con 1 carácter debe generar error (mínimo 2)")
    public void testNombreMuyCorto() {
        Persona persona = new Persona("A", 30);
        Set<ConstraintViolation<Persona>> violations = validator.validate(persona);
        assertFalse(violations.isEmpty(), "Nombre de 1 carácter debe generar error");
    }

    @Test
    @DisplayName("Test 5: Edad nula debe generar error de validación")
    public void testEdadNula() {
        Persona persona = new Persona("Carlos", null);
        Set<ConstraintViolation<Persona>> violations = validator.validate(persona);
        assertFalse(violations.isEmpty(), "Edad nula debe generar error");
    }

    @Test
    @DisplayName("Test 6: Edad negativa debe generar error de validación")
    public void testEdadNegativa() {
        Persona persona = new Persona("Diana", -1);
        Set<ConstraintViolation<Persona>> violations = validator.validate(persona);
        assertFalse(violations.isEmpty(), "Edad negativa debe generar error");
    }

    @Test
    @DisplayName("Test 7: Edad mayor a 150 debe generar error de validación")
    public void testEdadExcesiva() {
        Persona persona = new Persona("Eduardo", 151);
        Set<ConstraintViolation<Persona>> violations = validator.validate(persona);
        assertFalse(violations.isEmpty(), "Edad mayor a 150 debe generar error");
    }

    @Test
    @DisplayName("Test 8: Edad 0 debe ser válida")
    public void testEdadCero() {
        Persona persona = new Persona("Bebé", 0);
        Set<ConstraintViolation<Persona>> violations = validator.validate(persona);
        assertTrue(violations.isEmpty(), "Edad 0 debe ser válida");
    }

    @Test
    @DisplayName("Test 9: Edad 150 debe ser válida (límite superior)")
    public void testEdadLimiteSuperior() {
        Persona persona = new Persona("Longevo", 150);
        Set<ConstraintViolation<Persona>> violations = validator.validate(persona);
        assertTrue(violations.isEmpty(), "Edad 150 debe ser válida");
    }

    @Test
    @DisplayName("Test 10: Constructor y getters deben funcionar correctamente")
    public void testConstructorYGetters() {
        String nombreEsperado = "Fernando";
        Integer edadEsperada = 45;

        Persona persona = new Persona(nombreEsperado, edadEsperada);

        assertEquals(nombreEsperado, persona.getNombre());
        assertEquals(edadEsperada, persona.getEdad());
    }
}
