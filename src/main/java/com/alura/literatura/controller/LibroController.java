package com.alura.literatura.controller;

import com.alura.literatura.model.Libro;
import com.alura.literatura.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class LibroController {

    @Autowired
    private LibroService libroService;

    @PostMapping
    public ResponseEntity<String> addLibro(@RequestBody Libro libro) {
        String message = libroService.guardarLibro(libro);
        if ("El libro ya está guardado en la base de datos.".equals(message)) {
            return ResponseEntity.status(409).body(message);  // 409 Conflict
        }
        return ResponseEntity.ok(message);
    }

    @GetMapping
    public ResponseEntity<Libro> getLibro(@RequestParam String titulo) {
        Optional<Libro> libro = libroService.buscarLibroPorTitulo(titulo);
        if (libro.isPresent()) {
            return ResponseEntity.ok(libro.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
