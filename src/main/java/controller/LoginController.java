package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Optional;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import model.dao.UsuarioDAO;
import model.entities.Usuario;
import utils.HashUtils;
import view.DashboardView;
import view.LoginView;

/**
 * Controlador encargado de la lógica del inicio de sesión.
 */
public class LoginController {

    private final LoginView vista;
    private final UsuarioDAO usuarioDAO;

    /**
     * Construye el controlador utilizando las dependencias necesarias.
     *
     * @param vista interfaz gráfica asociada.
     */
    public LoginController(LoginView vista) {
        this(vista, new UsuarioDAO());
    }

    /**
     * Constructor de uso interno que permite inyectar el DAO (útil para pruebas).
     *
     * @param vista interfaz de usuario.
     * @param usuarioDAO acceso a datos de usuarios.
     */
    public LoginController(LoginView vista, UsuarioDAO usuarioDAO) {
        this.vista = vista;
        this.usuarioDAO = usuarioDAO;
        configurarEventos();
    }

    /**
     * Muestra la vista de inicio de sesión.
     */
    public void iniciar() {
        SwingUtilities.invokeLater(() -> vista.setVisible(true));
    }

    private void configurarEventos() {
        vista.agregarListenerLogin(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                procesarInicioSesion();
            }
        });
    }

    private void procesarInicioSesion() {
        vista.limpiarError();
        String usuarioIngresado = vista.obtenerUsuario();
        String contrasenaIngresada = vista.obtenerContrasena();

        if (usuarioIngresado.isEmpty() || contrasenaIngresada.isEmpty()) {
            vista.mostrarError("Debe ingresar usuario y contraseña.");
            return;
        }

        try {
            Optional<Usuario> usuarioOpt = usuarioDAO.buscarPorUsername(usuarioIngresado);
            if (!usuarioOpt.isPresent()) {
                vista.mostrarError("Credenciales inválidas, verifique e intente nuevamente.");
                return;
            }

            Usuario usuario = usuarioOpt.get();
            if (!"ACTIVO".equalsIgnoreCase(usuario.getEstado())) {
                vista.mostrarError("El usuario no se encuentra activo. Contacte al administrador.");
                return;
            }

            boolean contrasenaValida = HashUtils.verificar(contrasenaIngresada, usuario.getPasswordHash());
            if (!contrasenaValida) {
                vista.mostrarError("Credenciales inválidas, verifique e intente nuevamente.");
                return;
            }

            registrarUltimaConexion(usuario);
            abrirDashboard(usuario);
        } catch (SQLException ex) {
            vista.mostrarError("Ocurrió un error al validar las credenciales.");
            System.err.println("Error al iniciar sesión: " + ex.getMessage());
        }
    }

    private void registrarUltimaConexion(Usuario usuario) {
        try {
            LocalDateTime ahora = LocalDateTime.now();
            usuarioDAO.actualizarUltimaConexion(usuario.getIdUsuario(), ahora);
            usuario.setUltimaConexion(ahora);
        } catch (SQLException ex) {
            System.err.println("No fue posible registrar la última conexión: " + ex.getMessage());
        }
    }

    private void abrirDashboard(Usuario usuario) {
        vista.mostrarMensajeInformativo("Bienvenido " + usuario.getNombre() + "");
        vista.dispose();

        DashboardView dashboard = new DashboardView();
        if (dashboard instanceof JFrame) {
            ((JFrame) dashboard).setVisible(true);
        } else {
            System.out.println("DashboardView aún no está implementado como JFrame.");
        }
    }
}
