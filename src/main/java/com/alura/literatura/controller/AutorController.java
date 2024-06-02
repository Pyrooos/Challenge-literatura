package com.alura.literatura.controller;

import com.alura.literatura.model.Autor;;
import com.alura.literatura.service.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class AutorController {
    @Autowired
    private AutorService autorService;
    @PostMapping
    public ResponseEntity<String> addAutor(@RequestBody Autor autor) {
        String message = autorService.guardarAutor(autor);
        if ("El autor ya está guardado en la base de datos.".equals(message)) {
            return ResponseEntity.status(409).body(message);
        }
        return ResponseEntity.ok(message);
    }
}
