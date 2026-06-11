@echo off
cls
echo Compiling Dormitory Management System...
javac -encoding UTF-8 Main.java
if %errorlevel% neq 0 (
    echo Compilation failed!
    pause
    exit /b %errorlevel%
)
echo Running Dormitory Management System...
java Main
pause
echo Cleaning up compiled class files...
del *.class
del dsa\*.class
del entity\*.class
del service\*.class
del util\*.class
