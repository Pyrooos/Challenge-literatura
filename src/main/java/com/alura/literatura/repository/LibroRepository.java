package com.alura.literatura.repository;

import com.alura.literatura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Long> {
    Optional<Libro> findByTitulo(String titulo);

    List<Libro> findBylenguaje(String lenguaje);
    @Query("SELECT COUNT(l) FROM Libro l WHERE l.lenguaje = :lenguaje")
    long contarPorLenguaje(@Param("lenguaje") String lenguaje);

}