package com.alura.literatura.service;

import com.alura.literatura.model.Autor;
import com.alura.literatura.repository.AutorRepository;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class AutorService {

    @Autowired
    private AutorRepository autorRepository;


    @Transactional
    public List<Autor> obtenerTodosLosAutores() {
        List<Autor> autores = autorRepository.findAll();
        return autores;
    }
    public String guardarAutor(Autor autor) {
        Optional<Autor> autorExistente = autorRepository.findByNombre(autor.getNombre());
        if (autorExistente.isPresent()) {

            return "El autor ya está guardado en la base de datos: " + autor.getNombre();
        }
        autorRepository.save(autor);
        System.out.println("Autor guardado exitosamente: " + autor.getNombre());
        return "Autor guardado exitosamente.";
    }

    public List<Autor> obtenerAutoresVivosEnAnio(int anio) {
        return autorRepository.obtenerAutoresVivosEnAnio(anio);
    }

}
