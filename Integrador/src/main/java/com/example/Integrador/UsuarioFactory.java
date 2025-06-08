package com.example.demInte;

public class UsuarioFactory {

    public static TipoUsuario crearUsuario(String tipo) {
        if (tipo == null) return null;

        switch (tipo.toLowerCase()) {
            case "admin":
                return new Admin();
            case "tecnico":
                return new Tecnico();
            default:
                return null;
        }
    }
}
