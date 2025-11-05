package com.yjj.library.controller;

import com.yjj.library.model.dao.UsuarioDAO;
import com.yjj.library.model.entities.Usuario;
import com.yjj.library.utils.HashUtils;
import com.yjj.library.utils.SessionManager;
import com.yjj.library.view.DashboardView;
import com.yjj.library.view.LoginView;
import java.util.Optional;

import javax.swing.JOptionPane;

/**
 * LoginController - maneja el evento de login entre la view y el dao.
 */
public class LoginController {
    private LoginView loginView;
    private UsuarioDAO usuarioDAO;

    public LoginController(LoginView loginView, UsuarioDAO usuarioDAO) {
        this.loginView = loginView;
        this.usuarioDAO = usuarioDAO;
    }

    public void verificarCredenciales(String username, String passwordPlain) {
        try {
            Optional<Usuario> usuario = usuarioDAO.buscarPorUsername(username);
            if (usuario == null) {
                JOptionPane.showMessageDialog(loginView, "Usuario no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            boolean ok = HashUtils.verificar(passwordPlain, usuario.getPasswordHash());
            if (ok) {
                SessionManager.iniciarSesion(usuario);
                JOptionPane.showMessageDialog(loginView, "Bienvenido " + usuario.getNombre());
                // Abrir dashboard
                DashboardView dash = new DashboardView();
                dash.setVisible(true);
                loginView.dispose();
            } else {
                JOptionPane.showMessageDialog(loginView, "Contraseña incorrecta", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(loginView, "Error al verificar credenciales: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
}
