package com.alura.literatura.services;

public interface IConvertirDatos {
        <T> T obtenerDatos(String json, Class <T> clase);
}
