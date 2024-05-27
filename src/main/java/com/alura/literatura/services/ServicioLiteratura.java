package com.alura.literatura.services;

import com.alura.literatura.model.Libro;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Scanner;

@Service
public class ServicioLiteratura {

        private final ConsumoAPI consumoAPI;

        public ServicioLiteratura(ConsumoAPI consumoAPI) {
                this.consumoAPI = consumoAPI;
        }

        public void mostrarMenu() {
                Scanner scanner = new Scanner(System.in);

                while (true) {
                        System.out.println("Seleccione una opción:");
                        System.out.println("1. Consultar libros");
                        System.out.println("2. Salir");

                        int opcion = scanner.nextInt();
                        scanner.nextLine();

                        switch (opcion) {
                                case 1:
                                        consultarLibros();
                                        break;
                                case 2:
                                        System.out.println("Saliendo...");
                                        return;
                                default:
                                        System.out.println("Opción no válida, por favor intente de nuevo.");
                        }
                }
        }

        private void consultarLibros() {
                String json = consumoAPI.obtenerDatos("http://gutendex.com/books");

                ObjectMapper objectMapper = new ObjectMapper();
                try {
                        Libro[] libros = objectMapper.readValue(json, Libro[].class);
                        for (Libro libro : libros) {
                                System.out.println(libro);
                        }
                } catch (IOException e) {
                        System.out.println("Error al procesar los datos de libros: " + e.getMessage());
                }
        }
}
