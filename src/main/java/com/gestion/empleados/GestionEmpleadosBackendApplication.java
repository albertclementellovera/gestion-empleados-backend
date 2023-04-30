package com.gestion.empleados;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;


@SpringBootApplication
@Component
public class GestionEmpleadosBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestionEmpleadosBackendApplication.class, args);
	}

}
