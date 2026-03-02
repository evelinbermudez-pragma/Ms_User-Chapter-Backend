# Configuración de Tests del Microservicio

## Resumen de Implementación

Se ha configurado completamente el proyecto con todas las dependencias y herramientas necesarias para realizar pruebas unitarias, de integración y generación de reportes de cobertura.

## ✅ Implementaciones Realizadas

### 1. Plugin de Jacoco en build.gradle
```gradle
plugins {
    id 'jacoco'
}

jacoco {
    toolVersion = "0.8.10"
}

jacocoTestReport {
    dependsOn test
    reports {
        xml.required = true
        html.required = true
        csv.required = false
    }
}
```

**Propósito**: Generar reportes de cobertura de código que muestran qué porcentaje de tu código está siendo testeado.

---

### 2. Dependencias de Testing en build.gradle

#### JUnit 5 (Jupiter)
```gradle
testImplementation 'org.junit.jupiter:junit-jupiter-api'
testRuntimeOnly 'org.junit.jupiter:junit-jupiter-engine'
testRuntimeOnly 'org.junit.platform:junit-platform-launcher'
```
**Propósito**: Framework moderno para escribir tests unitarios en Java.

#### Mockito
```gradle
testImplementation 'org.mockito:mockito-core:5.2.0'
testImplementation 'org.mockito:mockito-junit-jupiter:5.2.0'
```
**Propósito**: Framework para crear mocks (objetos simulados) de dependencias en tests.

#### JUnit 4 (Compatibilidad)
```gradle
testImplementation 'junit:junit:4.13.2'
```
**Propósito**: Compatibilidad con tests legacy que aún usen JUnit 4.

#### H2 Database
```gradle
testImplementation 'com.h2database:h2'
```
**Propósito**: Base de datos en memoria para tests, sin necesidad de MySQL.

---

### 3. Archivo application-test.properties

**Ubicación**: `src/main/resources/application-test.properties`

**Configuración**:
```properties
# Configuración de H2 (base de datos en memoria)
spring.datasource.url=jdbc:h2:mem:testdb;MODE=MySQL
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=

# JPA automáticamente crea y elimina tablas entre tests
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect

# Carga datos de prueba
spring.sql.init.mode=always
spring.sql.init.data-locations=classpath:data-test.sql
```

**Ventajas**:
- Tests rápidos sin dependencia de MySQL
- Datos limpios en cada test
- Configuración aislada de producción

---

### 4. Archivo data-test.sql

**Ubicación**: `src/main/resources/data-test.sql`

**Contenido**:
```sql
INSERT INTO role (name) VALUES ('ADMIN');
INSERT INTO role (name) VALUES ('OWNER');
INSERT INTO role (name) VALUES ('CLIENT');
INSERT INTO role (name) VALUES ('EMPLOYEE');
```

**Propósito**: Proporciona datos iniciales necesarios para que los tests se ejecuten correctamente.

---

### 5. Anotación @TestPropertySource en Tests

**En AdminUseCaseTest.java**:
```java
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@TestPropertySource(locations = "classpath:application-test.properties")
class AdminUseCaseTest {
    // ...
}
```

**En MsUserApplicationTests.java** (Test de Contexto):
```java
@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class MsUserApplicationTests {
    // ...
}
```

**Propósito**: Indica a Spring que use `application-test.properties` en lugar de `application.yaml` durante los tests.

---

### 6. Scripts para Generar Reportes

#### Windows Batch (generate-coverage-report.bat)
```batch
@echo off
call .\gradlew.bat clean test jacocoTestReport
echo Reportes en: build\reports\jacoco\test\html\index.html
```

**Uso**: `generate-coverage-report.bat`

#### PowerShell (generate-coverage-report.ps1)
```powershell
.\gradlew.bat clean test jacocoTestReport
Start-Process "$projectPath\build\reports\jacoco\test\html\index.html"
```

**Uso**: `.\generate-coverage-report.ps1`

---

## 🚀 Cómo Usar

### Ejecutar Tests

```bash
# Opción 1: Todos los tests
./gradlew test

# Opción 2: Tests específicos
./gradlew test --tests AdminUseCaseTest

# Opción 3: Con reportes (recomendado)
./gradlew test jacocoTestReport

# Opción 4: Task personalizado
./gradlew testReport
```

### Generar Reportes

**Windows CMD**:
```bash
generate-coverage-report.bat
```

**PowerShell**:
```powershell
.\generate-coverage-report.ps1
```

### Abrir Reportes

Los reportes se generan en:

1. **Tests**: `build/reports/tests/test/index.html`
2. **Cobertura**: `build/reports/jacoco/test/html/index.html`

---

## 📊 Estructura de Reportes Generados

### Reporte de Jacoco

Muestra por cada clase/paquete:

| Métrica | Descripción |
|---------|-------------|
| Line Coverage | % de líneas ejecutadas en tests |
| Branch Coverage | % de decisiones lógicas cubiertas |
| Complexity | Complejidad ciclomática del código |

Ejemplo:
```
Package: com.chapter.user.domain.usecase
├── AdminUseCase
│   ├── Line: 85%
│   ├── Branch: 80%
│   └── Method: 100%
```

---

## ✨ Mejores Prácticas Implementadas

### 1. Separación de Configuraciones
- `application.yaml` → Producción (MySQL)
- `application-test.properties` → Tests (H2)

### 2. Aislamiento de Tests
- Base de datos en memoria se crea y destruye por test
- Sin efectos secundarios entre pruebas
- Sin dependencias de servicios externos

### 3. Inyección de Mocks
```java
@Mock
private IUserPersistencePort userPersistencePort;

@InjectMocks
private AdminUseCase adminUseCase;  // Se inyectan los mocks automáticamente
```

### 4. Nomenclatura Estándar
```
testMethodName_givenCondition_expectedResult
```

Ejemplo:
```
saveOwner_whenUserIsOlder_shouldSaveUser()
```

---

## 🔧 Configuración Excluida de Cobertura

Para mantener métricas relevantes, se excluyen:

```gradle
exclude: [
    '**/dto/**',      // DTOs - solo transferencia de datos
    '**/entity/**',   // Entities - mapeo automático JPA
    '**/config/**'    // Configuración - difícil de testear
]
```

---

## 📝 Ejemplo Completo de Test

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
    void saveOwner_whenUserIsOlder_shouldSaveUser() {
        // ARRANGE - Preparar datos
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, -30);
        Date dateOfBirth = cal.getTime();
        User user = new User(null, "John", "Doe", 12345678L, "+573001234567", 
                           "john.doe@example.com", dateOfBirth, null, "password123", null);

        // ACT - Ejecutar la acción
        adminUseCase.saveOwner(user);

        // ASSERT - Verificar resultados
        verify(userPersistencePort).createUser(user);
    }

    @Test
    void saveOwner_whenUserIsYounger_shouldThrowException() {
        // ARRANGE
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, -17);  // Usuario menor de edad
        Date dateOfBirth = cal.getTime();
        User user = new User(null, "John", "Doe", 1234678908L, "+573001234567", 
                           "john.doe@example.com", dateOfBirth, null, "password123", null);

        // ACT & ASSERT
        assertThrows(IsOlderUserException.class, () -> adminUseCase.saveOwner(user));
        verify(userPersistencePort, never()).createUser(any(User.class));
    }
}
```

---

## ✅ Checklist de Verificación

- [x] Plugin Jacoco agregado a build.gradle
- [x] Dependencia de JUnit 5 (Jupiter)
- [x] Dependencia de Mockito 5.2.0
- [x] Dependencia de H2 Database
- [x] Archivo application-test.properties creado
- [x] Archivo data-test.sql creado
- [x] Anotación @TestPropertySource en tests
- [x] Scripts para generar reportes (BAT y PS1)
- [x] Task personalizado "testReport"
- [x] Configuración de Jacoco completa

---

## 📚 Referencias

- [Documentación Jacoco](https://www.jacoco.org/jacoco/trunk/doc/)
- [Documentación JUnit 5](https://junit.org/junit5/docs/current/user-guide/)
- [Documentación Mockito](https://javadoc.io/doc/org.mockito/mockito-core/latest/org/mockito/Mockito.html)
- [Spring Boot Testing Guide](https://spring.io/guides/gs/testing-web/)


