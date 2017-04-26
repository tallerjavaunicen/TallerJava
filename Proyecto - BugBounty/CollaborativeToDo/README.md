# Como configurar este proyecto

Importar como proyecto Gradle al IDE:
- En Eclipse: instalar BuildShip Gradle Integration a utilizando el Marketplace (Help -> Eclipse Marketplace). 
- En IntelliJ: instalar previamente Gradle y usar el asistente de Gradle para importar proyectos.

# Ejecutar el proyecto

Ir a la clase CollaborativeToDo y ejecutar el main. El frontend les va a tirar errores, ya que faltan las dependencias.

# Obtener dependencias del frontend
- Instalar nodejs.
- correr
```
npm install
```

en src/main/resources/public (donde esté la carpeta package.json).

# Construir el frontend

Correr

```
npm run build
```

Eso ejecuta el compilador de typescript en modo watch (se queda esperando por se hacen cambios en los archivos). Ctrl+C para cancelarlo una vez que termina la compilación.

# Tests

Los tests se encuentran en src/test/java y se ejecutan haciendo Run as JUnit al hacer click derecho en la clase.
