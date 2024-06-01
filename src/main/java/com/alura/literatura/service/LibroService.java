package com.alura.literatura.service;

import com.alura.literatura.model.Autor;
import com.alura.literatura.model.Libro;
import com.alura.literatura.repository.AutorRepository;
import com.alura.literatura.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LibroService {

    @Autowired
    private LibroRepository libroRepository;

    @Autowired
    private AutorRepository autorRepository;

    public String guardarLibro(Libro libro) {
        Optional<Libro> libroExistente = libroRepository.findByTitulo(libro.getTitulo());
        if (libroExistente.isPresent()) {
            System.out.println("El libro ya está guardado en la base de datos: " + libro.getTitulo());
            return "El libro ya está guardado en la base de datos.";
        }

        Autor autorExistente = autorRepository.findByNombre(libro.getAutor().getNombre());
        if (autorExistente != null) {
            libro.setAutor(autorExistente);
        } else {
            autorRepository.save(libro.getAutor());
        }

        libroRepository.save(libro);
        System.out.println("Libro guardado exitosamente: " + libro.getTitulo());
        return "Libro guardado exitosamente.";
    }

    public Optional<Libro> buscarLibroPorTitulo(String titulo) {
        return libroRepository.findByTitulo(titulo);
    }
    public List<Libro> obtenerTodosLosLibros() {
        return libroRepository.findAll();
    }
    public List<Libro> obtenerLibrosPorIdioma(String lenguaje) {
        return libroRepository.findBylenguaje(lenguaje);
    }
    public long contarLibrosPorLenguaje(String lenguaje) {
        return libroRepository.contarPorLenguaje(lenguaje);
    }
}
