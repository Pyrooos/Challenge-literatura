package com.alura.literatura.model;

import jakarta.persistence.*;

@Entity
@Table(name = "libros")
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String titulo;
    private String lenguaje;
    private String numeroDeDescargas;

    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Autor autor;

    public Libro() {

    }

    public Libro(String titulo, String lenguaje, Autor autor, String numeroDeDescargas) {
        this.titulo = titulo;
        this.lenguaje = lenguaje;
        this.autor = autor;
        this.numeroDeDescargas = numeroDeDescargas;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getLenguaje() {
        return lenguaje;
    }

    public void setLenguaje(String lenguaje) {
        this.lenguaje = lenguaje;
    }

    public String getNumeroDeDescargas() {
        return numeroDeDescargas;
    }

    public void setNumeroDeDescargas(String numeroDeDescargas) {
        this.numeroDeDescargas = numeroDeDescargas;
    }

    @Override
    public String toString() {
        return
                "Id=" + id +
                ", \n" +"Titulo='" + titulo + '\'' +
                        ", \n" +"Idioma='" + lenguaje + '\'' +
                        ", \n" +"Autor=" + autor.getNombre() +
                        ", \n" +"Numero de descargas='" + numeroDeDescargas + '\''+" \n"+ " \n";
    }

}
