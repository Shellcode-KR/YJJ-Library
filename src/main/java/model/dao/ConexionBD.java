package model.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.InputStream;

public class ConexionBD {
    private static Connection conexion;

    private static final String PROPERTIES_FILE = "/config.properties";

    public static Connection getConexion() throws SQLException {
        if (conexion == null || conexion.isClosed()) {
            try (InputStream input = ConexionBD.class.getResourceAsStream(PROPERTIES_FILE)) {
                Properties props = new Properties();
                props.load(input);

                String url = props.getProperty("db.url");
                String usuario = props.getProperty("db.username");
                String password = props.getProperty("db.password");

                Class.forName("com.mysql.cj.jdbc.Driver");
                conexion = DriverManager.getConnection(url, usuario, password);
                System.out.println("Conexión establecida con MySQL");
            } catch (Exception ex) {
                System.err.println("Error en conexión: " + ex.getMessage());
                throw new SQLException("No se pudo conectar a la base de datos", ex);
            }
        }
        return conexion;
    }

    public static void cerrarConexion() {
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
                System.out.println("Conexión cerrada correctamente");
            }
        } catch (SQLException e) {
            System.err.println("⚠️ Error al cerrar conexión: " + e.getMessage());
        }
    }
}
