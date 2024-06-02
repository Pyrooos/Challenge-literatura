package com.alura.literatura.principal;

import com.alura.literatura.DTO.DatosAutor;
import com.alura.literatura.model.Autor;
import com.alura.literatura.model.Datos;
import com.alura.literatura.DTO.DatosLibros;
import com.alura.literatura.model.Libro;
import com.alura.literatura.service.AutorService;
import com.alura.literatura.service.ConsumoAPI;
import com.alura.literatura.service.ConvierteDatos;
import com.alura.literatura.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class Principal {

    @Autowired
    private LibroService libroService;
    @Autowired
    private AutorService autorService;


    private static final String URL_BASE = "https://gutendex.com/books/";
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConvierteDatos conversor = new ConvierteDatos();
    private Scanner teclado = new Scanner(System.in);

    public void muestraElMenu() {
        int opcion = -1;
        do {
            var menu = """
                                            
                        1.- Buscar un libro.
                        2.- Mostrar libros en "biblioteca".
                        3.- Mostrar libros por idioma.
                        4.- Mostrar todos los autores.
                        5.- Mostrar si el autor estaba vivo en cierto año.
                        6.- Mostrar cantidad de libros por idioma
                        7.- Buscar por Autor.
                        8.- Estadisticas
                        9.- Top 10
                        0.- Salir.
                                        
                    """;
            try {
                System.out.println(menu);
                opcion = teclado.nextInt();
                teclado.nextLine();

                switch (opcion) {
                    case 1:
                        buscarLibrosPorNombre();
                        break;
                    case 2:
                        obtenerTodosLosLibros(libroService);
                        break;
                    case 3:
                        System.out.println("Ingrese las primeras 2 letras del lenguaje que quiere filtrar:");
                        String idioma = teclado.nextLine();
                        mostrarLibrosPorIdioma(idioma);
                        break;
                    case 4:
                        listarTodosLosAutores();
                        break;
                    case 5:
                        listarAutoresVivosEnAnio();
                        break;
                    case 6:
                        contarLibrosPorLenguaje();
                        break;
                    case 7:
                        procesarAutor();
                        break;
                    case 8:
                        estadisticas();
                        break;
                    case 9:
                        Top10();
                        break;
                    case 0:
                        System.out.println("Saliendo");
                        System.exit(0);
                    default:
                        System.out.println("Opción no válida. Por favor, seleccione nuevamente.");
                }

            } catch (InputMismatchException e) {
                System.out.println("\nOpción inválida\n");
                teclado.next();
            }
        } while (opcion != 0);
        System.out.println("Saliendo");
    }

    private void buscarLibrosPorNombre() {
        System.out.println("Ingrese el nombre del libro que desea buscar");
        var tituloLibro = teclado.nextLine();

        var json = consumoAPI.obtenerDatos(URL_BASE + "?search=" + tituloLibro.replace(" ", "+"));
        if (json == null || json.isEmpty()) {
            System.out.println("No se recibieron datos de la API");
            return;
        }
        var datosBusqueda = conversor.obtenerDatos(json, Datos.class);
        if (datosBusqueda == null || datosBusqueda.resultados().isEmpty()) {
            System.out.println("No se encontraron resultados en los datos de búsqueda");
            return;
        }

        Optional<DatosLibros> libroBuscado = datosBusqueda.resultados().stream()
                .filter(l -> l.titulo().toUpperCase().contains(tituloLibro.toUpperCase()))
                .findFirst();
        if (libroBuscado.isPresent()) {
            DatosLibros datosLibro = libroBuscado.get();

            if (datosLibro.autor().isEmpty()) {
                System.out.println("No se encontraron autores para el libro especificado");
                return;
            }
            DatosAutor datosAutor = datosLibro.autor().get(0);
            int anioNacimiento = 0;
            int anioFallecimiento = 0;
            try {
                anioNacimiento = datosAutor.fechaDeNacimiento() != null ? Integer.parseInt(datosAutor.fechaDeNacimiento()) : 0;
                anioFallecimiento = datosAutor.fechaDeFallecimiento() != null ? Integer.parseInt(datosAutor.fechaDeFallecimiento()) : 0;
            } catch (NumberFormatException e) {
                System.out.println("Error al convertir las fechas de nacimiento o fallecimiento del autor: " + e.getMessage());
            }

            Autor autor = new Autor(datosAutor.nombre(), anioNacimiento, anioFallecimiento);

            String titulo = datosLibro.titulo();
            if (titulo.length() > 255) {
                titulo = titulo.substring(0, 255);
            }

            Libro libro = new Libro(titulo, datosLibro.idiomas().get(0), autor, datosLibro.numeroDeDescargas().toString());
            libroService.guardarLibro(libro);

            System.out.println(libro);
        } else {
            System.out.println("Libro no encontrado");
        }
    }


    public void procesarAutor() {

        System.out.println("Ingrese el nombre del autor que desea buscar");
        var nombreAutor = teclado.nextLine();
        var json = consumoAPI.obtenerDatos(URL_BASE + "?search=" + nombreAutor.replace(" ", "+"));
        if (json == null || json.isEmpty()) {
            System.out.println("No se recibieron datos de la API");
            return;
        }
        var datosBusqueda = conversor.obtenerDatos(json, Datos.class);
        if (datosBusqueda == null || datosBusqueda.resultados().isEmpty()) {
            System.out.println("No se encontraron resultados en los datos de búsqueda");
            return;
        }
        Optional<DatosAutor> autorBuscado = datosBusqueda.resultados().stream()
                .flatMap(l -> l.autor().stream())
                .filter(a -> a.nombre().toUpperCase().contains(nombreAutor.toUpperCase()))
                .findFirst();

        if (autorBuscado.isPresent()) {
            DatosAutor datosAutor = autorBuscado.get();
            int anioNacimiento = 0;
            int anioFallecimiento = 0;
            try {
                anioNacimiento = datosAutor.fechaDeNacimiento() != null ? Integer.parseInt(datosAutor.fechaDeNacimiento()) : 0;
                anioFallecimiento = datosAutor.fechaDeFallecimiento() != null ? Integer.parseInt(datosAutor.fechaDeFallecimiento()) : 0;
            } catch (NumberFormatException e) {
                System.out.println("Error al convertir las fechas de nacimiento o fallecimiento del autor: " + e.getMessage());
            }

            Autor autor = new Autor(datosAutor.nombre(), anioNacimiento, anioFallecimiento);
            String message = autorService.guardarAutor(autor);
            System.out.println(message);
        } else {
            System.out.println("Autor no encontrado");
        }
    }

    public static void obtenerTodosLosLibros(LibroService libroService) {

        List<Libro> libros = libroService.obtenerTodosLosLibros();
        System.out.println("Libros disponibles:");
        for (Libro libro : libros) {
            System.out.println(libro);
        }
    }

    public void mostrarLibrosPorIdioma(String idioma) {

        List<Libro> librosPorIdioma = libroService.obtenerLibrosPorIdioma(idioma);
        if (librosPorIdioma.isEmpty()) {
            System.out.println("No hay libros en este idioma.");
        } else {
            System.out.println("Listado de libros en el idioma '" + idioma + "´:");
            for (Libro libro : librosPorIdioma) {
                System.out.println(libro);
            }
        }
    }

    private void listarTodosLosAutores() {

        List<Autor> autores = autorService.obtenerTodosLosAutores();
        if (autores.isEmpty()) {
            System.out.println("No hay autores.");
        } else {
            System.out.println("Listado de autores:");
            for (Autor autor : autores) {
                System.out.println(autor);
            }
        }
    }

    private void listarAutoresVivosEnAnio() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el año:");
        try {
            int anio = Integer.parseInt(scanner.nextLine());

            if (anio < 0) {
                System.out.println("Advertencia: El año ingresado es negativo.");
                return;
            }

            List<Autor> autores = autorService.obtenerAutoresVivosEnAnio(anio);
            if (autores.isEmpty()) {
                System.out.println("No hay autores vivos en el año " + anio);
            } else {
                System.out.println("Autores vivos en el año " + anio + ":");
                for (Autor autor : autores) {
                    System.out.println(autor);
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Por favor, ingresa un año válido.");
        }
    }


    private void contarLibrosPorLenguaje() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingresa el idioma (por ejemplo, 'es' para español, 'en' para inglés): ");
        String lenguaje = scanner.nextLine();
        long count = libroService.contarLibrosPorLenguaje(lenguaje);
        System.out.println("Cantidad de libros en el idioma " + lenguaje + ": " + count);
    }
    private void estadisticas(){

        var json = consumoAPI.obtenerDatos(URL_BASE);
        var data=conversor.obtenerDatos(json, Datos.class);
        DoubleSummaryStatistics est = data.resultados().stream()
                .filter(d -> d.numeroDeDescargas() >0 )
                .collect(Collectors.summarizingDouble(DatosLibros::numeroDeDescargas));
        System.out.println("Cantidad media de descargas: " + est.getAverage());
        System.out.println("Cantidad máxima de descargas: "+ est.getMax());
        System.out.println("Cantidad mínima de descargas: " + est.getMin());
        System.out.println(" Cantidad de registros evaluados para calcular las estadisticas: " + est.getCount());
    }
    private void Top10(){

        var json = consumoAPI.obtenerDatos(URL_BASE);
        var datos = conversor.obtenerDatos(json, Datos.class);
        System.out.println("Top 10 libros más descargados");
        datos.resultados().stream()
                .sorted(Comparator.comparing(DatosLibros::numeroDeDescargas).reversed())
                .limit(10)
                .map(l -> l.titulo().toUpperCase())
                .forEach(System.out::println);
    }
}
