package com.yjj.library.model.entities;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Entidad que representa a los usuarios del sistema de biblioteca.
 */
public class Usuario {

    private Integer idUsuario;
    private Integer idRol;
    private String nombreRol;
    private String nombre;
    private String username;
    private String passwordHash;
    private String correo;
    private String telefono;
    private String matricula;
    private String estado;
    private LocalDateTime fechaRegistro;
    private LocalDateTime ultimaConexion;

    /**
     * Constructor por defecto requerido por algunas librerías de mapeo.
     */
    public Usuario() {
        // Constructor vacío
    }

    /**
     * Constructor completo para facilitar la creación del objeto desde la capa DAO.
     *
     * @param idUsuario identificador único en la base de datos.
     * @param idRol identificador del rol asociado.
     * @param nombreRol nombre legible del rol.
     * @param nombre nombre completo del usuario.
     * @param username nombre de usuario utilizado para autenticarse.
     * @param passwordHash contraseña hasheada almacenada.
     * @param correo correo electrónico de contacto.
     * @param telefono número telefónico de contacto.
     * @param matricula matrícula institucional (si aplica).
     * @param estado estado del usuario (ACTIVO, INACTIVO, etc.).
     * @param fechaRegistro fecha y hora de creación del registro.
     * @param ultimaConexion fecha y hora del último acceso exitoso.
     */
    public Usuario(Integer idUsuario, Integer idRol, String nombreRol, String nombre, String username,
            String passwordHash, String correo, String telefono, String matricula, String estado,
            LocalDateTime fechaRegistro, LocalDateTime ultimaConexion) {
        this.idUsuario = idUsuario;
        this.idRol = idRol;
        this.nombreRol = nombreRol;
        this.nombre = nombre;
        this.username = username;
        this.passwordHash = passwordHash;
        this.correo = correo;
        this.telefono = telefono;
        this.matricula = matricula;
        this.estado = estado;
        this.fechaRegistro = fechaRegistro;
        this.ultimaConexion = ultimaConexion;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getIdRol() {
        return idRol;
    }

    public void setIdRol(Integer idRol) {
        this.idRol = idRol;
    }

    public String getNombreRol() {
        return nombreRol;
    }

    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public LocalDateTime getUltimaConexion() {
        return ultimaConexion;
    }

    public void setUltimaConexion(LocalDateTime ultimaConexion) {
        this.ultimaConexion = ultimaConexion;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUsuario, username);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Usuario other = (Usuario) obj;
        return Objects.equals(idUsuario, other.idUsuario)
                && Objects.equals(username, other.username);
    }

    @Override
    public String toString() {
        return "Usuario{"
                + "idUsuario=" + idUsuario
                + ", idRol=" + idRol
                + ", nombreRol='" + nombreRol + '\''
                + ", nombre='" + nombre + '\''
                + ", username='" + username + '\''
                + ", correo='" + correo + '\''
                + ", telefono='" + telefono + '\''
                + ", matricula='" + matricula + '\''
                + ", estado='" + estado + '\''
                + ", fechaRegistro=" + fechaRegistro
                + ", ultimaConexion=" + ultimaConexion
                + '}';
    }
}
