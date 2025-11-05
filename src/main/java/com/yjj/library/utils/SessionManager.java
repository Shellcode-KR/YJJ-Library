package com.yjj.library.utils;

import com.yjj.library.model.entities.Usuario;

/**
 * SessionManager - guarda el usuario actual en memoria durante la ejecución.
 */
public class SessionManager {
    private static Usuario usuarioActual;

    public static void iniciarSesion(Usuario u) {
        usuarioActual = u;
    }

    public static Usuario getUsuarioActual() {
        return usuarioActual;
    }

    public static boolean esAdmin() {
        return usuarioActual != null && "ADMIN".equalsIgnoreCase(usuarioActual.getRol());
    }

    public static boolean esBibliotecario() {
        return usuarioActual != null && "BIBLIOTECARIO".equalsIgnoreCase(usuarioActual.getRol());
    }

    public static void cerrarSesion() {
        usuarioActual = null;
    }
}
