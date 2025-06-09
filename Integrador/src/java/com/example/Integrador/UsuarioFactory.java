package com.example.Integrador;

public class UsuarioFactory {

    public static TipoUsuario crearUsuario(String tipo, String usuario, String contraseña) {
        if (tipo == null) return null;

        switch (tipo.toLowerCase()) {
            case "admin":
                return new Admin(usuario, contraseña);
            case "tecnico":
                return new Tecnico(usuario, contraseña);
            default:
                return null;
        }
    }
}