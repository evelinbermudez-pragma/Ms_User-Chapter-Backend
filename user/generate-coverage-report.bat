@echo off
REM Script para generar reportes de Jacoco
REM Este script ejecuta los tests y genera los reportes de cobertura

echo ========================================
echo Generando reportes de Jacoco...
echo ========================================

cd /d "%~dp0"

REM Ejecutar tests y generar reportes
call .\gradlew.bat clean test jacocoTestReport

if %ERRORLEVEL% neq 0 (
    echo Error durante la ejecucion de tests
    exit /b 1
)

echo.
echo ========================================
echo Reportes generados exitosamente!
echo ========================================
echo.
echo Reportes disponibles en:
echo.
echo 1. Reporte de Tests:
echo    file:///%CD%\build\reports\tests\test\index.html
echo.
echo 2. Reporte de Cobertura (Jacoco):
echo    file:///%CD%\build\reports\jacoco\test\html\index.html
echo.
pause

