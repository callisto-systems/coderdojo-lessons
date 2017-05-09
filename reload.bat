set folder=plugins
cd %folder%
for /F "delims=" %%i in ('dir /b') do (rmdir "%%i" /s/q || del "%%i" /s/q)
cd ..\../proiecte/coderdojo.git/coderdojo-projects/ctf/
call mvn clean install
if %ERRORLEVEL%  NEQ 0 ( GOTO ERROR )
cd ..\..\..\..\spigot
java -Xdebug -Xnoagent -Djava.compiler=NONE -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=4020 -jar spigot-1.11.2.jar

goto EOF

:ERROR
cd ..\..\..\..\spigot
echo *****************
echo COMPILATION ERROR
echo *****************


:EOF