@echo off
cls
javac -encoding UTF-8 Main.java
if %errorlevel% neq 0 (
    echo Compilation failed!
    pause
    exit /b %errorlevel%
)
java Main
pause
del *.class
del entity\*.class
del dsa\*.class
del service\*.class
del util\*.class
