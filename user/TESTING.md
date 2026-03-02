# Configuración de Tests y Reportes de Cobertura

## Descripción

Este proyecto está configurado con todas las dependencias necesarias para ejecutar tests unitarios e integración, con generación automática de reportes de cobertura usando Jacoco.

## Dependencias Instaladas

### Testing
- **JUnit 5 (Jupiter)**: Framework para pruebas unitarias
- **Mockito 5.2.0**: Framework para mocking de dependencias
- **JUnit 4.13.2**: Compatibilidad con tests legacy
- **Spring Boot Test**: Herramientas de testing para Spring Boot

### Base de Datos para Tests
- **H2 Database**: Base de datos en memoria para tests

### Cobertura
- **Jacoco 0.8.10**: Herramienta para generación de reportes de cobertura de código

## Estructura de Archivos

```
src/main/resources/
├── application.yaml              # Configuración principal (producción)
├── application-test.properties   # Configuración para tests (H2)
├── data.sql                      # Datos iniciales (producción)
└── data-test.sql                 # Datos para tests

generate-coverage-report.bat      # Script para Windows CMD
generate-coverage-report.ps1      # Script para PowerShell
```

## Cómo Ejecutar Tests

### Opción 1: Con Gradle directamente

```bash
# Ejecutar todos los tests
./gradlew test

# Ejecutar tests de una clase específica
./gradlew test --tests AdminUseCaseTest

# Ejecutar tests y generar reporte de cobertura
./gradlew test jacocoTestReport

# Task personalizado para generar reportes completos
./gradlew testReport
```

### Opción 2: Usar los scripts proporcionados

#### Windows (CMD)
```bash
generate-coverage-report.bat
```

#### PowerShell
```powershell
.\generate-coverage-report.ps1
```

## Configuración de Tests

### application-test.properties

Este archivo configura el entorno de pruebas con:

- **Base de Datos H2**: Base de datos en memoria que se crea y destruye con cada test
- **DDL automático**: `ddl-auto=create-drop` - crea las tablas antes del test y las elimina después
- **Datos de prueba**: Carga automáticamente `data-test.sql`

### data-test.sql

Contiene los datos iniciales necesarios para ejecutar los tests:
- Roles predefinidos (ADMIN, OWNER, CLIENT, EMPLOYEE)
- Puede extenderse con más datos según sea necesario

## Reportes Generados

Después de ejecutar los tests, se generan dos tipos de reportes:

### 1. Reporte de Tests
**Ubicación**: `build/reports/tests/test/index.html`

Muestra:
- Resumen de tests ejecutados
- Tests pasados/fallidos
- Tiempos de ejecución
- Stack traces de errores

### 2. Reporte de Cobertura (Jacoco)
**Ubicación**: `build/reports/jacoco/test/html/index.html`

Muestra:
- Porcentaje de cobertura por línea
- Porcentaje de cobertura por rama
- Detalles de código cubierto/no cubierto

## Configuración de Anotaciones en Tests

Los tests están configurados con las siguientes anotaciones:

```java
@ExtendWith(MockitoExtension.class)        // Extensión de Mockito
@MockitoSettings(strictness = Strictness.LENIENT)  // Configuración estricta de mocks
@TestPropertySource(locations = "classpath:application-test.properties")  // Carga application-test.properties
```

## Estructura de un Test Unitario

Ejemplo básico:

```java
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@TestPropertySource(locations = "classpath:application-test.properties")
class AdminUseCaseTest {

    @Mock
    private IUserPersistencePort userPersistencePort;

    @InjectMocks
    private AdminUseCase adminUseCase;

    @Test
    void testExample() {
        // Given
        when(userPersistencePort.getUser(1)).thenReturn(mockUser);
        
        // When
        User result = adminUseCase.getUser(1);
        
        // Then
        assertEquals(mockUser, result);
        verify(userPersistencePort).getUser(1);
    }
}
```

## Mejores Prácticas

### 1. Nomenclatura de Tests
- Usa nombres descriptivos: `testExample()` → `saveOwner_whenUserIsOlder_shouldSaveUser()`
- Estructura: `methodName_condition_expectedResult`

### 2. Estructura AAA (Arrange-Act-Assert)
```java
// Arrange - Preparar datos
User user = new User(...);

// Act - Ejecutar la acción
User result = adminUseCase.getUser(user.getId());

// Assert - Verificar resultados
assertEquals(user, result);
```

### 3. Mocking
- Mockea solo las dependencias externas
- Evita mockear la clase bajo prueba
- Usa `@Mock` para dependencias inyectadas

### 4. Cobertura de Código
- Objetivo: Mantener cobertura > 80%
- Excluir DTOs, configuraciones y entidades JPA
- Enfocarse en lógica de negocio

## Gradle Tasks Disponibles

| Task | Descripción |
|------|-------------|
| `test` | Ejecuta todos los tests |
| `jacocoTestReport` | Genera reporte de cobertura |
| `testReport` | Task personalizado que ejecuta tests y genera reportes |
| `clean` | Limpia los artifacts compilados |

## Solución de Problemas

### Test falla con "H2" driver not found
**Solución**: Asegúrate de que `com.h2database:h2` esté en las dependencias `testImplementation`

### application-test.properties no se carga
**Solución**: Verifica que el archivo esté en `src/main/resources/` y que la anotación sea:
```java
@TestPropertySource(locations = "classpath:application-test.properties")
```

### Jacoco report no se genera
**Solución**: Asegúrate de ejecutar:
```bash
./gradlew clean test jacocoTestReport
```

## Referencias Útiles

- [Documentación de Mockito](https://javadoc.io/doc/org.mockito/mockito-core/latest/org/mockito/Mockito.html)
- [Documentación de JUnit 5](https://junit.org/junit5/docs/current/user-guide/)
- [Documentación de Jacoco](https://www.jacoco.org/jacoco/trunk/doc/)
- [Spring Boot Testing](https://spring.io/guides/gs/testing-web/)

