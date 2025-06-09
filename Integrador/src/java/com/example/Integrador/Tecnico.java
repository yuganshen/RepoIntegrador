package com.example.Integrador;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Tecnico implements UsuarioConCredenciales{
	
	@Id
    private String id;
	
    private String nombre;
    private String apellido;
    private String especialidad;
    private String telefono;
    private String email;
    private String usuario;
    private String contraseña;

    // Constructor
    public Tecnico() {
    	
    }
    public Tecnico(String usuario, String contraseña) {
    	this.usuario=usuario;
    	this.contraseña=contraseña;
    }
    
    public Tecnico(String id, String nombre, String apellido, String especialidad, String telefono, String email) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.especialidad = especialidad;
        this.telefono = telefono;
        this.email = email;
    }

    // Getters y Setters
    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getEmail() {
        return email;
    }
    
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getContraseña() {
		return contraseña;
	}
	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}
    @Override
    public String redirigir() {
        return "menu-trabjador"; // Nombre de la vista o ruta
    }

}