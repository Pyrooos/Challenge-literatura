package com.alura.literatura.services;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.springframework.stereotype.Service;

@Service
public class ConsumoAPI {
    public String obtenerDatos(String url) {
        HttpClient cliente = HttpClient.newHttpClient();
        HttpRequest solicitud = HttpRequest.newBuilder()
                .uri(URI.create("http://gutendex.com/booksR"))
                .build();
        HttpResponse<String> respuesta = null;
        try {
            respuesta = cliente.send(solicitud, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        return respuesta.body();
    }
}
