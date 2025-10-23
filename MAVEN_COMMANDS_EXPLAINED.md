# 📘 Comandos Maven - Explicación Detallada

## Índice
1. [mvn clean](#1-mvn-clean)
2. [mvn compile](#2-mvn-compile)
3. [mvn test](#3-mvn-test)
4. [mvn package](#4-mvn-package)
5. [mvn install](#5-mvn-install)
6. [Ciclo de vida completo](#ciclo-de-vida-completo)
7. [Comandos adicionales útiles](#comandos-adicionales-útiles)

---

## 1. mvn clean

### Descripción
Limpia el proyecto eliminando todos los archivos generados en compilaciones previas.

### Comando
```bash
mvn clean
```

### ¿Qué hace exactamente?

1. **Elimina el directorio `target/`** completo
2. **Borra todos los archivos compilados** (.class)
3. **Elimina JARs/WARs** generados previamente
4. **Limpia reportes de tests** antiguos
5. **Remueve archivos temporales** de Maven

### Antes de ejecutar:
```
proyecto/
├── src/
├── target/                    ← Existe
│   ├── classes/
│   ├── test-classes/
│   └── spring-mvc-rest-api.war
└── pom.xml
```

### Después de ejecutar:
```
proyecto/
├── src/
└── pom.xml                    ← target/ eliminado
```

### Salida en consola:
```
[INFO] Scanning for projects...
[INFO]
[INFO] ---------------< com.abpro.proyecto:spring-mvc-rest-api >---------------
[INFO] Building Spring MVC REST API - Actividad Grupal 1.0.0-SNAPSHOT
[INFO]   from pom.xml
[INFO] --------------------------------[ war ]---------------------------------
[INFO]
[INFO] --- maven-clean-plugin:3.2.0:clean (default-clean) @ spring-mvc-rest-api ---
[INFO] Deleting /home/user/M6_AE1_ABPRO/target
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  0.234 s
[INFO] Finished at: 2025-10-23T22:50:00Z
[INFO] ------------------------------------------------------------------------
```

### Cuándo usar:
- ✅ Antes de crear un build limpio para producción
- ✅ Cuando hay problemas con archivos compilados antiguos
- ✅ Si cambios en el código no se reflejan
- ✅ Antes de compartir el proyecto (reducir tamaño)
- ✅ Para resolver problemas de caché

### Plugin involucrado:
```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-clean-plugin</artifactId>
    <version>3.2.0</version>
</plugin>
```

---

## 2. mvn compile

### Descripción
Compila el código fuente principal del proyecto.

### Comando
```bash
mvn compile
```

### ¿Qué hace exactamente?

1. **Descarga dependencias** si no están en el repositorio local
2. **Compila archivos .java** de `src/main/java`
3. **Genera archivos .class** en `target/classes/`
4. **Copia recursos** de `src/main/resources` a `target/classes/`
5. **Valida el código** (sintaxis, imports, etc.)

### Estructura generada:
```
target/
├── classes/                          ← Código compilado
│   └── com/abpro/proyecto/
│       ├── config/
│       │   ├── WebAppInitializer.class
│       │   └── WebConfig.class
│       ├── controller/
│       │   └── PersonaController.class
│       ├── model/
│       │   └── Persona.class
│       ├── dto/
│       │   └── ApiResponse.class
│       └── exception/
│           └── GlobalExceptionHandler.class
└── generated-sources/                ← Código generado automáticamente
```

### Salida en consola:
```
[INFO] Scanning for projects...
[INFO]
[INFO] ---------------< com.abpro.proyecto:spring-mvc-rest-api >---------------
[INFO] Building Spring MVC REST API - Actividad Grupal 1.0.0-SNAPSHOT
[INFO]   from pom.xml
[INFO] --------------------------------[ war ]---------------------------------
[INFO]
[INFO] --- maven-resources-plugin:3.3.1:resources (default-resources) @ spring-mvc-rest-api ---
[INFO] Copying 0 resource from src/main/resources to target/classes
[INFO]
[INFO] --- maven-compiler-plugin:3.12.1:compile (default-compile) @ spring-mvc-rest-api ---
[INFO] Compiling 7 source files with javac [debug target 17] to target/classes
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  2.345 s
[INFO] Finished at: 2025-10-23T22:50:00Z
[INFO] ------------------------------------------------------------------------
```

### Archivos compilados en este proyecto:
```
Compilando 7 archivos:
1. WebAppInitializer.java → WebAppInitializer.class
2. WebConfig.java → WebConfig.class
3. PersonaController.java → PersonaController.class
4. Persona.java → Persona.class
5. ApiResponse.java → ApiResponse.class
6. GlobalExceptionHandler.java → GlobalExceptionHandler.class
7. PersonaController$PersonaConId.java → PersonaController$PersonaConId.class (clase interna)
```

### Si hay errores:
```
[INFO] -------------------------------------------------------------
[ERROR] COMPILATION ERROR :
[INFO] -------------------------------------------------------------
[ERROR] /proyecto/src/main/java/Foo.java:[10,8] cannot find symbol
  symbol:   class Bar
  location: class Foo
[INFO] 1 error
[INFO] -------------------------------------------------------------
[INFO] BUILD FAILURE
```

### Cuándo usar:
- ✅ Para verificar que el código compila sin errores
- ✅ Antes de ejecutar la aplicación
- ✅ Para ver errores de compilación rápidamente
- ✅ Durante desarrollo activo

### Plugin involucrado:
```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-compiler-plugin</artifactId>
    <version>3.12.1</version>
    <configuration>
        <source>17</source>
        <target>17</target>
    </configuration>
</plugin>
```

---

## 3. mvn test

### Descripción
Ejecuta todas las pruebas unitarias del proyecto.

### Comando
```bash
mvn test
```

### ¿Qué hace exactamente?

1. **Ejecuta `mvn compile`** automáticamente (si es necesario)
2. **Compila el código de tests** de `src/test/java`
3. **Ejecuta todas las clases de test** (clases con @Test)
4. **Genera reportes** en `target/surefire-reports/`
5. **Muestra estadísticas** de tests ejecutados

### Estructura generada:
```
target/
├── classes/                          ← Código principal compilado
├── test-classes/                     ← Tests compilados
│   └── com/abpro/proyecto/
│       ├── controller/
│       │   └── PersonaControllerTest.class
│       └── model/
│           └── PersonaTest.class
└── surefire-reports/                 ← Reportes de tests
    ├── com.abpro.proyecto.controller.PersonaControllerTest.txt
    ├── com.abpro.proyecto.model.PersonaTest.txt
    ├── TEST-com.abpro.proyecto.controller.PersonaControllerTest.xml
    └── TEST-com.abpro.proyecto.model.PersonaTest.xml
```

### Salida en consola:
```
[INFO] Scanning for projects...
[INFO]
[INFO] ---------------< com.abpro.proyecto:spring-mvc-rest-api >---------------
[INFO] Building Spring MVC REST API - Actividad Grupal 1.0.0-SNAPSHOT
[INFO]   from pom.xml
[INFO] --------------------------------[ war ]---------------------------------
[INFO]
[INFO] --- maven-resources-plugin:3.3.1:resources (default-resources) @ spring-mvc-rest-api ---
[INFO] Copying 0 resource from src/main/resources to target/classes
[INFO]
[INFO] --- maven-compiler-plugin:3.12.1:compile (default-compile) @ spring-mvc-rest-api ---
[INFO] Nothing to compile - all classes are up to date
[INFO]
[INFO] --- maven-resources-plugin:3.3.1:testResources (default-testResources) @ spring-mvc-rest-api ---
[INFO] skip non existing resourceDirectory /home/user/M6_AE1_ABPRO/src/test/resources
[INFO]
[INFO] --- maven-compiler-plugin:3.12.1:testCompile (default-testCompile) @ spring-mvc-rest-api ---
[INFO] Compiling 2 source files with javac [debug target 17] to target/test-classes
[INFO]
[INFO] --- maven-surefire-plugin:3.2.5:test (default-test) @ spring-mvc-rest-api ---
[INFO] Using auto detected provider org.apache.maven.surefire.junitplatform.JUnitPlatformProvider
[INFO]
[INFO] -------------------------------------------------------
[INFO]  T E S T S
[INFO] -------------------------------------------------------
[INFO] Running com.abpro.proyecto.controller.PersonaControllerTest
[INFO] Tests run: 7, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 1.234 s -- in com.abpro.proyecto.controller.PersonaControllerTest
[INFO] Running com.abpro.proyecto.model.PersonaTest
[INFO] Tests run: 10, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.567 s -- in com.abpro.proyecto.model.PersonaTest
[INFO]
[INFO] Results:
[INFO]
[INFO] Tests run: 17, Failures: 0, Errors: 0, Skipped: 0
[INFO]
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  4.567 s
[INFO] Finished at: 2025-10-23T22:50:00Z
[INFO] ------------------------------------------------------------------------
```

### Tests ejecutados en este proyecto:

#### PersonaTest.java (10 tests):
```
✓ testPersonaValida
✓ testNombreNulo
✓ testNombreVacio
✓ testNombreMuyCorto
✓ testEdadNula
✓ testEdadNegativa
✓ testEdadExcesiva
✓ testEdadCero
✓ testEdadLimiteSuperior
✓ testConstructorYGetters
```

#### PersonaControllerTest.java (7 tests):
```
✓ testHealthCheck
✓ testListarTodasPersonasVacio
✓ testCrearPersonaValida
✓ testCrearPersonaNombreVacio
✓ testCrearPersonaEdadNegativa
✓ testCrearPersonaEdadExcesiva
✓ testObtenerPersonaNoExistente
```

### Si hay fallos:
```
[INFO] -------------------------------------------------------
[INFO]  T E S T S
[INFO] -------------------------------------------------------
[INFO] Running com.abpro.proyecto.model.PersonaTest
[ERROR] Tests run: 10, Failures: 1, Errors: 0, Skipped: 0, Time elapsed: 0.567 s <<< FAILURE!
[ERROR] testPersonaValida  Time elapsed: 0.123 s  <<< FAILURE!
org.junit.jupiter.api.AssertionFailedError: expected: <true> but was: <false>
    at org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
[INFO]
[INFO] Results:
[INFO]
[ERROR] Failures:
[ERROR]   PersonaTest.testPersonaValida:42 expected: <true> but was: <false>
[INFO]
[ERROR] Tests run: 17, Failures: 1, Errors: 0, Skipped: 0
[INFO]
[INFO] ------------------------------------------------------------------------
[INFO] BUILD FAILURE
[INFO] ------------------------------------------------------------------------
```

### Reportes generados:

**Archivo TXT** (`PersonaControllerTest.txt`):
```
-------------------------------------------------------------------------------
Test set: com.abpro.proyecto.controller.PersonaControllerTest
-------------------------------------------------------------------------------
Tests run: 7, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 1.234 s - in com.abpro.proyecto.controller.PersonaControllerTest
```

**Archivo XML** (`TEST-PersonaControllerTest.xml`):
```xml
<?xml version="1.0" encoding="UTF-8"?>
<testsuite name="com.abpro.proyecto.controller.PersonaControllerTest"
           tests="7" failures="0" errors="0" skipped="0" time="1.234">
  <testcase name="testHealthCheck" classname="com.abpro.proyecto.controller.PersonaControllerTest" time="0.234"/>
  <testcase name="testCrearPersonaValida" classname="com.abpro.proyecto.controller.PersonaControllerTest" time="0.156"/>
  <!-- ... más tests ... -->
</testsuite>
```

### Cuándo usar:
- ✅ Antes de cada commit
- ✅ Para verificar que los cambios no rompieron nada
- ✅ Como parte del CI/CD pipeline
- ✅ Después de refactorizar código
- ✅ Para validar nuevas funcionalidades

### Opciones útiles:
```bash
# Ejecutar solo una clase de test
mvn test -Dtest=PersonaTest

# Ejecutar un método específico
mvn test -Dtest=PersonaTest#testPersonaValida

# Saltar tests (NO recomendado)
mvn test -DskipTests

# Tests con información detallada
mvn test -X
```

### Plugin involucrado:
```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-surefire-plugin</artifactId>
    <version>3.2.5</version>
</plugin>
```

---

## 4. mvn package

### Descripción
Empaqueta la aplicación compilada en un formato distribuible (JAR, WAR, etc.).

### Comando
```bash
mvn package
```

### ¿Qué hace exactamente?

1. **Ejecuta `mvn compile`** (compila código principal)
2. **Ejecuta `mvn test`** (ejecuta todas las pruebas)
3. **Empaqueta la aplicación** según `<packaging>` en pom.xml
4. **Genera el archivo WAR** en `target/`
5. **Incluye todas las dependencias** en `WEB-INF/lib/`

### En este proyecto:
```xml
<packaging>war</packaging>  <!-- Genera un archivo WAR -->
```

### Estructura del WAR generado:
```
target/spring-mvc-rest-api.war
├── META-INF/
│   └── MANIFEST.MF              ← Metadatos del WAR
├── WEB-INF/
│   ├── classes/                 ← Nuestras clases compiladas
│   │   └── com/abpro/proyecto/
│   │       ├── config/
│   │       ├── controller/
│   │       ├── model/
│   │       ├── dto/
│   │       └── exception/
│   └── lib/                     ← Dependencias (JARs)
│       ├── spring-webmvc-6.1.4.jar
│       ├── spring-core-6.1.4.jar
│       ├── spring-beans-6.1.4.jar
│       ├── spring-context-6.1.4.jar
│       ├── spring-web-6.1.4.jar
│       ├── jackson-databind-2.16.1.jar
│       ├── jackson-core-2.16.1.jar
│       ├── jackson-annotations-2.16.1.jar
│       ├── jakarta.validation-api-3.0.2.jar
│       ├── hibernate-validator-8.0.1.Final.jar
│       ├── slf4j-api-2.0.11.jar
│       ├── slf4j-simple-2.0.11.jar
│       └── ... (todas las dependencias transitivas)
```

### Salida en consola:
```
[INFO] Scanning for projects...
[INFO]
[INFO] ---------------< com.abpro.proyecto:spring-mvc-rest-api >---------------
[INFO] Building Spring MVC REST API - Actividad Grupal 1.0.0-SNAPSHOT
[INFO]   from pom.xml
[INFO] --------------------------------[ war ]---------------------------------
[INFO]
[INFO] --- maven-resources-plugin:3.3.1:resources (default-resources) @ spring-mvc-rest-api ---
[INFO] Copying 0 resource from src/main/resources to target/classes
[INFO]
[INFO] --- maven-compiler-plugin:3.12.1:compile (default-compile) @ spring-mvc-rest-api ---
[INFO] Nothing to compile - all classes are up to date
[INFO]
[INFO] --- maven-resources-plugin:3.3.1:testResources (default-testResources) @ spring-mvc-rest-api ---
[INFO] skip non existing resourceDirectory /home/user/M6_AE1_ABPRO/src/test/resources
[INFO]
[INFO] --- maven-compiler-plugin:3.12.1:testCompile (default-testCompile) @ spring-mvc-rest-api ---
[INFO] Nothing to compile - all classes are up to date
[INFO]
[INFO] --- maven-surefire-plugin:3.2.5:test (default-test) @ spring-mvc-rest-api ---
[INFO]
[INFO] -------------------------------------------------------
[INFO]  T E S T S
[INFO] -------------------------------------------------------
[INFO] Running com.abpro.proyecto.controller.PersonaControllerTest
[INFO] Tests run: 7, Failures: 0, Errors: 0, Skipped: 0
[INFO] Running com.abpro.proyecto.model.PersonaTest
[INFO] Tests run: 10, Failures: 0, Errors: 0, Skipped: 0
[INFO]
[INFO] Results:
[INFO] Tests run: 17, Failures: 0, Errors: 0, Skipped: 0
[INFO]
[INFO]
[INFO] --- maven-war-plugin:3.4.0:war (default-war) @ spring-mvc-rest-api ---
[INFO] Packaging webapp
[INFO] Assembling webapp [spring-mvc-rest-api] in [/home/user/M6_AE1_ABPRO/target/spring-mvc-rest-api]
[INFO] Processing war project
[INFO] Copying webapp resources [/home/user/M6_AE1_ABPRO/src/main/webapp]
[INFO] Building war: /home/user/M6_AE1_ABPRO/target/spring-mvc-rest-api.war
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  6.789 s
[INFO] Finished at: 2025-10-23T22:50:00Z
[INFO] ------------------------------------------------------------------------
```

### Archivos en target/:
```
target/
├── spring-mvc-rest-api.war       ← Archivo WAR final (12-15 MB aprox)
├── spring-mvc-rest-api/          ← Contenido desempaquetado
│   ├── META-INF/
│   └── WEB-INF/
├── classes/                       ← Clases compiladas
├── test-classes/                  ← Tests compilados
├── surefire-reports/              ← Reportes de tests
└── maven-archiver/                ← Metadatos de Maven
    └── pom.properties
```

### Tamaño del WAR:
```
spring-mvc-rest-api.war
├── WEB-INF/classes/          ~50 KB    (nuestro código)
└── WEB-INF/lib/              ~12 MB    (dependencias)
─────────────────────────────────────
Total:                        ~12 MB
```

### Cuándo usar:
- ✅ Para crear el archivo distribuible
- ✅ Antes de desplegar en producción
- ✅ Para compartir la aplicación
- ✅ Como parte del build de CI/CD
- ✅ Para desplegar en Tomcat, Jetty, etc.

### Cómo desplegar el WAR:

#### Opción 1: Tomcat
```bash
cp target/spring-mvc-rest-api.war $TOMCAT_HOME/webapps/
# Tomcat lo desempaquetará automáticamente
```

#### Opción 2: Jetty
```bash
java -jar $JETTY_HOME/start.jar --module=deploy target/spring-mvc-rest-api.war
```

#### Opción 3: Jetty Plugin (desarrollo)
```bash
mvn jetty:run
```

### Opciones útiles:
```bash
# Package sin ejecutar tests (NO recomendado)
mvn package -DskipTests

# Package con perfil de producción
mvn package -Pprod

# Package limpio
mvn clean package
```

### Plugin involucrado:
```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-war-plugin</artifactId>
    <version>3.4.0</version>
    <configuration>
        <failOnMissingWebXml>false</failOnMissingWebXml>
    </configuration>
</plugin>
```

---

## 5. mvn install

### Descripción
Instala el artefacto generado en el repositorio local de Maven.

### Comando
```bash
mvn install
```

### ¿Qué hace exactamente?

1. **Ejecuta `mvn compile`** (compila código)
2. **Ejecuta `mvn test`** (ejecuta tests)
3. **Ejecuta `mvn package`** (genera WAR)
4. **Instala el WAR** en `~/.m2/repository/`
5. **Instala el POM** en el repositorio local
6. **Hace el artefacto disponible** para otros proyectos locales

### Ubicación de instalación:
```
~/.m2/repository/
└── com/abpro/proyecto/spring-mvc-rest-api/1.0.0-SNAPSHOT/
    ├── spring-mvc-rest-api-1.0.0-SNAPSHOT.war       ← Archivo WAR
    ├── spring-mvc-rest-api-1.0.0-SNAPSHOT.pom       ← POM
    ├── _remote.repositories                          ← Metadatos
    └── maven-metadata-local.xml                      ← Versiones
```

### Salida en consola:
```
[INFO] Scanning for projects...
[INFO]
[INFO] ---------------< com.abpro.proyecto:spring-mvc-rest-api >---------------
[INFO] Building Spring MVC REST API - Actividad Grupal 1.0.0-SNAPSHOT
[INFO]   from pom.xml
[INFO] --------------------------------[ war ]---------------------------------
[INFO]
[INFO] --- maven-resources-plugin:3.3.1:resources (default-resources) @ spring-mvc-rest-api ---
[INFO] Copying 0 resource from src/main/resources to target/classes
[INFO]
[INFO] --- maven-compiler-plugin:3.12.1:compile (default-compile) @ spring-mvc-rest-api ---
[INFO] Nothing to compile - all classes are up to date
[INFO]
[INFO] --- maven-resources-plugin:3.3.1:testResources (default-testResources) @ spring-mvc-rest-api ---
[INFO] skip non existing resourceDirectory /home/user/M6_AE1_ABPRO/src/test/resources
[INFO]
[INFO] --- maven-compiler-plugin:3.12.1:testCompile (default-testCompile) @ spring-mvc-rest-api ---
[INFO] Nothing to compile - all classes are up to date
[INFO]
[INFO] --- maven-surefire-plugin:3.2.5:test (default-test) @ spring-mvc-rest-api ---
[INFO]
[INFO] -------------------------------------------------------
[INFO]  T E S T S
[INFO] -------------------------------------------------------
[INFO] Running com.abpro.proyecto.controller.PersonaControllerTest
[INFO] Tests run: 7, Failures: 0, Errors: 0, Skipped: 0
[INFO] Running com.abpro.proyecto.model.PersonaTest
[INFO] Tests run: 10, Failures: 0, Errors: 0, Skipped: 0
[INFO]
[INFO] Results:
[INFO] Tests run: 17, Failures: 0, Errors: 0, Skipped: 0
[INFO]
[INFO]
[INFO] --- maven-war-plugin:3.4.0:war (default-war) @ spring-mvc-rest-api ---
[INFO] Packaging webapp
[INFO] Assembling webapp [spring-mvc-rest-api] in [/home/user/M6_AE1_ABPRO/target/spring-mvc-rest-api]
[INFO] Processing war project
[INFO] Copying webapp resources [/home/user/M6_AE1_ABPRO/src/main/webapp]
[INFO] Building war: /home/user/M6_AE1_ABPRO/target/spring-mvc-rest-api.war
[INFO]
[INFO] --- maven-install-plugin:3.1.1:install (default-install) @ spring-mvc-rest-api ---
[INFO] Installing /home/user/M6_AE1_ABPRO/target/spring-mvc-rest-api.war to ~/.m2/repository/com/abpro/proyecto/spring-mvc-rest-api/1.0.0-SNAPSHOT/spring-mvc-rest-api-1.0.0-SNAPSHOT.war
[INFO] Installing /home/user/M6_AE1_ABPRO/pom.xml to ~/.m2/repository/com/abpro/proyecto/spring-mvc-rest-api/1.0.0-SNAPSHOT/spring-mvc-rest-api-1.0.0-SNAPSHOT.pom
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  7.890 s
[INFO] Finished at: 2025-10-23T22:50:00Z
[INFO] ------------------------------------------------------------------------
```

### Ahora otros proyectos pueden usarlo:

```xml
<!-- En el pom.xml de otro proyecto -->
<dependency>
    <groupId>com.abpro.proyecto</groupId>
    <artifactId>spring-mvc-rest-api</artifactId>
    <version>1.0.0-SNAPSHOT</version>
    <type>war</type>
</dependency>
```

### Cuándo usar:
- ✅ En proyectos multi-módulo
- ✅ Cuando otros proyectos locales dependen de este
- ✅ Para hacer el artefacto disponible localmente
- ✅ Como parte del build completo

### Diferencia con `mvn deploy`:
- **install**: Instala en repositorio **local** (`~/.m2/`)
- **deploy**: Publica en repositorio **remoto** (Nexus, Artifactory, etc.)

### Plugin involucrado:
```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-install-plugin</artifactId>
    <version>3.1.1</version>
</plugin>
```

---

## Ciclo de vida completo

### Orden de ejecución:
```
mvn install
    ↓
    ├─→ validate      (valida proyecto)
    ├─→ compile       (compila código)
    ├─→ test          (ejecuta tests)
    ├─→ package       (crea WAR)
    └─→ install       (instala en ~/.m2/)
```

### Comando completo más común:
```bash
mvn clean install
```

Esto ejecuta:
1. **clean**: Limpia `target/`
2. **validate**: Valida el proyecto
3. **compile**: Compila código
4. **test**: Ejecuta tests
5. **package**: Crea WAR
6. **install**: Instala en `~/.m2/`

---

## Comandos adicionales útiles

### Ver árbol de dependencias:
```bash
mvn dependency:tree
```

**Salida:**
```
[INFO] com.abpro.proyecto:spring-mvc-rest-api:war:1.0.0-SNAPSHOT
[INFO] +- org.springframework:spring-webmvc:jar:6.1.4:compile
[INFO] |  +- org.springframework:spring-beans:jar:6.1.4:compile
[INFO] |  +- org.springframework:spring-core:jar:6.1.4:compile
[INFO] |  +- org.springframework:spring-context:jar:6.1.4:compile
[INFO] |  \- org.springframework:spring-web:jar:6.1.4:compile
[INFO] +- com.fasterxml.jackson.core:jackson-databind:jar:2.16.1:compile
[INFO] |  +- com.fasterxml.jackson.core:jackson-core:jar:2.16.1:compile
[INFO] |  \- com.fasterxml.jackson.core:jackson-annotations:jar:2.16.1:compile
[INFO] \- ...
```

### Limpiar repositorio local:
```bash
mvn dependency:purge-local-repository
```

### Ver información del proyecto:
```bash
mvn help:effective-pom
```

### Validar pom.xml:
```bash
mvn validate
```

### Ver plugins disponibles:
```bash
mvn help:describe -Dplugin=compiler
```

---

**Generado con Claude Code** 🤖
