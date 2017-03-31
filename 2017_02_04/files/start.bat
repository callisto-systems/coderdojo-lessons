for /f %%f in ('dir spigot-*.*.*.jar /B /O:-N') do @set NEWEST_EXE=%%f & goto DONE
:DONE

java -Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=4000 -jar %NEWEST_EXE%