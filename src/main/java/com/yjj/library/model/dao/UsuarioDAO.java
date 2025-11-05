package com.yjj.library.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.yjj.library.model.entities.Usuario;

/**
 * DAO encargado de la gestión de la tabla de usuarios.
 */
public class UsuarioDAO {

    private static final String CAMPOS_BASE = "u.id_usuario, u.id_rol, r.nombre AS nombre_rol, u.nombre, "
            + "u.username, u.password_hash, u.correo, u.telefono, u.matricula, u.estado, "
            + "u.fecha_registro, u.ultima_conexion";

    private static final String INSERTAR_USUARIO = "INSERT INTO usuarios "
            + "(id_rol, nombre, username, password_hash, correo, telefono, matricula, estado) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String ACTUALIZAR_USUARIO = "UPDATE usuarios SET id_rol = ?, nombre = ?, username = ?, "
            + "password_hash = ?, correo = ?, telefono = ?, matricula = ?, estado = ? WHERE id_usuario = ?";

    private static final String ACTUALIZAR_ULTIMA_CONEXION = "UPDATE usuarios SET ultima_conexion = ? "
            + "WHERE id_usuario = ?";

    private static final String ELIMINAR_USUARIO = "DELETE FROM usuarios WHERE id_usuario = ?";

    private static final String BUSCAR_POR_ID = "SELECT " + CAMPOS_BASE
            + " FROM usuarios u INNER JOIN roles r ON u.id_rol = r.id_rol WHERE u.id_usuario = ?";

    private static final String BUSCAR_POR_USERNAME = "SELECT " + CAMPOS_BASE
            + " FROM usuarios u INNER JOIN roles r ON u.id_rol = r.id_rol WHERE u.username = ?";

    private static final String LISTAR_TODOS = "SELECT " + CAMPOS_BASE
            + " FROM usuarios u INNER JOIN roles r ON u.id_rol = r.id_rol ORDER BY u.fecha_registro DESC";

    /**
     * Registra un nuevo usuario en la base de datos.
     *
     * @param usuario entidad con los datos a registrar.
     * @return identificador generado para el nuevo usuario.
     * @throws SQLException si ocurre algún problema durante la operación.
     */
    public Optional<Integer> registrarUsuario(Usuario usuario) throws SQLException {
        if (usuario.getIdRol() == null) {
            throw new IllegalArgumentException("El usuario debe tener un rol asignado antes de registrarse");
        }
        try (Connection conexion = ConexionBD.obtenerConexion();
                PreparedStatement sentencia = conexion.prepareStatement(INSERTAR_USUARIO, Statement.RETURN_GENERATED_KEYS)) {

            sentencia.setInt(1, usuario.getIdRol());
            sentencia.setString(2, usuario.getNombre());
            sentencia.setString(3, usuario.getUsername());
            sentencia.setString(4, usuario.getPasswordHash());
            sentencia.setString(5, usuario.getCorreo());
            asignarNullable(sentencia, 6, usuario.getTelefono());
            asignarNullable(sentencia, 7, usuario.getMatricula());
            sentencia.setString(8, usuario.getEstado() != null ? usuario.getEstado() : "ACTIVO");

            int filas = sentencia.executeUpdate();
            if (filas == 0) {
                return Optional.empty();
            }

            try (ResultSet llaves = sentencia.getGeneratedKeys()) {
                if (llaves.next()) {
                    return Optional.of(llaves.getInt(1));
                }
            }
            return Optional.empty();
        }
    }

    /**
     * Actualiza la información de un usuario existente.
     *
     * @param usuario entidad con los datos actualizados.
     * @return {@code true} si se modificó al menos un registro.
     * @throws SQLException si ocurre algún problema durante la operación.
     */
    public boolean actualizarUsuario(Usuario usuario) throws SQLException {
        if (usuario.getIdUsuario() == null) {
            throw new IllegalArgumentException("El usuario debe tener un identificador para ser actualizado");
        }
        if (usuario.getIdRol() == null) {
            throw new IllegalArgumentException("El usuario debe tener un rol asignado para ser actualizado");
        }
        try (Connection conexion = ConexionBD.obtenerConexion();
                PreparedStatement sentencia = conexion.prepareStatement(ACTUALIZAR_USUARIO)) {

            sentencia.setInt(1, usuario.getIdRol());
            sentencia.setString(2, usuario.getNombre());
            sentencia.setString(3, usuario.getUsername());
            sentencia.setString(4, usuario.getPasswordHash());
            sentencia.setString(5, usuario.getCorreo());
            asignarNullable(sentencia, 6, usuario.getTelefono());
            asignarNullable(sentencia, 7, usuario.getMatricula());
            sentencia.setString(8, usuario.getEstado());
            sentencia.setInt(9, usuario.getIdUsuario());

            return sentencia.executeUpdate() > 0;
        }
    }

    /**
     * Registra la fecha y hora de la última conexión del usuario.
     *
     * @param idUsuario identificador del usuario.
     * @param fechaConexion fecha y hora de la conexión.
     * @throws SQLException si ocurre algún problema durante la operación.
     */
    public void actualizarUltimaConexion(int idUsuario, LocalDateTime fechaConexion) throws SQLException {
        try (Connection conexion = ConexionBD.obtenerConexion();
                PreparedStatement sentencia = conexion.prepareStatement(ACTUALIZAR_ULTIMA_CONEXION)) {
            sentencia.setTimestamp(1, Timestamp.valueOf(fechaConexion));
            sentencia.setInt(2, idUsuario);
            sentencia.executeUpdate();
        }
    }

    /**
     * Elimina de manera permanente un usuario de la base de datos.
     *
     * @param idUsuario identificador del usuario a eliminar.
     * @return {@code true} si se eliminó el registro.
     * @throws SQLException si ocurre algún problema durante la operación.
     */
    public boolean eliminarUsuario(int idUsuario) throws SQLException {
        try (Connection conexion = ConexionBD.obtenerConexion();
                PreparedStatement sentencia = conexion.prepareStatement(ELIMINAR_USUARIO)) {
            sentencia.setInt(1, idUsuario);
            return sentencia.executeUpdate() > 0;
        }
    }

    /**
     * Busca un usuario por su identificador único.
     *
     * @param idUsuario identificador del usuario.
     * @return {@link Optional} conteniendo al usuario si existe.
     * @throws SQLException si ocurre algún problema durante la consulta.
     */
    public Optional<Usuario> buscarPorId(int idUsuario) throws SQLException {
        try (Connection conexion = ConexionBD.obtenerConexion();
                PreparedStatement sentencia = conexion.prepareStatement(BUSCAR_POR_ID)) {
            sentencia.setInt(1, idUsuario);
            try (ResultSet resultado = sentencia.executeQuery()) {
                if (resultado.next()) {
                    return Optional.of(mapearUsuario(resultado));
                }
            }
        }
        return Optional.empty();
    }

    /**
     * Busca un usuario por su nombre de usuario.
     *
     * @param username nombre de usuario con el que se autentica.
     * @return {@link Optional} conteniendo al usuario si existe.
     * @throws SQLException si ocurre algún problema durante la consulta.
     */
    public Optional<Usuario> buscarPorUsername(String username) throws SQLException {
        try (Connection conexion = ConexionBD.obtenerConexion();
                PreparedStatement sentencia = conexion.prepareStatement(BUSCAR_POR_USERNAME)) {
            sentencia.setString(1, username);
            try (ResultSet resultado = sentencia.executeQuery()) {
                if (resultado.next()) {
                    return Optional.of(mapearUsuario(resultado));
                }
            }
        }
        return Optional.empty();
    }

    /**
     * Obtiene todos los usuarios registrados en el sistema.
     *
     * @return lista de usuarios.
     * @throws SQLException si ocurre algún problema durante la consulta.
     */
    public List<Usuario> listarUsuarios() throws SQLException {
        List<Usuario> usuarios = new ArrayList<>();
        try (Connection conexion = ConexionBD.obtenerConexion();
                PreparedStatement sentencia = conexion.prepareStatement(LISTAR_TODOS);
                ResultSet resultado = sentencia.executeQuery()) {

            while (resultado.next()) {
                usuarios.add(mapearUsuario(resultado));
            }
        }
        return usuarios;
    }

    /**
     * Construye un objeto {@link Usuario} a partir de un {@link ResultSet}.
     *
     * @param resultado resultado de la consulta.
     * @return entidad de usuario completamente poblada.
     * @throws SQLException si ocurre un problema al leer los datos.
     */
    private Usuario mapearUsuario(ResultSet resultado) throws SQLException {
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(resultado.getInt("id_usuario"));
        usuario.setIdRol(resultado.getInt("id_rol"));
        usuario.setNombreRol(resultado.getString("nombre_rol"));
        usuario.setNombre(resultado.getString("nombre"));
        usuario.setUsername(resultado.getString("username"));
        usuario.setPasswordHash(resultado.getString("password_hash"));
        usuario.setCorreo(resultado.getString("correo"));
        usuario.setTelefono(resultado.getString("telefono"));
        usuario.setMatricula(resultado.getString("matricula"));
        usuario.setEstado(resultado.getString("estado"));
        Timestamp registro = resultado.getTimestamp("fecha_registro");
        usuario.setFechaRegistro(registro != null ? registro.toLocalDateTime() : null);
        Timestamp ultima = resultado.getTimestamp("ultima_conexion");
        usuario.setUltimaConexion(ultima != null ? ultima.toLocalDateTime() : null);
        return usuario;
    }

    private void asignarNullable(PreparedStatement sentencia, int indice, String valor) throws SQLException {
        if (valor != null && !valor.trim().isEmpty()) {
            sentencia.setString(indice, valor);
        } else {
            sentencia.setNull(indice, Types.VARCHAR);
        }
    }
}
