package com.yjj.library.model.services;


import com.yjj.library.model.dao.CategoriaDAO;
import com.yjj.library.model.entities.Categoria;
import java.util.List;

public class CategoriaService {
    private final CategoriaDAO categoriaDAO = new CategoriaDAO();

    public List<Categoria> listarTodas() {
        return categoriaDAO.findAll();
    }

    public List<Categoria> listarSubcategorias(Integer idPadre) {
        return categoriaDAO.listarSubcategorias(idPadre);
    }

    public void registrar(Categoria categoria) {
        categoriaDAO.create(categoria);
    }

    public void actualizar(Categoria categoria) {
        categoriaDAO.update(categoria);
    }

    public void eliminar(Integer id) {
        categoriaDAO.delete(id);
    }
}
