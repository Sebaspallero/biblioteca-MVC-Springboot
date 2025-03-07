# Sistema de gestión de biblioteca - MVC

Este sistema es una solución para gestionar todos los aspectos de una biblioteca, desde el manejo de libros hasta la administración de los usuarios y más. Desarrollado con SpringBoot MVC con MySQL, este sistema ofrece una manera intuitiva de organizar y controlar los recursos de la biblioteca.

## Funcionalidades Clave

- Administración de **Libros**: Permite registrar, modificar, eliminar y buscar libros de manera eficiente.

- Administración de **Editoriales**: Permite registrar, modificar, eliminar y buscar libros de manera eficiente.

- Administración de **Autores**: Permite registrar, modificar, eliminar y buscar libros de manera eficiente.

- Gestión de **Usuarios**: Sistema para registrar nuevos usuarios, roles, editar sus datos y gestionarlos a lo largo del tiempo.


## Instalación

1- Para clonar este repositorio y comenzar a trabajar en él:

   ```bash
   git clone https://github.com/Sebaspallero/biblioteca-MVC-Springboot
   ```

2-  Una vez clonado, cambia de directorio e instala las dependencias: 

   ```bash
   cd biblioteca-MVC-Springboot
   mvn clean install
   ```

- En la mayoría de los casos, no es necesario ejecutar ```mvn clean install```, a menos que haya problemas con las dependencias o se requiera una compilación limpia. En caso de querer ejecutarlo, se debe tener instalado Maven en el sistema para poder usar este comando.

- En **Visual Studio Code**, si tenes instalada la extensión **Maven for Java**, al modificar el ```pom.xml```, es necesario hacer clic en el botón azul en la parte inferior derecha para sincronizar los cambios.

- En **IntelliJ**, hacer click en el botón azul que aparece arriba a la derecha dentro del ```pom.xml``` para sincronizar los cambios.

- Si se quiere hacer un fork, simplemente se hace desde la interfaz de GitHub y luego clonalo desde tu cuenta

## Importante

- Modificar el nombre de la base de datos, el usuario y la contraseña de MySQL en el archivo ```application.properties```

## Contribuir 🌟
Si este proyecto te fue útil, considera darle una estrella ⭐ en GitHub. También podes seguirme para estar al tanto de futuras actualizaciones del repositorio y nuevos proyectos.

¡Gracias por tu apoyo! 🚀
