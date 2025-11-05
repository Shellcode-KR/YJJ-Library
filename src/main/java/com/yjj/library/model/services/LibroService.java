package com.yjj.library.model.services;


import com.yjj.library.model.dao.LibroDAO;
import com.yjj.library.model.entities.Libro;
import java.util.List;

public class LibroService {
    private final LibroDAO libroDAO = new LibroDAO();

    public List<Libro> listarTodos() {
        return libroDAO.findAll();
    }

    public void registrar(Libro libro) {
        libroDAO.create(libro);
    }

    public void actualizar(Libro libro) {
        libroDAO.update(libro);
    }

    public void eliminar(Integer idLibro) {
        libroDAO.delete(idLibro);
    }

    public Libro buscarPorId(Integer id) {
        return libroDAO.findById(id);
    }

    public List<Libro> buscarPorTitulo(String titulo) {
        return libroDAO.buscarPorTitulo(titulo);
    }

    public List<Libro> buscarPorAutor(String autor) {
        return libroDAO.buscarPorAutor(autor);
    }

    public List<Libro> buscarPorCategoria(String categoria) {
        return libroDAO.buscarPorCategoria(categoria);
    }
}
