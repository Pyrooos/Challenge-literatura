# Challenge-literatura

## Descripción
El Proyecto es una aplicación que permite buscar libros y autores, gestionar una biblioteca local y obtener información detallada desde una API externa. 
Los usuarios pueden buscar libros por nombre, mostrar libros por idioma, listar todos los autores, verificar si un autor estaba vivo en un cierto año, y más.

## Tabla de Contenidos
- [Instalación](#instalación)
- [Uso](#uso)
- [Características](#características)
- [Contribución](#contribución)
- [Licencia](#licencia)
- [Contacto](#contacto)

## Instalación

1. Clona el repositorio:
    ```bash
    git clone https://github.com/Pyrooos/Challenge-literatura
    ```

2. Navega al directorio del proyecto:
    ```bash
   
    cd (direccion_donde_guardaste_el_archivo)Challenge-literatura
   
    ```

3. Configura la base de datos en `application.properties` ingresando los datos de tu.
   base de datos.

   Por ejemplo
   ```
   spring.datasource.url=jdbc:postgresql://${DB_HOST}/${DB_NAME}
   spring.datasource.username=${DB_USER}
   spring.datasource.password=${DB_PASSWORD}
   spring.datasource.driver-class.name=org.postgresql.Driver
   hibernate.dialect=org.hibernate.dialect.HSQLDialect

    ```
   No olvidar configurar las variables de entorno segun tu sistema operativo
   asi mismo esta configurado para crear y borrar la tabla cada vez que se inicia 
   y finaliza el programa.


4. Ejecuta la aplicación:
    ```
   run ´DesafioApplication.java´

    ```

5. Visualiza los datos desde terminal:


## Uso

Para buscar un libro por nombre:
1. Buscar un libro.
2. Mostrar libros en "biblioteca".
3. Mostrar libros por idioma.
4. Mostrar todos los autores.
5. Mostrar si el autor estaba vivo en cierto año.
6. Mostrar cantidad de libros por idioma
7. Buscar por Autor.
8. Estadisticas
9. Top 10


### Ejemplo de uso

## Características

- Buscar libros por nombre utilizando una API externa.
- Mostrar libros en la biblioteca local.
- Filtrar libros por idioma.
- Listar todos los autores almacenados.
- Verificar si un autor estaba vivo en un cierto año.
- Contar libros por idioma.

## Contribución

¡Las contribuciones son bienvenidas! Para contribuir, sigue estos pasos:

1. Haz un fork del repositorio.
2. Crea una nueva rama (`git checkout -b feature-nueva-caracteristica`).
3. Realiza tus cambios y haz commit (`git commit -m 'Agrega nueva característica'`).
4. Empuja tu rama (`git push origin feature-nueva-caracteristica`).
5. Abre un Pull Request.

Asegúrate de que tu código siga las convenciones del proyecto y esté bien documentado.

## Contacto

Desarrollado por [Cristian Gutierrez]https://github.com/Pyrooos/Challenge-literatura). Si tienes alguna pregunta o sugerencia, 
no dudes en ponerte en contacto.
