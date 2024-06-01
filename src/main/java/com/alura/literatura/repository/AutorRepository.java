package com.alura.literatura.repository;

import com.alura.literatura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    Autor findByNombre(String nombre);
    @Query("SELECT a FROM Autor a WHERE a.fechaDeNacimiento <= :anio AND (:anio < a.fechaDeFallecimiento OR a.fechaDeFallecimiento = 0)")
    List<Autor> obtenerAutoresVivosEnAnio(@Param("anio") int anio);



}