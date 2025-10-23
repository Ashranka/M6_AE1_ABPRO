#!/bin/bash

# Script de pruebas automatizadas para la API REST
# Autor: Equipo de Desarrollo ABPRO

BASE_URL="http://localhost:8080/api"

# Colores para output
GREEN='\033[0;32m'
RED='\033[0;31m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
NC='\033[0m' # No Color

echo -e "${BLUE}=========================================${NC}"
echo -e "${BLUE}  Pruebas Automatizadas de API REST${NC}"
echo -e "${BLUE}=========================================${NC}"
echo ""

# Función para imprimir resultados
print_test() {
    echo -e "${YELLOW}Test $1: $2${NC}"
}

print_success() {
    echo -e "${GREEN}✓ Exitoso${NC}"
}

print_error() {
    echo -e "${RED}✗ Error${NC}"
}

# Test 1: Health Check
print_test "1" "Health Check"
curl -s -X GET "$BASE_URL/personas/health" | json_pp
echo ""
echo ""

# Test 2: Crear persona válida
print_test "2" "Crear persona válida (Juan Pérez, 30)"
curl -s -X POST "$BASE_URL/personas" \
  -H "Content-Type: application/json" \
  -d '{"nombre": "Juan Pérez", "edad": 30}' | json_pp
echo ""
echo ""

# Test 3: Crear otra persona
print_test "3" "Crear persona válida (María García, 25)"
curl -s -X POST "$BASE_URL/personas" \
  -H "Content-Type: application/json" \
  -d '{"nombre": "María García", "edad": 25}' | json_pp
echo ""
echo ""

# Test 4: Listar todas las personas
print_test "4" "Listar todas las personas"
curl -s -X GET "$BASE_URL/personas" | json_pp
echo ""
echo ""

# Test 5: Obtener persona por ID
print_test "5" "Obtener persona por ID (1)"
curl -s -X GET "$BASE_URL/personas/1" | json_pp
echo ""
echo ""

# Test 6: Actualizar persona
print_test "6" "Actualizar persona (ID 1)"
curl -s -X PUT "$BASE_URL/personas/1" \
  -H "Content-Type: application/json" \
  -d '{"nombre": "Juan Pérez Actualizado", "edad": 31}' | json_pp
echo ""
echo ""

# Test 7: Validación - Nombre vacío
print_test "7" "Validación: Nombre vacío (debe retornar error 400)"
curl -s -X POST "$BASE_URL/personas" \
  -H "Content-Type: application/json" \
  -d '{"nombre": "", "edad": 25}' | json_pp
echo ""
echo ""

# Test 8: Validación - Edad negativa
print_test "8" "Validación: Edad negativa (debe retornar error 400)"
curl -s -X POST "$BASE_URL/personas" \
  -H "Content-Type: application/json" \
  -d '{"nombre": "Pedro López", "edad": -5}' | json_pp
echo ""
echo ""

# Test 9: Validación - Edad excesiva
print_test "9" "Validación: Edad mayor a 150 (debe retornar error 400)"
curl -s -X POST "$BASE_URL/personas" \
  -H "Content-Type: application/json" \
  -d '{"nombre": "Ana Martínez", "edad": 200}' | json_pp
echo ""
echo ""

# Test 10: ID no existe
print_test "10" "Obtener ID inexistente (debe retornar error 404)"
curl -s -X GET "$BASE_URL/personas/999" | json_pp
echo ""
echo ""

# Test 11: Eliminar persona
print_test "11" "Eliminar persona (ID 2)"
curl -s -X DELETE "$BASE_URL/personas/2" | json_pp
echo ""
echo ""

# Test 12: Listar después de eliminar
print_test "12" "Listar personas después de eliminación"
curl -s -X GET "$BASE_URL/personas" | json_pp
echo ""
echo ""

echo -e "${BLUE}=========================================${NC}"
echo -e "${GREEN}  Pruebas completadas${NC}"
echo -e "${BLUE}=========================================${NC}"
