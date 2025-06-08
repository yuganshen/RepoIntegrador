package com.example.demInte;

public class Tecnico implements TipoUsuario{
    private String id;
    private String nombre;
    private String apellido;
    private String especialidad;
    private String telefono;
    private String email;

    // Constructor
    public Tecnico() {
    	
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
    @Override
    public String redirigir() {
        return "menu-trabjador"; // Nombre de la vista o ruta
    }

}

    


