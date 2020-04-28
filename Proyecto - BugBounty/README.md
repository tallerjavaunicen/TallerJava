# Como configurar este proyecto

Importar como proyecto Gradle al IDE:
- Hoy casi cualquier IDE detecta automáticamente Gradle y lo configura.

# Ejecutar el proyecto

Ir a la clase CollaborativeToDo, en `src/main/java/edu.unicen.tallerjava.todo` y ejecutar el main. Acceder mediante http://localhost:8080. 
El frontend les va a tirar errores, ya que faltan compilar el código en que utiliza el framework Angular.

# Obtener dependencias del frontend
- Instalar nodejs en el sistema.
- Ejecutar
```
npm install
```

en `client` (donde esté el archivo package.json).

# Construir el frontend

En la carpeta client:

```
npm run build
```

Compila todo el frontend y lo pone en la carpeta src/main/resources/public para que Spring lo sirva al navegador.

# Ejecutar el frontend

En la carpeta client:

```
npm start
```

Esto crea usa el servidor http de Angular. El proyecto está configurado para que todos los pedidos a /api/* vayan a localhost:8080, es decir, a nuestro server.

# Tests

Los tests se encuentran en src/test/java y se ejecutan haciendo Run as JUnit al hacer click derecho en la clase.

# Objetivos del proyecto

Encontrar y solucionar errores a partir de lo indicado en los errores en los tests (src/test/java).

Indicar:

 | Error Encontrado |                                                  | Solución |
 | ---------------- | ------------------------------------------------ | -------- |
 | ¿Por qué falla?  | ¿Dónde falla? (Clase y método o línea de código) |    Tu solución propuesta      |
 

