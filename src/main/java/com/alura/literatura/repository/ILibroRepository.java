package com.alura.literatura.repository;


import com.alura.literatura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ILibroRepository extends JpaRepository<Libro, Long> {
    Libro findByTitulo(String titulo);

}
