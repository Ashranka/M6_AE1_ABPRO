# 🧪 Guía de Pruebas de la API REST

## Probando con cURL

### 1. Health Check
```bash
curl -X GET http://localhost:8080/api/personas/health
```

**Respuesta esperada:**
```json
{
  "success": true,
  "message": "API REST funcionando correctamente",
  "data": "Total de personas registradas: 0",
  "timestamp": "2025-10-23T22:50:00"
}
```

---

### 2. Crear una persona válida
```bash
curl -X POST http://localhost:8080/api/personas \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "Juan Pérez",
    "edad": 30
  }'
```

**Respuesta esperada (201 Created):**
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

---

### 3. Crear otra persona
```bash
curl -X POST http://localhost:8080/api/personas \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "María García",
    "edad": 25
  }'
```

---

### 4. Listar todas las personas
```bash
curl -X GET http://localhost:8080/api/personas
```

**Respuesta esperada:**
```json
{
  "success": true,
  "message": "Lista de personas obtenida exitosamente",
  "data": [
    {
      "id": 1,
      "nombre": "Juan Pérez",
      "edad": 30
    },
    {
      "id": 2,
      "nombre": "María García",
      "edad": 25
    }
  ],
  "timestamp": "2025-10-23T22:50:00"
}
```

---

### 5. Obtener persona por ID
```bash
curl -X GET http://localhost:8080/api/personas/1
```

---

### 6. Actualizar persona
```bash
curl -X PUT http://localhost:8080/api/personas/1 \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "Juan Pérez Actualizado",
    "edad": 31
  }'
```

---

### 7. Eliminar persona
```bash
curl -X DELETE http://localhost:8080/api/personas/1
```

---

## Casos de Validación

### ❌ Caso 1: Nombre vacío
```bash
curl -X POST http://localhost:8080/api/personas \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "",
    "edad": 25
  }'
```

**Respuesta esperada (400 Bad Request):**
```json
{
  "success": false,
  "message": "Error de validación en los datos enviados",
  "data": {
    "nombre": "El nombre no puede estar vacío"
  },
  "timestamp": "2025-10-23T22:50:00"
}
```

---

### ❌ Caso 2: Edad negativa
```bash
curl -X POST http://localhost:8080/api/personas \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "Pedro López",
    "edad": -5
  }'
```

**Respuesta esperada (400 Bad Request):**
```json
{
  "success": false,
  "message": "Error de validación en los datos enviados",
  "data": {
    "edad": "La edad debe ser mayor o igual a 0"
  },
  "timestamp": "2025-10-23T22:50:00"
}
```

---

### ❌ Caso 3: Edad excesiva
```bash
curl -X POST http://localhost:8080/api/personas \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "Ana Martínez",
    "edad": 200
  }'
```

**Respuesta esperada (400 Bad Request):**
```json
{
  "success": false,
  "message": "Error de validación en los datos enviados",
  "data": {
    "edad": "La edad debe ser menor o igual a 150"
  },
  "timestamp": "2025-10-23T22:50:00"
}
```

---

### ❌ Caso 4: Múltiples errores de validación
```bash
curl -X POST http://localhost:8080/api/personas \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "",
    "edad": -10
  }'
```

**Respuesta esperada (400 Bad Request):**
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

### ❌ Caso 5: ID no existe
```bash
curl -X GET http://localhost:8080/api/personas/999
```

**Respuesta esperada (404 Not Found):**
```json
{
  "success": false,
  "message": "Persona con ID 999 no encontrada",
  "data": null,
  "timestamp": "2025-10-23T22:50:00"
}
```

---

### ❌ Caso 6: Nombre muy corto (menos de 2 caracteres)
```bash
curl -X POST http://localhost:8080/api/personas \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "A",
    "edad": 25
  }'
```

**Respuesta esperada (400 Bad Request):**
```json
{
  "success": false,
  "message": "Error de validación en los datos enviados",
  "data": {
    "nombre": "El nombre debe tener entre 2 y 50 caracteres"
  },
  "timestamp": "2025-10-23T22:50:00"
}
```

---

## Probando con Postman

### Configuración de Colección

1. **Crear nueva colección**: "Spring MVC REST API"
2. **Variables de colección**:
   - `baseUrl`: `http://localhost:8080/api`

### Requests a crear:

#### 1. Health Check
- **Método**: GET
- **URL**: `{{baseUrl}}/personas/health`

#### 2. Listar Personas
- **Método**: GET
- **URL**: `{{baseUrl}}/personas`

#### 3. Crear Persona
- **Método**: POST
- **URL**: `{{baseUrl}}/personas`
- **Headers**: `Content-Type: application/json`
- **Body** (raw JSON):
```json
{
  "nombre": "{{$randomFullName}}",
  "edad": {{$randomInt}}
}
```

#### 4. Obtener por ID
- **Método**: GET
- **URL**: `{{baseUrl}}/personas/1`

#### 5. Actualizar Persona
- **Método**: PUT
- **URL**: `{{baseUrl}}/personas/1`
- **Headers**: `Content-Type: application/json`
- **Body** (raw JSON):
```json
{
  "nombre": "Nombre Actualizado",
  "edad": 35
}
```

#### 6. Eliminar Persona
- **Método**: DELETE
- **URL**: `{{baseUrl}}/personas/1`

---

## Scripts de Prueba Automatizada

### test-api.sh
```bash
#!/bin/bash

BASE_URL="http://localhost:8080/api"

echo "========================================="
echo "Pruebas Automatizadas de API REST"
echo "========================================="
echo ""

# Test 1: Health Check
echo "Test 1: Health Check"
curl -s -X GET "$BASE_URL/personas/health" | json_pp
echo ""

# Test 2: Crear persona
echo "Test 2: Crear persona válida"
curl -s -X POST "$BASE_URL/personas" \
  -H "Content-Type: application/json" \
  -d '{"nombre": "Juan Pérez", "edad": 30}' | json_pp
echo ""

# Test 3: Listar personas
echo "Test 3: Listar todas las personas"
curl -s -X GET "$BASE_URL/personas" | json_pp
echo ""

# Test 4: Validación - Nombre vacío
echo "Test 4: Validación - Nombre vacío (debe fallar)"
curl -s -X POST "$BASE_URL/personas" \
  -H "Content-Type: application/json" \
  -d '{"nombre": "", "edad": 25}' | json_pp
echo ""

# Test 5: Validación - Edad negativa
echo "Test 5: Validación - Edad negativa (debe fallar)"
curl -s -X POST "$BASE_URL/personas" \
  -H "Content-Type: application/json" \
  -d '{"nombre": "Pedro", "edad": -5}' | json_pp
echo ""

echo "========================================="
echo "Pruebas completadas"
echo "========================================="
```

**Dar permisos y ejecutar:**
```bash
chmod +x test-api.sh
./test-api.sh
```

---

## Verificación de Resultados

### Checklist de Pruebas

- [ ] Health check retorna 200 OK
- [ ] Crear persona válida retorna 201 Created
- [ ] Listar personas retorna array
- [ ] Obtener persona por ID retorna 200 OK
- [ ] Obtener ID inexistente retorna 404 Not Found
- [ ] Actualizar persona retorna 200 OK
- [ ] Eliminar persona retorna 200 OK
- [ ] Nombre vacío retorna 400 Bad Request
- [ ] Edad negativa retorna 400 Bad Request
- [ ] Edad > 150 retorna 400 Bad Request
- [ ] Nombre < 2 caracteres retorna 400 Bad Request
- [ ] Múltiples errores retornan todos los campos con error

---

## Códigos HTTP Utilizados

| Código | Significado | Cuándo se usa |
|--------|-------------|---------------|
| 200 | OK | Operación exitosa (GET, PUT, DELETE) |
| 201 | Created | Recurso creado exitosamente (POST) |
| 400 | Bad Request | Validación fallida |
| 404 | Not Found | Recurso no encontrado |
| 500 | Internal Server Error | Error del servidor |

---

## Logs a Verificar

Al ejecutar las pruebas, deberías ver en la consola del servidor:

```
=================================================
Spring MVC REST API - Iniciado correctamente
Contexto: /api
Puerto: 8080
=================================================
```

Para cada request, verás logs similares a:
```
[INFO] Request: POST /personas
[INFO] Response: 201 Created
```

---

**Generado con Claude Code** 🤖
