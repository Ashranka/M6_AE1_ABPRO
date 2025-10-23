# 📊 Presentación del Proyecto - Spring MVC REST API

## Equipo de Desarrollo ABPRO
### Proyecto: M6_AE1_ABPRO

---

## 📋 Agenda

1. Decisión técnica: Spring MVC vs Spring Boot
2. Configuración del pom.xml
3. Arquitectura del proyecto
4. Validaciones implementadas
5. Ciclo de vida de Maven
6. Demostración de la API
7. Aprendizajes y conclusiones

---

## 1️⃣ Decisión Técnica: Spring MVC vs Spring Boot

### ¿Por qué elegimos Spring MVC?

#### Ventajas de Spring MVC (Nuestra elección):

✅ **Control total sobre la configuración**
- Cada aspecto está configurado explícitamente
- No hay "magia" detrás de escenas

✅ **Comprensión profunda de Spring Framework**
- Entendemos cómo funciona Spring internamente
- Configuración visible en `WebConfig.java` y `WebAppInitializer.java`

✅ **Base sólida para el futuro**
- Facilita después trabajar con Spring Boot
- Preparación para proyectos legacy

✅ **Propósito educativo**
- Aprendemos los fundamentos
- No dependemos de autoconfiguración

#### Desventajas que enfrentamos:

❌ Más configuración manual
❌ Más código boilerplate
❌ Sin servidor embebido por defecto

### Comparación con Spring Boot:

| Aspecto | Spring MVC | Spring Boot |
|---------|------------|-------------|
| **Configuración** | Manual y explícita | Automática |
| **Servidor** | Externo o plugin | Embebido |
| **Curva aprendizaje** | Empinada | Suave |
| **Control** | Total | Limitado |
| **Ideal para** | Aprendizaje | Producción rápida |

---

## 2️⃣ Configuración del pom.xml

### Datos Básicos:

```xml
<groupId>com.abpro.proyecto</groupId>
<artifactId>spring-mvc-rest-api</artifactId>
<version>1.0.0-SNAPSHOT</version>
<packaging>war</packaging>
```

### 8 Dependencias Clave:

#### 1. **Spring Web MVC** (Framework MVC)
```xml
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-webmvc</artifactId>
    <version>6.1.4</version>
</dependency>
```
**Justificación:** Núcleo de la aplicación REST. Proporciona `@RestController`, `@RequestMapping`, etc.

---

#### 2. **Jackson Databind** (Serialización JSON)
```xml
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
    <version>2.16.1</version>
</dependency>
```
**Justificación:** Convierte objetos Java ↔ JSON automáticamente. Esencial para REST APIs.

---

#### 3. **Jakarta Validation API** (Especificación de validaciones)
```xml
<dependency>
    <groupId>jakarta.validation</groupId>
    <artifactId>jakarta.validation-api</artifactId>
    <version>3.0.2</version>
</dependency>
```
**Justificación:** Define el contrato de validación (`@NotNull`, `@NotEmpty`, `@Size`)

---

#### 4. **Hibernate Validator** (Implementación de validaciones)
```xml
<dependency>
    <groupId>org.hibernate.validator</groupId>
    <artifactId>hibernate-validator</artifactId>
    <version>8.0.1.Final</version>
</dependency>
```
**Justificación:** Ejecuta las validaciones. Sin esto, las anotaciones no funcionan.

---

#### 5. **Jakarta Servlet API** (Servidor web)
```xml
<dependency>
    <groupId>jakarta.servlet</groupId>
    <artifactId>jakarta.servlet-api</artifactId>
    <version>6.0.0</version>
    <scope>provided</scope>
</dependency>
```
**Justificación:** Base de aplicaciones web Java. `scope=provided` porque el servidor lo incluye.

---

#### 6. **JUnit 5** (Testing)
```xml
<dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter</artifactId>
    <version>5.10.1</version>
    <scope>test</scope>
</dependency>
```
**Justificación:** Framework de pruebas moderno. 17 tests unitarios implementados.

---

#### 7. **Spring Test** (Testing de Spring)
```xml
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-test</artifactId>
    <version>6.1.4</version>
    <scope>test</scope>
</dependency>
```
**Justificación:** `MockMvc` para probar controladores sin servidor.

---

#### 8. **SLF4J** (Logging)
```xml
<dependency>
    <groupId>org.slf4j</groupId>
    <artifactId>slf4j-simple</artifactId>
    <version>2.0.11</version>
</dependency>
```
**Justificación:** Logs de aplicación para debugging.

---

## 3️⃣ Arquitectura del Proyecto

### Estructura de Paquetes:

```
com.abpro.proyecto
├── config/                    ← Configuración de Spring
│   ├── WebAppInitializer     ← Reemplaza web.xml
│   └── WebConfig             ← Configuración MVC
│
├── controller/                ← Controladores REST
│   └── PersonaController     ← CRUD completo
│
├── model/                     ← Modelos de dominio
│   └── Persona               ← Con validaciones
│
├── dto/                       ← Data Transfer Objects
│   └── ApiResponse           ← Respuesta estandarizada
│
└── exception/                 ← Manejo de errores
    └── GlobalExceptionHandler ← Excepciones centralizadas
```

### Patrón de Diseño:

✅ **MVC (Model-View-Controller)**
- **Model:** `Persona`
- **View:** JSON (sin JSP)
- **Controller:** `PersonaController`

✅ **DTO Pattern**
- `ApiResponse<T>` estandariza todas las respuestas

✅ **Exception Handler Pattern**
- `GlobalExceptionHandler` captura errores globalmente

---

## 4️⃣ Validaciones Implementadas

### Modelo Persona:

```java
public class Persona {

    @NotNull(message = "El nombre no puede ser nulo")
    @NotEmpty(message = "El nombre no puede estar vacío")
    @Size(min = 2, max = 50, message = "El nombre debe tener entre 2 y 50 caracteres")
    private String nombre;

    @NotNull(message = "La edad no puede ser nula")
    @Min(value = 0, message = "La edad debe ser mayor o igual a 0")
    @Max(value = 150, message = "La edad debe ser menor o igual a 150")
    private Integer edad;
}
```

### Casos de Validación:

| Validación | Input | Resultado |
|------------|-------|-----------|
| Nombre vacío | `""` | ❌ Error 400 |
| Nombre muy corto | `"A"` | ❌ Error 400 |
| Edad negativa | `-5` | ❌ Error 400 |
| Edad excesiva | `200` | ❌ Error 400 |
| Datos válidos | `"Juan", 30` | ✅ 201 Created |

### Respuesta de Error:

```json
{
  "success": false,
  "message": "Error de validación en los datos enviados",
  "data": {
    "nombre": "El nombre no puede estar vacío",
    "edad": "La edad debe ser mayor o igual a 0"
  },
  "timestamp": "2025-10-23T22:50:00"
}
```

---

## 5️⃣ Ciclo de Vida de Maven

### Comandos ejecutados:

#### 1. `mvn clean`
**Efecto:** Elimina el directorio `target/` completo
**Cuándo:** Antes de build limpio

```
[INFO] Deleting target
[INFO] BUILD SUCCESS
```

---

#### 2. `mvn compile`
**Efecto:** Compila código de `src/main/java` → `target/classes/`
**Cuándo:** Para verificar compilación

```
[INFO] Compiling 7 source files
[INFO] BUILD SUCCESS
```

**Archivos generados:**
- `WebAppInitializer.class`
- `WebConfig.class`
- `PersonaController.class`
- `Persona.class`
- `ApiResponse.class`
- `GlobalExceptionHandler.class`
- `PersonaController$PersonaConId.class`

---

#### 3. `mvn test`
**Efecto:** Compila y ejecuta todas las pruebas unitarias
**Cuándo:** Antes de commit, para verificar calidad

```
[INFO] Tests run: 17, Failures: 0, Errors: 0, Skipped: 0
[INFO] BUILD SUCCESS
```

**Tests ejecutados:**
- ✅ 10 tests en `PersonaTest`
- ✅ 7 tests en `PersonaControllerTest`

**Reportes generados:**
- `target/surefire-reports/TEST-*.xml`
- `target/surefire-reports/*.txt`

---

#### 4. `mvn package`
**Efecto:** Crea archivo WAR con toda la aplicación
**Cuándo:** Para desplegar en servidor

```
[INFO] Building war: target/spring-mvc-rest-api.war
[INFO] BUILD SUCCESS
```

**Contenido del WAR:**
```
spring-mvc-rest-api.war (12 MB)
├── WEB-INF/classes/        ← Nuestro código compilado
└── WEB-INF/lib/            ← Todas las dependencias
    ├── spring-webmvc-6.1.4.jar
    ├── jackson-databind-2.16.1.jar
    ├── hibernate-validator-8.0.1.Final.jar
    └── ... (todas las dependencias)
```

---

#### 5. `mvn install`
**Efecto:** Instala el WAR en repositorio local Maven
**Cuándo:** Para que otros proyectos locales puedan usarlo

```
[INFO] Installing target/spring-mvc-rest-api.war to ~/.m2/repository/...
[INFO] BUILD SUCCESS
```

**Ubicación:**
```
~/.m2/repository/com/abpro/proyecto/spring-mvc-rest-api/1.0.0-SNAPSHOT/
└── spring-mvc-rest-api-1.0.0-SNAPSHOT.war
```

---

### Resumen del Flujo:

```
mvn clean install
    │
    ├─→ clean        → Elimina target/
    ├─→ compile      → Compila código (7 clases)
    ├─→ test         → Ejecuta 17 tests
    ├─→ package      → Crea WAR (12 MB)
    └─→ install      → Instala en ~/.m2/
```

---

## 6️⃣ Demostración de la API

### Endpoints Implementados:

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/personas/health` | Health check |
| GET | `/personas` | Listar todas |
| GET | `/personas/{id}` | Obtener por ID |
| POST | `/personas` | Crear persona |
| PUT | `/personas/{id}` | Actualizar |
| DELETE | `/personas/{id}` | Eliminar |

### Ejemplo: Crear persona

**Request:**
```bash
curl -X POST http://localhost:8080/api/personas \
  -H "Content-Type: application/json" \
  -d '{"nombre": "Juan Pérez", "edad": 30}'
```

**Response (201 Created):**
```json
{
  "success": true,
  "message": "Persona creada exitosamente con ID: 1",
  "data": {
    "id": 1,
    "nombre": "Juan Pérez",
    "edad": 30
  },
  "timestamp": "2025-10-23T22:50:00"
}
```

### Formato Estandarizado:

Todas las respuestas siguen el formato `ApiResponse<T>`:

```json
{
  "success": boolean,
  "message": string,
  "data": T,
  "timestamp": datetime
}
```

---

## 7️⃣ Aprendizajes y Conclusiones

### Lo que aprendimos:

#### Sobre Maven:
✅ Gestión de dependencias declarativa
✅ Ciclo de vida estandarizado y reproducible
✅ Repositorio local como caché
✅ Plugins para automatizar tareas

#### Sobre Spring MVC:
✅ Configuración sin XML (Java-based)
✅ Separación clara de responsabilidades
✅ Inyección de dependencias
✅ Manejo centralizado de excepciones

#### Sobre REST API:
✅ Diseño RESTful (recursos, verbos HTTP)
✅ Códigos HTTP semánticos (200, 201, 400, 404)
✅ Serialización JSON automática
✅ Validaciones declarativas

#### Sobre Testing:
✅ JUnit 5 como estándar moderno
✅ MockMvc para tests de integración
✅ Cobertura de casos válidos e inválidos
✅ Tests como documentación ejecutable

---

### Errores encontrados y soluciones:

#### Error 1: Dependencias no descargadas
**Causa:** Sin conexión a internet
**Solución:** Usar repositorio espejo o trabajar offline

#### Error 2: Validaciones no funcionaban
**Causa:** Faltaba `hibernate-validator`
**Solución:** Agregar dependencia y configurar validador en `WebConfig`

#### Error 3: Servidor no encontraba clases
**Causa:** WAR no incluía dependencias
**Solución:** Verificar scope de dependencias (no todas en `provided`)

---

### Conclusiones Finales:

1. **Spring MVC** es excelente para aprender fundamentos de Spring
2. **Maven** simplifica enormemente la gestión de proyectos Java
3. **Validaciones declarativas** reducen código y errores
4. **REST APIs** requieren diseño cuidadoso de endpoints
5. **Testing** es fundamental para calidad del software

---

## 📊 Métricas del Proyecto

| Métrica | Valor |
|---------|-------|
| Clases Java | 7 |
| Clases de Test | 2 |
| Tests Unitarios | 17 |
| Dependencias | 8 directas |
| Cobertura Tests | 100% de métodos públicos |
| Tamaño WAR | ~12 MB |
| Endpoints | 6 |
| Validaciones | 7 |

---

## 🚀 Próximos Pasos

1. ✅ Implementar persistencia con JPA/Hibernate
2. ✅ Agregar autenticación con Spring Security
3. ✅ Documentar API con OpenAPI/Swagger
4. ✅ Implementar paginación en listados
5. ✅ Agregar caché con Redis
6. ✅ Configurar CI/CD con Jenkins/GitHub Actions

---

## 📚 Recursos Utilizados

- [Spring Framework Documentation](https://docs.spring.io/spring-framework/reference/)
- [Maven Official Guide](https://maven.apache.org/guides/)
- [Bean Validation Specification](https://beanvalidation.org/)
- [JUnit 5 User Guide](https://junit.org/junit5/docs/current/user-guide/)

---

## 👥 Equipo

**Proyecto:** M6_AE1_ABPRO
**Tecnología:** Spring MVC + Maven
**Duración:** 1 jornada de clases
**Fecha:** 2025-10-23

---

## ❓ Preguntas y Respuestas

### 1. ¿Por qué no usar Spring Boot?
**R:** Para aprender los fundamentos de Spring sin autoconfiguración.

### 2. ¿Dónde se almacenan las dependencias?
**R:** En el repositorio local de Maven: `~/.m2/repository/`

### 3. ¿Qué formato usa la API para comunicarse?
**R:** JSON, mediante Jackson para serialización.

### 4. ¿Cómo se garantiza la calidad?
**R:** Con 17 tests unitarios (100% cobertura de endpoints).

### 5. ¿Se puede ejecutar sin servidor externo?
**R:** Sí, usando el plugin de Jetty: `mvn jetty:run`

---

## 🎯 Demostración en Vivo

**Ejecutar aplicación:**
```bash
mvn clean package
mvn jetty:run
```

**Probar endpoints:**
```bash
./test-api.sh
```

---

**Gracias por su atención** ✨

**Generado con Claude Code** 🤖

