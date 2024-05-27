package com.alura.literatura.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public class Autor {
    @JsonAlias("id") private String id;
    @JsonAlias("name") private String nombre;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    @Override
    public String toString() {
        return "Autor{id='" + id + "', name='" + nombre + "'}";
    }
}

