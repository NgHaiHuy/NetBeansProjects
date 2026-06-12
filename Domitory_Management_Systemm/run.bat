@echo off
chcp 65001 >nul
echo ===================================
echo   Dormitory Management System
echo ===================================
echo.

REM Tao thu muc build neu chua co
if not exist "build\classes" mkdir build\classes

REM Bien dich ma nguon Java (JDK 1.8, UTF-8)
echo [1/2] Dang bien dich...
javac -encoding UTF-8 -source 1.8 -target 1.8 -d build\classes src\Main.java src\dsa\*.java src\entity\*.java src\service\*.java src\util\*.java

if %errorlevel% neq 0 (
    echo.
    echo [LOI] Bien dich that bai! Kiem tra lai ma nguon.
    pause
    exit /b 1
)

echo [2/2] Dang chay chuong trinh...
echo.

REM Chay chuong trinh voi UTF-8
java -Dfile.encoding=UTF-8 -cp build\classes Main

echo.
pause
