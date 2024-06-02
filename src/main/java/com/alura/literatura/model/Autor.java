package com.alura.literatura.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "autores")
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private int fechaDeNacimiento;
    private int fechaDeFallecimiento;
    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Libro> libros;

    public Autor() {
    }

    public Autor(String nombre, Integer fechaDeNacimiento, Integer fechaDeFallecimiento) {
        this.nombre = nombre;
        this.fechaDeNacimiento = Integer.parseInt(String.valueOf(fechaDeNacimiento));
        this.fechaDeFallecimiento = Integer.parseInt(String.valueOf(fechaDeFallecimiento));
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public int getfechaDeFallecimiento() {
        return fechaDeFallecimiento;
    }

    public void setfechaDeFallecimiento(int fechaDeFallecimiento) {
        this.fechaDeFallecimiento = fechaDeFallecimiento;
    }

    public int getfechaDeNacimiento() {return fechaDeNacimiento;}

    public void setfechaDeNacimiento(int fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }

    @Override
    public String toString() {
        return
                " \n" +"Id=" + id +
                ", \n" +"Nombre='" + nombre + '\'' +
                ", \n" +"Fecha de nacimiento=" + fechaDeNacimiento +
                ", \n" +"Fecha de fallecimiento=" + fechaDeFallecimiento+" \n"+ " \n";
    }
}
