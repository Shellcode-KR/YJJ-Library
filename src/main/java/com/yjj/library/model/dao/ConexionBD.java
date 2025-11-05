package com.yjj.library.model.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Properties;

/**
 * Clase responsable de gestionar la conexión con la base de datos MySQL.
 * Lee los datos de configuración desde el archivo {@code config.properties}
 * ubicado en el classpath del proyecto.
 */
public final class ConexionBD {

    /** Bloqueo para garantizar acceso seguro cuando se construye la conexión. */
    private static final Object BLOQUEO = new Object();
    /** Ruta del archivo de propiedades dentro del classpath. */
    private static final String ARCHIVO_PROPIEDADES = "config.properties";

    /** Conexión compartida reutilizable. */
    private static Connection conexionCompartida;
    /** Propiedades cargadas desde el archivo de configuración. */
    private static Properties propiedades;

    /** Constructor privado para evitar instanciación. */
    private ConexionBD() {
        // Evitar instanciación
    }

    /**
     * Obtiene una conexión activa hacia la base de datos.
     *
     * @return conexión JDBC lista para usarse.
     * @throws SQLException si ocurre algún problema al conectarse.
     */
    public static Connection obtenerConexion() throws SQLException {
        if (conexionCompartida == null || conexionCompartida.isClosed()) {
            synchronized (BLOQUEO) {
                if (conexionCompartida == null || conexionCompartida.isClosed()) {
                    cargarPropiedades();
                    final String url = propiedades.getProperty("db.url");
                    final String usuario = propiedades.getProperty("db.username");
                    final String contrasena = propiedades.getProperty("db.password");

                    try {
                        Class.forName(propiedades.getProperty("db.driver", "com.mysql.cj.jdbc.Driver"));
                        conexionCompartida = DriverManager.getConnection(url, usuario, contrasena);
                    } catch (ClassNotFoundException ex) {
                        throw new SQLException("No se encontró el driver de MySQL", ex);
                    }
                }
            }
        }
        return conexionCompartida;
    }

    /**
     * Cierra la conexión compartida si se encuentra abierta.
     */
    public static void cerrarConexion() {
        synchronized (BLOQUEO) {
            if (conexionCompartida != null) {
                try {
                    if (!conexionCompartida.isClosed()) {
                        conexionCompartida.close();
                    }
                } catch (SQLException ex) {
                    System.err.println("Error al cerrar la conexión: " + ex.getMessage());
                }
            }
        }
    }

    /**
     * Carga la configuración de conexión desde el archivo de propiedades.
     */
    private static void cargarPropiedades() {
        if (propiedades != null) {
            return;
        }
        try (InputStream entrada = ConexionBD.class.getClassLoader().getResourceAsStream(ARCHIVO_PROPIEDADES)) {
            if (Objects.isNull(entrada)) {
                throw new IllegalStateException("No se encontró el archivo " + ARCHIVO_PROPIEDADES + " en el classpath");
            }
            Properties props = new Properties();
            props.load(entrada);
            propiedades = props;
        } catch (IOException ex) {
            throw new IllegalStateException("No fue posible cargar la configuración de la base de datos", ex);
        }
    }
}
