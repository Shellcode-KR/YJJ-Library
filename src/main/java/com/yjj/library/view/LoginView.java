package com.yjj.library.view;

import com.yjj.library.controller.LoginController;

import javax.swing.*;
import java.awt.*;

/**
 * LoginView - pequeña ventana de login que invoca al LoginController.
 */
public class LoginView extends JFrame {
    /*
    private JTextField txtUsuario;
    private JPasswordField txtContrasena;
    private JButton btnIngresar;
    private LoginController controller;

    public LoginView(LoginController controller) {
        this.controller = controller;
        inicializar();
    }

    private void inicializar() {
        setTitle("Biblioteca - Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(420, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Título
        JLabel titulo = new JLabel("Bienvenido a la Biblioteca", SwingConstants.CENTER);
        titulo.setFont(new Font("Dialog", Font.BOLD, 18));
        titulo.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        add(titulo, BorderLayout.NORTH);

        // Panel de formulario
        JPanel form = new JPanel(new GridBagLayout());
        form.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8,8,8,8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0;
        form.add(new JLabel("Usuario:"), gbc);

        gbc.gridx = 1; gbc.gridy = 0;
        txtUsuario = new JTextField();
        form.add(txtUsuario, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        form.add(new JLabel("Contraseña:"), gbc);

        gbc.gridx = 1; gbc.gridy = 1;
        txtContrasena = new JPasswordField();
        form.add(txtContrasena, gbc);

        gbc.gridwidth = 2;
        gbc.gridx = 0; gbc.gridy = 2;
        btnIngresar = new JButton("Ingresar");
        btnIngresar.setBackground(new Color(52,152,219));
        btnIngresar.setForeground(Color.WHITE);
        btnIngresar.setFocusPainted(false);
        form.add(btnIngresar, gbc);

        add(form, BorderLayout.CENTER);

        // Acción
        btnIngresar.addActionListener(e -> {
            String u = txtUsuario.getText().trim();
            String p = new String(txtContrasena.getPassword());
            controller.verificarCredenciales(u, p);
        });
    }
*/
}
