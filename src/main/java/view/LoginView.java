package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

/**
 * Ventana de inicio de sesión inspirada en el diseño proporcionado en Figma.
 */
public class LoginView extends JFrame {

    private final JTextField campoUsuario;
    private final JPasswordField campoContrasena;
    private final JButton botonIngresar;
    private final JLabel etiquetaError;

    /**
     * Construye la ventana de inicio de sesión con estilos modernos.
     */
    public LoginView() {
        configurarLookAndFeel();
        setTitle("Sistema de Biblioteca - Inicio de sesión");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(920, 520);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BorderLayout());

        JPanel panelLateral = construirPanelLateral();
        JPanel panelCentral = construirPanelCentral();

        add(panelLateral, BorderLayout.WEST);
        add(panelCentral, BorderLayout.CENTER);

        campoUsuario = new JTextField();
        campoUsuario.setFont(new Font("Roboto", Font.PLAIN, 16));
        campoUsuario.setPreferredSize(new Dimension(280, 40));
        campoUsuario.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(210, 220, 230), 1, true),
                new EmptyBorder(10, 14, 10, 14)));

        campoContrasena = new JPasswordField();
        campoContrasena.setFont(new Font("Roboto", Font.PLAIN, 16));
        campoContrasena.setPreferredSize(new Dimension(280, 40));
        campoContrasena.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(210, 220, 230), 1, true),
                new EmptyBorder(10, 14, 10, 14)));

        botonIngresar = new JButton("Iniciar sesión");
        botonIngresar.setFont(new Font("Roboto", Font.BOLD, 16));
        botonIngresar.setForeground(Color.WHITE);
        botonIngresar.setBackground(new Color(0, 112, 192));
        botonIngresar.setFocusPainted(false);
        botonIngresar.setBorder(BorderFactory.createEmptyBorder(12, 20, 12, 20));

        etiquetaError = new JLabel(" ", SwingConstants.CENTER);
        etiquetaError.setForeground(new Color(200, 0, 0));
        etiquetaError.setFont(new Font("Roboto", Font.PLAIN, 13));

        armarFormulario(panelCentral);
    }

    /**
     * Aplica el look and feel del sistema para mantener coherencia visual.
     */
    private void configurarLookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            System.err.println("No se pudo aplicar el look and feel del sistema: " + ex.getMessage());
        }
    }

    private JPanel construirPanelLateral() {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(300, 520));
        panel.setBackground(new Color(0, 112, 192));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(new EmptyBorder(40, 30, 40, 30));

        JLabel titulo = new JLabel("Biblioteca Universitaria", SwingConstants.LEFT);
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Roboto", Font.BOLD, 26));

        JLabel subtitulo = new JLabel("Gestión integral de recursos", SwingConstants.LEFT);
        subtitulo.setForeground(new Color(230, 245, 255));
        subtitulo.setFont(new Font("Roboto", Font.PLAIN, 16));

        panel.add(titulo);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(subtitulo);
        panel.add(Box.createVerticalGlue());

        JLabel pie = new JLabel("© Universidad 2024", SwingConstants.LEFT);
        pie.setForeground(new Color(200, 220, 240));
        pie.setFont(new Font("Roboto", Font.PLAIN, 12));
        panel.add(pie);

        return panel;
    }

    private JPanel construirPanelCentral() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(new EmptyBorder(40, 60, 40, 60));
        return panel;
    }

    private void armarFormulario(JPanel panelCentral) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;

        JLabel titulo = new JLabel("Bienvenido nuevamente", SwingConstants.CENTER);
        titulo.setFont(new Font("Roboto", Font.BOLD, 24));
        titulo.setForeground(new Color(40, 60, 80));
        panelCentral.add(titulo, gbc);

        gbc.gridy++;
        JLabel descripcion = new JLabel("Ingresa tus credenciales para continuar", SwingConstants.CENTER);
        descripcion.setFont(new Font("Roboto", Font.PLAIN, 15));
        descripcion.setForeground(new Color(90, 110, 130));
        panelCentral.add(descripcion, gbc);

        gbc.gridy++;
        panelCentral.add(Box.createVerticalStrut(20), gbc);

        gbc.gridy++;
        JLabel etiquetaUsuario = new JLabel("Usuario");
        etiquetaUsuario.setFont(new Font("Roboto", Font.PLAIN, 14));
        etiquetaUsuario.setForeground(new Color(60, 80, 100));
        panelCentral.add(etiquetaUsuario, gbc);

        gbc.gridy++;
        panelCentral.add(campoUsuario, gbc);

        gbc.gridy++;
        JLabel etiquetaContrasena = new JLabel("Contraseña");
        etiquetaContrasena.setFont(new Font("Roboto", Font.PLAIN, 14));
        etiquetaContrasena.setForeground(new Color(60, 80, 100));
        panelCentral.add(etiquetaContrasena, gbc);

        gbc.gridy++;
        panelCentral.add(campoContrasena, gbc);

        gbc.gridy++;
        panelCentral.add(etiquetaError, gbc);

        gbc.gridy++;
        panelCentral.add(botonIngresar, gbc);
    }

    /**
     * @return texto ingresado en el campo de usuario sin espacios sobrantes.
     */
    public String obtenerUsuario() {
        return campoUsuario.getText().trim();
    }

    /**
     * @return contraseña en texto plano que será hasheada en la capa correspondiente.
     */
    public String obtenerContrasena() {
        return new String(campoContrasena.getPassword());
    }

    /**
     * Permite asociar un listener externo al botón de inicio de sesión.
     *
     * @param listener acción que se ejecutará al presionar el botón.
     */
    public void agregarListenerLogin(ActionListener listener) {
        botonIngresar.addActionListener(listener);
    }

    /**
     * Muestra un mensaje de error sobre el formulario.
     *
     * @param mensaje texto a desplegar.
     */
    public void mostrarError(String mensaje) {
        etiquetaError.setText(mensaje);
    }

    /**
     * Limpia el mensaje de error actual.
     */
    public void limpiarError() {
        etiquetaError.setText(" ");
    }

    /**
     * Muestra un cuadro de diálogo informativo.
     *
     * @param mensaje contenido del cuadro de diálogo.
     */
    public void mostrarMensajeInformativo(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Información", JOptionPane.INFORMATION_MESSAGE);
    }
}
