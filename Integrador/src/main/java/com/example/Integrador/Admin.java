package com.example.Integrador;

public class Admin implements TipoUsuario {
    @Override
    public String redirigir() {
        return "Inicio"; // Nombre de la vista o ruta
    }
}
