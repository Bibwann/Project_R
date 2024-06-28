@echo off

rem Compilation du projet (si nécessaire)
javac -cp "libraries/slick.jar;libraries/jar/lwjgl.jar;bin" -d bin src/fr/sae/main/Main.java

rem Exécution avec définition du java.library.path
java -Djava.library.path="libraries/native/windows" -cp "bin;libraries/slick.jar;libraries/jar/lwjgl.jar" fr.sae.main.Main

rem Exécution sans définition spécifique du java.library.path
rem java -cp "bin;libraries/slick.jar;libraries/jar/lwjgl.jar" fr.sae.main.Main

pause
