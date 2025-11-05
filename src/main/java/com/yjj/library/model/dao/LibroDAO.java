package com.yjj.library.model.dao;

import com.yjj.library.model.entities.Libro;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LibroDAO {

    public List<Libro> listarTodos() throws SQLException {
        List<Libro> lista = new ArrayList<>();
        String sql = "SELECT id_libro, titulo, anio_publicacion, isbn, ubicacion, estado FROM libros";
        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Libro l = new Libro();
                l.setIdLibro(rs.getInt("id_libro"));
                l.setTitulo(rs.getString("titulo"));
                l.setAnioPublicacion(rs.getInt("anio_publicacion"));
                l.setIsbn(rs.getString("isbn"));
                l.setUbicacion(rs.getString("ubicacion"));
                l.setEstado(rs.getString("estado"));
                lista.add(l);
            }
        }
        return lista;
    }

    public void insertar(Libro libro) throws SQLException {
        String sql = "INSERT INTO libros (titulo, anio_publicacion, isbn, id_editorial, ubicacion, estado) VALUES (?, ?, ?, NULL, ?, ?)";
        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, libro.getTitulo());
            ps.setInt(2, libro.getAnioPublicacion());
            ps.setString(3, libro.getIsbn());
            ps.setString(4, libro.getUbicacion());
            ps.setString(5, libro.getEstado());
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    libro.setIdLibro(keys.getInt(1));
                }
            }
        }
    }

    // editar, eliminar, buscarPorTitulo... implementar similar
}
