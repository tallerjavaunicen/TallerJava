# Cuidado

Este proyecto depende del código de performance de la otra carpeta. Debido a como se maneja Maven, primero hagan un mvn install del otro proyecto (para que quede el jar en la carpeta .m2) y luego que lo pueda encontrar maven en este proyecto.

El error META-INF/BenchmarkList not found se debe a que primero hay que correr mvn package o mvn install sobre este proyecto para que el código de jmh tome las anotaciones del código y cree dicho archivo.

Se puede ejecutar el benchmark usando el jar creado por mvn package o directamente usando el main desde el IDE (luego de que el Benchmarklist este creado).
