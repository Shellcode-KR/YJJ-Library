package com.yjj.library.model.services;


import com.yjj.library.model.dao.EjemplarDAO;
import com.yjj.library.model.entities.Ejemplar;
import java.util.List;

public class EjemplarService {
    private final EjemplarDAO ejemplarDAO = new EjemplarDAO();

    public List<Ejemplar> listarTodos() {
        return ejemplarDAO.findAll();
    }

    public void registrarEjemplar(Ejemplar ejemplar) {
        ejemplarDAO.create(ejemplar);
    }

    public void actualizarEjemplar(Ejemplar ejemplar) {
        ejemplarDAO.update(ejemplar);
    }

    public void eliminarEjemplar(Integer id) {
        ejemplarDAO.delete(id);
    }

    public List<Ejemplar> buscarPorLibro(Integer idLibro) {
        return ejemplarDAO.buscarPorLibro(idLibro);
    }

    public List<Ejemplar> buscarPorEstado(String estado) {
        return ejemplarDAO.buscarPorEstado(estado);
    }
}
