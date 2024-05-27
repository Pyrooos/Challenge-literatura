package com.alura.literatura.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public class Libro {
    @JsonAlias("id") private String id;
    @JsonAlias("title") private String nombreDelLibro;
    @JsonAlias("authors") private String autor;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombreDelLibro() {
        return nombreDelLibro;
    }

    public void setNombreDelLibro(String nombreDelLibro) {
        this.nombreDelLibro = nombreDelLibro;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }
    @Override
    public String toString() {
        return "Book{id='" + id + "', Nombre del libro='" + nombreDelLibro + "', Autor=" + autor + "}";
    }
}
