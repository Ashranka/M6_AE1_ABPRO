# Proyecto Spring MVC REST API - Actividad Grupal M6 AE1 ABPRO

## 📋 Descripción del Proyecto

Este proyecto es una **API REST desarrollada con Spring MVC puro** (sin Spring Boot), construida con Maven. Implementa un sistema de gestión de personas con validaciones completas y arquitectura profesional.

## 👥 Información del Equipo

- **Proyecto**: M6_AE1_ABPRO
- **Tecnología**: Spring MVC + Maven
- **Tipo**: REST API
- **Empaquetado**: WAR

---

## 🎯 Decisión Técnica: ¿Por qué Spring MVC en lugar de Spring Boot?

### Spring MVC (Nuestra elección)

**Ventajas:**
- ✅ **Control total** sobre la configuración
- ✅ **Comprensión profunda** de cómo funciona Spring
- ✅ **Flexibilidad** para personalizar cada aspecto
- ✅ **Aprendizaje** de los fundamentos de Spring
- ✅ **Configuración explícita** - Se ve claramente qué se está configurando

**Desventajas:**
- ❌ Más configuración manual necesaria
- ❌ Más código boilerplate
- ❌ No tiene autoconfiguración

### Spring Boot (Alternativa)

**Ventajas:**
- ✅ Configuración automática (autoconfiguration)
- ✅ Servidor embebido incluido
- ✅ Desarrollo más rápido
- ✅ Menos código boilerplate

**Desventajas:**
- ❌ "Magia" detrás de escenas
- ❌ Menos control sobre la configuración
- ❌ Curva de aprendizaje empinada para entender internals

### 🏆 Justificación de nuestra elección

Elegimos **Spring MVC** porque:
1. **Propósito educativo**: Permite entender los fundamentos de Spring Framework
2. **Control y transparencia**: Cada configuración es explícita y visible
3. **Preparación profesional**: Muchos proyectos legacy usan Spring MVC
4. **Base sólida**: Entender Spring MVC facilita después trabajar con Spring Boot

---

## 🏗️ Estructura del Proyecto

```
spring-mvc-rest-api/
├── pom.xml                              # Configuración de Maven
├── src/
│   ├── main/
│   │   ├── java/com/abpro/proyecto/
│   │   │   ├── config/                  # Configuración de Spring
│   │   │   │   ├── WebAppInitializer.java   # Reemplaza web.xml
│   │   │   │   └── WebConfig.java           # Configuración MVC
│   │   │   ├── controller/              # Controladores REST
│   │   │   │   └── PersonaController.java
│   │   │   ├── model/                   # Modelos de datos
│   │   │   │   └── Persona.java
│   │   │   ├── dto/                     # Data Transfer Objects
│   │   │   │   └── ApiResponse.java
│   │   │   └── exception/               # Manejo de excepciones
│   │   │       └── GlobalExceptionHandler.java
│   │   ├── resources/                   # Recursos
│   │   └── webapp/                      # Recursos web
│   │       └── WEB-INF/
│   └── test/
│       └── java/com/abpro/proyecto/
│           ├── controller/              # Tests de controladores
│           │   └── PersonaControllerTest.java
│           └── model/                   # Tests de modelos
│               └── PersonaTest.java
├── target/                              # Generado por Maven (compilación)
└── README.md                            # Este archivo
```

### Explicación de la estructura Maven:

- **src/main/java**: Código fuente de la aplicación
- **src/main/resources**: Archivos de configuración y recursos
- **src/main/webapp**: Contenido web (para aplicaciones WAR)
- **src/test/java**: Código de pruebas unitarias
- **target/**: Directorio generado con archivos compilados (creado por Maven)

---

## 📦 Configuración del pom.xml

### Datos Básicos del Proyecto

```xml
<groupId>com.abpro.proyecto</groupId>
<artifactId>spring-mvc-rest-api</artifactId>
<version>1.0.0-SNAPSHOT</version>
<packaging>war</packaging>
```

- **groupId**: Identificador único de la organización (com.abpro.proyecto)
- **artifactId**: Nombre del proyecto (spring-mvc-rest-api)
- **version**: Versión actual (1.0.0-SNAPSHOT indica desarrollo)
- **packaging**: Tipo de empaquetado (war para aplicaciones web)

### Dependencias Principales (8 dependencias)

#### 1. **Spring Web MVC** (`spring-webmvc`)
- **Propósito**: Framework MVC de Spring para crear aplicaciones web
- **Uso**: Permite usar @RestController, @RequestMapping, etc.
- **Por qué es importante**: Es el núcleo de nuestra REST API

#### 2. **Jackson Databind** (`jackson-databind`)
- **Propósito**: Serialización/deserialización de objetos Java a/desde JSON
- **Uso**: Convierte automáticamente objetos Persona a JSON y viceversa
- **Por qué es importante**: Las REST APIs trabajan con JSON

#### 3. **Jakarta Validation API** (`jakarta.validation-api`)
- **Propósito**: API estándar para validación de beans
- **Uso**: Proporciona anotaciones como @NotNull, @NotEmpty, @Size
- **Por qué es importante**: Define el contrato de validación

#### 4. **Hibernate Validator** (`hibernate-validator`)
- **Propósito**: Implementación de referencia de Bean Validation
- **Uso**: Ejecuta las validaciones definidas con las anotaciones
- **Por qué es importante**: Sin esto, las anotaciones no funcionarían

#### 5. **Jakarta Servlet API** (`jakarta.servlet-api`)
- **Propósito**: API de Servlets (especificación Java EE)
- **Uso**: Necesaria para ejecutar en servidores web
- **Scope**: `provided` (el servidor lo proporciona)
- **Por qué es importante**: Base de todas las aplicaciones web Java

#### 6. **JUnit 5** (`junit-jupiter`)
- **Propósito**: Framework de testing
- **Uso**: Crear y ejecutar pruebas unitarias
- **Scope**: `test` (solo para pruebas)
- **Por qué es importante**: Garantiza la calidad del código

#### 7. **Spring Test** (`spring-test`)
- **Propósito**: Utilidades de Spring para testing
- **Uso**: MockMvc para probar controladores
- **Scope**: `test`
- **Por qué es importante**: Permite probar componentes Spring

#### 8. **SLF4J Simple** (`slf4j-simple`)
- **Propósito**: Logging simple
- **Uso**: Registrar información de ejecución
- **Por qué es importante**: Debugging y monitoreo

---

## 🔄 Ciclo de Vida de Maven

### Comandos Maven y su Efecto

#### 1️⃣ `mvn clean`

**Qué hace:**
- Elimina el directorio `target/` completo
- Borra todos los archivos compilados y generados previamente
- Deja el proyecto en estado limpio

**Cuándo usarlo:**
- Antes de una compilación completa desde cero
- Cuando hay problemas con archivos compilados antiguos
- Antes de crear el paquete final para distribución

**Salida esperada:**
```
[INFO] --- maven-clean-plugin:3.2.0:clean ---
[INFO] Deleting target
[INFO] BUILD SUCCESS
```

---

#### 2️⃣ `mvn compile`

**Qué hace:**
- Compila el código fuente de `src/main/java`
- Genera archivos `.class` en `target/classes/`
- Copia recursos de `src/main/resources` a `target/classes/`
- Descarga dependencias si es necesario

**Archivos generados en target/classes/:**
```
target/classes/
├── com/abpro/proyecto/
│   ├── config/
│   │   ├── WebAppInitializer.class
│   │   └── WebConfig.class
│   ├── controller/
│   │   └── PersonaController.class
│   ├── model/
│   │   └── Persona.class
│   ├── dto/
│   │   └── ApiResponse.class
│   └── exception/
│       └── GlobalExceptionHandler.class
```

**Cuándo usarlo:**
- Para verificar que el código compila sin errores
- Antes de ejecutar la aplicación
- Para ver errores de compilación rápidamente

**Salida esperada:**
```
[INFO] --- maven-compiler-plugin:3.12.1:compile ---
[INFO] Compiling 7 source files
[INFO] BUILD SUCCESS
```

---

#### 3️⃣ `mvn test`

**Qué hace:**
- Ejecuta `mvn compile` primero (si es necesario)
- Compila el código de pruebas de `src/test/java`
- Ejecuta todas las pruebas unitarias
- Genera reportes de pruebas en `target/surefire-reports/`

**Archivos generados:**
```
target/
├── test-classes/              # Clases de test compiladas
│   └── com/abpro/proyecto/
│       ├── controller/
│       │   └── PersonaControllerTest.class
│       └── model/
│           └── PersonaTest.class
└── surefire-reports/          # Reportes de pruebas
    ├── TEST-*.xml
    └── *.txt
```

**Pruebas incluidas en este proyecto:**
- ✅ 7 tests en `PersonaControllerTest` (endpoints REST)
- ✅ 10 tests en `PersonaTest` (validaciones del modelo)
- **Total: 17 tests**

**Cuándo usarlo:**
- Antes de hacer commit
- Para verificar que no se rompió nada
- Como parte del CI/CD

**Salida esperada:**
```
[INFO] --- maven-surefire-plugin:3.2.5:test ---
[INFO] Running com.abpro.proyecto.controller.PersonaControllerTest
[INFO] Tests run: 7, Failures: 0, Errors: 0, Skipped: 0
[INFO] Running com.abpro.proyecto.model.PersonaTest
[INFO] Tests run: 10, Failures: 0, Errors: 0, Skipped: 0
[INFO] BUILD SUCCESS
```

---

#### 4️⃣ `mvn package`

**Qué hace:**
- Ejecuta `compile` y `test`
- Empaqueta la aplicación según el `<packaging>` del pom.xml
- En nuestro caso, crea un archivo **WAR** (Web Application Archive)
- Genera el archivo en `target/spring-mvc-rest-api.war`

**Archivo generado:**
```
target/
└── spring-mvc-rest-api.war    # Archivo WAR listo para desplegar
```

**Contenido del WAR:**
```
spring-mvc-rest-api.war
├── WEB-INF/
│   ├── classes/               # Nuestras clases compiladas
│   │   └── com/abpro/proyecto/...
│   └── lib/                   # Dependencias (JARs)
│       ├── spring-webmvc-6.1.4.jar
│       ├── jackson-databind-2.16.1.jar
│       ├── hibernate-validator-8.0.1.Final.jar
│       └── ... (todas las dependencias)
└── META-INF/
    └── MANIFEST.MF
```

**Cuándo usarlo:**
- Para crear el archivo distribuible de la aplicación
- Antes de desplegar en un servidor (Tomcat, Jetty, etc.)
- Para generar el artefacto final

**Salida esperada:**
```
[INFO] --- maven-war-plugin:3.4.0:war ---
[INFO] Packaging webapp
[INFO] Building war: target/spring-mvc-rest-api.war
[INFO] BUILD SUCCESS
```

---

#### 5️⃣ `mvn install`

**Qué hace:**
- Ejecuta todas las fases anteriores (compile, test, package)
- Instala el WAR en el **repositorio local de Maven**
- Ubicación: `~/.m2/repository/com/abpro/proyecto/spring-mvc-rest-api/1.0.0-SNAPSHOT/`
- Permite que otros proyectos Maven en tu máquina usen este proyecto como dependencia

**Archivos generados:**
```
~/.m2/repository/
└── com/abpro/proyecto/spring-mvc-rest-api/1.0.0-SNAPSHOT/
    ├── spring-mvc-rest-api-1.0.0-SNAPSHOT.war
    ├── spring-mvc-rest-api-1.0.0-SNAPSHOT.pom
    └── maven-metadata-local.xml
```

**Cuándo usarlo:**
- Cuando otros proyectos necesitan usar este proyecto
- Para hacer el artefacto disponible localmente
- En proyectos multi-módulo

**Salida esperada:**
```
[INFO] --- maven-install-plugin:3.1.1:install ---
[INFO] Installing target/spring-mvc-rest-api.war to ~/.m2/repository/...
[INFO] Installing pom.xml to ~/.m2/repository/...
[INFO] BUILD SUCCESS
```

---

## 🗂️ Repositorio Local de Maven

### ¿Qué es?

El repositorio local de Maven es una caché en tu computadora donde Maven almacena:
- Dependencias descargadas
- Plugins de Maven
- Artefactos instalados por ti

### Ubicación

```bash
~/.m2/repository/    # Linux/Mac
C:\Users\{usuario}\.m2\repository\    # Windows
```

### Estructura

```
~/.m2/repository/
├── org/springframework/spring-webmvc/6.1.4/
│   ├── spring-webmvc-6.1.4.jar
│   ├── spring-webmvc-6.1.4.pom
│   └── spring-webmvc-6.1.4.jar.sha1
├── com/fasterxml/jackson/core/jackson-databind/2.16.1/
│   ├── jackson-databind-2.16.1.jar
│   └── jackson-databind-2.16.1.pom
└── com/abpro/proyecto/spring-mvc-rest-api/1.0.0-SNAPSHOT/
    └── spring-mvc-rest-api-1.0.0-SNAPSHOT.war
```

### Tipo de archivos que contiene

1. **JARs** (.jar): Librerías Java
2. **POMs** (.pom): Descriptores de proyecto (como nuestro pom.xml)
3. **WARs** (.war): Aplicaciones web
4. **Checksums** (.sha1, .md5): Para verificar integridad
5. **Metadata** (maven-metadata.xml): Información de versiones

---

## 🚀 Cómo Ejecutar el Proyecto

### Opción 1: Usando Jetty (Recomendado para desarrollo)

```bash
mvn jetty:run
```

La aplicación estará disponible en: `http://localhost:8080/api`

### Opción 2: Desplegar en Tomcat

1. Ejecutar `mvn package`
2. Copiar `target/spring-mvc-rest-api.war` a `{TOMCAT_HOME}/webapps/`
3. Iniciar Tomcat
4. Acceder a `http://localhost:8080/spring-mvc-rest-api`

---

## 📡 Endpoints de la API REST

### Base URL
```
http://localhost:8080/api
```

### Endpoints Disponibles

#### 1. Health Check
```http
GET /personas/health
```

**Respuesta:**
```json
{
  "success": true,
  "message": "API REST funcionando correctamente",
  "data": "Total de personas registradas: 0",
  "timestamp": "2025-10-23T22:50:00"
}
```

---

#### 2. Listar todas las personas
```http
GET /personas
```

**Respuesta:**
```json
{
  "success": true,
  "message": "Lista de personas obtenida exitosamente",
  "data": [
    {
      "id": 1,
      "nombre": "Juan Pérez",
      "edad": 30
    }
  ],
  "timestamp": "2025-10-23T22:50:00"
}
```

---

#### 3. Obtener persona por ID
```http
GET /personas/{id}
```

**Ejemplo:**
```http
GET /personas/1
```

**Respuesta exitosa (200):**
```json
{
  "success": true,
  "message": "Persona encontrada",
  "data": {
    "id": 1,
    "nombre": "Juan Pérez",
    "edad": 30
  },
  "timestamp": "2025-10-23T22:50:00"
}
```

**Respuesta error (404):**
```json
{
  "success": false,
  "message": "Persona con ID 999 no encontrada",
  "data": null,
  "timestamp": "2025-10-23T22:50:00"
}
```

---

#### 4. Crear nueva persona
```http
POST /personas
Content-Type: application/json
```

**Body:**
```json
{
  "nombre": "María García",
  "edad": 25
}
```

**Respuesta exitosa (201):**
```json
{
  "success": true,
  "message": "Persona creada exitosamente con ID: 1",
  "data": {
    "id": 1,
    "nombre": "María García",
    "edad": 25
  },
  "timestamp": "2025-10-23T22:50:00"
}
```

**Respuesta con error de validación (400):**
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

#### 5. Actualizar persona
```http
PUT /personas/{id}
Content-Type: application/json
```

**Body:**
```json
{
  "nombre": "Juan Pérez Actualizado",
  "edad": 31
}
```

**Respuesta (200):**
```json
{
  "success": true,
  "message": "Persona actualizada exitosamente",
  "data": {
    "id": 1,
    "nombre": "Juan Pérez Actualizado",
    "edad": 31
  },
  "timestamp": "2025-10-23T22:50:00"
}
```

---

#### 6. Eliminar persona
```http
DELETE /personas/{id}
```

**Respuesta (200):**
```json
{
  "success": true,
  "message": "Persona eliminada exitosamente",
  "data": "ID: 1",
  "timestamp": "2025-10-23T22:50:00"
}
```

---

## ✅ Validaciones Implementadas

### Validaciones del campo `nombre`:

1. ❌ **No puede ser nulo** (`@NotNull`)
2. ❌ **No puede estar vacío** (`@NotEmpty`)
3. ❌ **Longitud mínima: 2 caracteres** (`@Size(min=2)`)
4. ❌ **Longitud máxima: 50 caracteres** (`@Size(max=50)`)

### Validaciones del campo `edad`:

1. ❌ **No puede ser nula** (`@NotNull`)
2. ❌ **Debe ser mayor o igual a 0** (`@Min(0)`)
3. ❌ **Debe ser menor o igual a 150** (`@Max(150)`)

### Ejemplos de datos inválidos:

```json
// ERROR: Nombre vacío
{
  "nombre": "",
  "edad": 25
}

// ERROR: Edad negativa
{
  "nombre": "Pedro",
  "edad": -5
}

// ERROR: Edad excesiva
{
  "nombre": "Ana",
  "edad": 200
}

// ERROR: Nombre muy corto
{
  "nombre": "A",
  "edad": 30
}
```

---

## 🧪 Pruebas Unitarias

### Cobertura de Pruebas

#### PersonaTest (10 tests)
✅ Persona válida sin errores
✅ Nombre nulo genera error
✅ Nombre vacío genera error
✅ Nombre muy corto genera error
✅ Edad nula genera error
✅ Edad negativa genera error
✅ Edad excesiva genera error
✅ Edad 0 es válida
✅ Edad 150 es válida (límite)
✅ Constructor y getters funcionan

#### PersonaControllerTest (7 tests)
✅ Health check responde 200 OK
✅ Listar todas las personas
✅ Crear persona válida retorna 201
✅ Nombre vacío retorna error 400
✅ Edad negativa retorna error 400
✅ Edad excesiva retorna error 400
✅ Persona no existente retorna 404

**Total: 17 tests unitarios**

### Ejecutar las pruebas:
```bash
mvn test
```

---

## 🛠️ Tecnologías Utilizadas

| Tecnología | Versión | Propósito |
|------------|---------|-----------|
| Java | 17 | Lenguaje de programación |
| Spring MVC | 6.1.4 | Framework web |
| Jackson | 2.16.1 | Serialización JSON |
| Hibernate Validator | 8.0.1.Final | Validaciones |
| JUnit 5 | 5.10.1 | Testing |
| Maven | 3.9.11 | Gestión de dependencias |
| Jetty | 11.0.20 | Servidor de aplicaciones |

---

## 📚 Aprendizajes Clave

### 1. Maven
- ✅ Gestión de dependencias centralizada
- ✅ Ciclo de vida estandarizado (clean, compile, test, package, install)
- ✅ Repositorio local para caché de dependencias
- ✅ Plugins para diferentes tareas

### 2. Spring MVC
- ✅ Configuración sin XML (solo Java)
- ✅ Arquitectura MVC clara
- ✅ Inyección de dependencias
- ✅ Manejo de excepciones global

### 3. REST API
- ✅ Diseño de endpoints RESTful
- ✅ Códigos HTTP apropiados (200, 201, 400, 404)
- ✅ Formato JSON estandarizado
- ✅ Versionado de API

### 4. Validaciones
- ✅ Bean Validation (Jakarta Validation)
- ✅ Validaciones declarativas con anotaciones
- ✅ Mensajes de error personalizados
- ✅ Manejo centralizado de errores

### 5. Testing
- ✅ JUnit 5 como framework principal
- ✅ MockMvc para probar controladores
- ✅ Tests de integración
- ✅ Cobertura de casos válidos e inválidos

---

## 📝 Errores Comunes y Soluciones

### Error: "Plugin could not be resolved"
**Causa:** Sin conexión a internet para descargar dependencias
**Solución:** Verificar conexión o usar repositorio local/espejo

### Error: "DispatcherServlet not found"
**Causa:** Dependencia spring-webmvc no incluida
**Solución:** Verificar que esté en el pom.xml

### Error: "Validation doesn't work"
**Causa:** Falta hibernate-validator o configuración del validador
**Solución:** Agregar dependencia y configurar en WebConfig

### Error: "Tests not running"
**Causa:** Plugin surefire no configurado
**Solución:** Agregar maven-surefire-plugin al pom.xml

---

## 👨‍💻 Comandos Útiles

```bash
# Limpiar proyecto
mvn clean

# Compilar
mvn compile

# Ejecutar tests
mvn test

# Empaquetar WAR
mvn package

# Instalar en repositorio local
mvn install

# Ejecutar con Jetty
mvn jetty:run

# Ver árbol de dependencias
mvn dependency:tree

# Ver ayuda de un plugin
mvn help:describe -Dplugin=compiler

# Saltar tests (NO recomendado)
mvn package -DskipTests

# Compilar con información detallada
mvn clean install -X
```

---

## 🎓 Conclusiones

Este proyecto demuestra:

1. **Configuración completa de Maven** con 8 dependencias cuidadosamente seleccionadas
2. **Arquitectura REST limpia** con Spring MVC puro (sin Spring Boot)
3. **Validaciones robustas** usando Bean Validation
4. **Cobertura de pruebas** con 17 tests unitarios
5. **Documentación completa** del proceso y decisiones técnicas
6. **Comprensión del ciclo de vida de Maven** y sus comandos
7. **Manejo profesional de errores** con respuestas JSON estandarizadas

### Diferencias clave entre Spring MVC y Spring Boot:

| Aspecto | Spring MVC (Este proyecto) | Spring Boot |
|---------|---------------------------|-------------|
| Configuración | Manual (WebConfig, WebAppInitializer) | Automática |
| Servidor | Externo o plugin | Embebido |
| Dependencias | Declaradas explícitamente | Starters |
| Curva aprendizaje | Empinada pero educativa | Suave |
| Control | Total | Limitado |

---

## 📧 Contacto

**Equipo de Desarrollo ABPRO**
Proyecto: M6_AE1_ABPRO
Tecnología: Spring MVC + Maven

---

## 📜 Licencia

Este proyecto es parte de una actividad educativa.

---

**Generado con Claude Code** 🤖
Fecha: 2025-10-23
