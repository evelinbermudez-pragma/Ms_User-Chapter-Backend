Write-Host "========================================" -ForegroundColor Green
Write-Host "Generando reportes de Jacoco..." -ForegroundColor Green
Write-Host "========================================" -ForegroundColor Green
Write-Host ""

$projectPath = Split-Path -Parent $MyInvocation.MyCommand.Path
Set-Location $projectPath

Write-Host "Ejecutando tests y generando reportes de cobertura..." -ForegroundColor Yellow

# Ejecutar tests y generar reportes
& .\gradlew.bat clean test jacocoTestReport

if ($LASTEXITCODE -ne 0) {
    Write-Host "Error durante la ejecucion de tests" -ForegroundColor Red
    exit 1
}

Write-Host ""
Write-Host "========================================" -ForegroundColor Green
Write-Host "Reportes generados exitosamente!" -ForegroundColor Green
Write-Host "========================================" -ForegroundColor Green
Write-Host ""
Write-Host "Reportes disponibles en:" -ForegroundColor Yellow
Write-Host ""
Write-Host "1. Reporte de Tests:" -ForegroundColor Cyan
$testReportPath = "$projectPath\build\reports\tests\test\index.html"
Write-Host "   $testReportPath" -ForegroundColor White
Write-Host ""
Write-Host "2. Reporte de Cobertura (Jacoco):" -ForegroundColor Cyan
$jacocoReportPath = "$projectPath\build\reports\jacoco\test\html\index.html"
Write-Host "   $jacocoReportPath" -ForegroundColor White
Write-Host ""

# Intenta abrir el reporte en el navegador
Write-Host "Abriendo reportes en el navegador..." -ForegroundColor Yellow
if (Test-Path $jacocoReportPath) {
    Start-Process $jacocoReportPath
}

