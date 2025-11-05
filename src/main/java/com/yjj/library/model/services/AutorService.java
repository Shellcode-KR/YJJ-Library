package com.yjj.library.model.services;


import com.yjj.library.model.dao.AutorDAO;
import com.yjj.library.model.entities.Autor;
import java.util.List;

public class AutorService {
    private final AutorDAO autorDAO = new AutorDAO();

    public List<Autor> listarTodos() {
        return autorDAO.findAll();
    }

    public void registrar(Autor autor) {
        autorDAO.create(autor);
    }

    public Autor buscarPorNombre(String nombre) {
        return autorDAO.buscarPorNombre(nombre);
    }

    public void actualizar(Autor autor) {
        autorDAO.update(autor);
    }

    public void eliminar(Integer id) {
        autorDAO.delete(id);
    }
}
