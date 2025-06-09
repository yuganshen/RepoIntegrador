package com.example.Integrador;

import java.io.File;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class IntegradorApplication {
	 public static void main(String[] args) {
	        // Crear carpeta logs si no existe
	        File logDir = new File("logs");
	        if (!logDir.exists()) {
	            boolean created = logDir.mkdirs();
	            if(created) {
	                System.out.println("Carpeta 'logs' creada correctamente.");
	            } else {
	                System.err.println("No se pudo crear la carpeta 'logs'.");
	            }
	        }

	        SpringApplication.run(IntegradorApplication.class, args);
	    }



}
