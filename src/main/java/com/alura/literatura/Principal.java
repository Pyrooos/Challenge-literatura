package com.alura.literatura;

import com.alura.literatura.services.ServicioLiteratura;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Principal implements CommandLineRunner {

	private final ServicioLiteratura servicioLiteratura;

	@Autowired
	public Principal(ServicioLiteratura servicioLiteratura) {
		this.servicioLiteratura = servicioLiteratura;
	}

	public static void main(String[] args) {
		SpringApplication.run(Principal.class, args);
	}

	@Override
	public void run(String... args) {
		servicioLiteratura.mostrarMenu();
	}
}
