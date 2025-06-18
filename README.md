Este sistema permite la gestión de listas de reproducción (playlists) musicales y las canciones asociadas a cada una de ellas. A través de una API REST desarrollada con Spring Boot, se pueden realizar operaciones como crear nuevas listas, añadir canciones, ver detalles de listas específicas y más. Está diseñado para funcionar como una base para sistemas de música tipo Spotify.

La API está organizada en diferentes endpoints accesibles mediante solicitudes HTTP, y se pueden probar mediante herramientas como Postman. A continuación, se describe brevemente su funcionamiento general:

📌 Funcionalidades principales:
✅ Gestión de Listas de Reproducción
Crear una lista: permite crear una nueva lista con un nombre válido.

Ver todas las listas: recupera todas las listas existentes.

Ver detalles de una lista específica: obtiene información de una lista por nombre.

Eliminar una lista: elimina una lista de reproducción por nombre o ID (si está implementado).

✅ Gestión de Canciones
Agregar una canción a una lista: se puede añadir una canción específica a una lista dada.

Ver todas las canciones de una lista: devuelve el conjunto de canciones que pertenecen a una lista.

Eliminar canciones de una lista: permite remover canciones de una lista (opcional según implementación).

✅ Autenticación básica (si aplica)
Se incluye un endpoint de login para simular la autenticación.

Se usa JWT para la generación de tokens de acceso con roles de usuario (ADMIN, USER).

El token puede ser usado en encabezados Authorization para acceder a endpoints protegidos (si hay seguridad implementada).


Credenciales de acceso:

username : admin
password: admin123

Acceso a la console H2
jdbc:h2:~/spotifydb
user: sa
sin contraseña
