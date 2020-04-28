# Como configurar este proyecto

Importar como proyecto Gradle al IDE:
- En Eclipse: instalar BuildShip Gradle Integration utilizando el Marketplace (Help -> Eclipse Marketplace). 
- En IntelliJ: instalar previamente Gradle y usar el asistente de Gradle para importar proyectos.

# Ejecutar el proyecto

Ir a la clase CollaborativeToDo y ejecutar el main. Acceder mediante http://localhost:8080. El frontend les va a tirar errores, ya que faltan las dependencias.

# Obtener dependencias del frontend
- Instalar nodejs.
- correr
```
npm install
```

en src/main/resources/public (donde esté el archivo package.json).

# Construir el frontend

Correr

```
npm run build
```

Eso ejecuta el compilador de typescript en modo watch (se queda esperando por cambios en los archivos). Ctrl+C para cancelarlo una vez que termina la compilación.

# Tests

Los tests se encuentran en src/test/java y se ejecutan haciendo Run as JUnit al hacer click derecho en la clase.

# Objetivos del proyecto

Encontrar y solucionar errores a partir de lo indicado en los errores en los tests (src/test/java). Cuidado con UserHeavyTest, que se rompe por falta de memoria y puede trabar la máquina por el uso de CPU.
Indicar:

 | Error Encontrado |                                                  | Solución |
 | ---------------- | ------------------------------------------------ | -------- |
 | ¿Por qué falla?  | ¿Dónde falla? (Clase y método o línea de código) |    Tu solución propuesta      |
 

