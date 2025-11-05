package com.yjj.library.model.services;


import com.yjj.library.model.dao.PrestamoDAO;
import com.yjj.library.model.entities.Prestamo;
import java.util.List;

public class PrestamoService {
    private final PrestamoDAO prestamoDAO = new PrestamoDAO();

    public List<Prestamo> listarTodos() {
        return prestamoDAO.findAll();
    }

    public void registrar(Prestamo prestamo) {
        prestamoDAO.create(prestamo);
    }

    public void actualizar(Prestamo prestamo) {
        prestamoDAO.update(prestamo);
    }

    public void eliminar(Integer idPrestamo) {
        prestamoDAO.delete(idPrestamo);
    }

    public Prestamo buscarPorId(Integer id) {
        return prestamoDAO.findById(id);
    }

    public List<Prestamo> listarPorUsuario(Integer idUsuario) {
        return prestamoDAO.buscarPorUsuario(idUsuario);
    }

    public List<Prestamo> listarActivos() {
        return prestamoDAO.buscarActivos();
    }

    public List<Prestamo> listarAtrasados() {
        return prestamoDAO.buscarAtrasados();
    }
}
