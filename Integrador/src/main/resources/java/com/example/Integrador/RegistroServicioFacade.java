package com.example.Integrador;

import java.time.LocalDate;
import java.util.List;

public class RegistroServicioFacade {
	 private DTOMaquinaria maquinaria;
	    private List<Mantenimiento> mantenimientos;
	    private LocalDate fechaSalida;
	    private Tecnico tecnico;

	    // Paso 1: Registrar maquinaria
	    public void registrarMaquinaria(DTOMaquinaria maquinaria) {
	        this.maquinaria = maquinaria;
	        System.out.println("Nombre: " + maquinaria.getNombre());
	        System.out.println("Apellido: " + maquinaria.getApellido());
	        System.out.println("Celular: " + maquinaria.getNumeroCelular());
	        System.out.println("Gmail: " + maquinaria.getGmail());
	        System.out.println("ID Maquinaria: " + maquinaria.getIDMaquinaria());
	        System.out.println("Marca: " + maquinaria.getMarca());
	        System.out.println("Modelo: " + maquinaria.getModelo());
	        System.out.println("Número de Serie: " + maquinaria.getNumeroSerie());
	        System.out.println("Tipo: " + maquinaria.getTipoMaquinaria());
	        System.out.println("Año: " + maquinaria.getAnioFabricacion());
	        System.out.println("Motor: " + maquinaria.getNumeroMotor());
	        System.out.println("Horas: " + maquinaria.getHorasOperación());
	        System.out.println("Kilometraje: " + maquinaria.getKilometraje());
	    }

	    // Paso 2: Seleccionar tipos de mantenimiento
	    public void seleccionarTiposMantenimiento(List<String> tipos, SeleccionMantenimientoDTO seleccion) {
	        this.mantenimientos = MantenimientoFactory.crearMantenimientos(tipos);
	        System.out.println("Tipos seleccionados: " + seleccion.getTiposSeleccionados());
	        for (Mantenimiento m : mantenimientos) {
	            System.out.println("- " + m.getDescripcion());
	        }
	    }

	    // Paso 3: Establecer la fecha de salida
	    public void establecerFecha(LocalDate fechaSalida) {
	        this.fechaSalida = fechaSalida;
	        System.out.println("Fecha de salida registrada: " + fechaSalida);
	    }
	    
	    //Mostrar al tecnico

	    public void seleccionarTecnicoDesdeDTO(DtoTecnico dto) {
	        System.out.println("Técnico asignado :");	       
	        System.out.println("Nombre: " + dto.getNombre());
	        System.out.println("Apellido: " + dto.getApellido());
	        System.out.println("Especialidad: " + dto.getEspecialidad());
	        System.out.println("Teléfono: " + dto.getTelefono());
	        System.out.println("Email: " + dto.getEmail());
	    }
	    
	}