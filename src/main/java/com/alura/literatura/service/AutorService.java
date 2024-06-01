package com.alura.literatura.service;

import com.alura.literatura.model.Autor;
import com.alura.literatura.DTO.DatosAutor;
import com.alura.literatura.repository.AutorRepository;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AutorService {

    @Autowired
    private AutorRepository autorRepository;


    @Transactional
    public List<Autor> obtenerTodosLosAutores() {
        List<Autor> autores = autorRepository.findAll();
        return autores;
    }
    public Autor convertirYGuardarAutor(DatosAutor datosAutor) {
        String nombre = datosAutor.nombre();
        int fechaDeNacimiento = 0;
        int fechaDeFallecimiento = 0;
        try {
            fechaDeNacimiento = datosAutor.fechaDeNacimiento() != null ? Integer.parseInt(datosAutor.fechaDeNacimiento()) : 0;
            fechaDeFallecimiento = datosAutor.fechaDeFallecimiento() != null ? Integer.parseInt(datosAutor.fechaDeFallecimiento()) : 0;

        } catch (NumberFormatException e) {
            System.out.println("Error al convertir las fechas de nacimiento o fallecimiento del autor: " + e.getMessage());
        }

        Autor autor = new Autor(nombre, fechaDeNacimiento, fechaDeFallecimiento);
        return autorRepository.save(autor);
    }

    public List<Autor> obtenerAutoresVivosEnAnio(int anio) {
        return autorRepository.obtenerAutoresVivosEnAnio(anio);
    }

}
