package com.yjj.library.model.services;


import com.yjj.library.model.dao.EditorialDAO;
import com.yjj.library.model.entities.Editorial;
import java.util.List;

public class EditorialService {
    private final EditorialDAO editorialDAO = new EditorialDAO();

    public List<Editorial> listarTodas() {
        return editorialDAO.findAll();
    }

    public void registrar(Editorial editorial) {
        editorialDAO.create(editorial);
    }

    public Editorial buscarPorNombre(String nombre) {
        return editorialDAO.buscarPorNombre(nombre);
    }

    public void actualizar(Editorial editorial) {
        editorialDAO.update(editorial);
    }

    public void eliminar(Integer id) {
        editorialDAO.delete(id);
    }
}
