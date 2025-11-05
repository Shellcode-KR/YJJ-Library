package com.yjj.library.model.dao;

import com.yjj.library.model.entities.Libro;
import com.yjj.library.utils.JpaUtil;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;



public class LibroDAO extends BaseDAO<Libro> {

    public LibroDAO() {
        super(Libro.class);
    }

    public List<Libro> buscarPorTitulo(String titulo) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            TypedQuery<Libro> q = em.createQuery(
                "SELECT l FROM Libro l WHERE LOWER(l.titulo) LIKE LOWER(:titulo)",
                Libro.class
            );
            q.setParameter("titulo", "%" + titulo + "%");
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Libro> buscarPorAutor(String nombreAutor) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            TypedQuery<Libro> q = em.createQuery(
                "SELECT l FROM Libro l JOIN l.autores a WHERE LOWER(a.nombre) LIKE LOWER(:nombre)",
                Libro.class
            );
            q.setParameter("nombre", "%" + nombreAutor + "%");
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Libro> buscarPorCategoria(String nombreCategoria) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            TypedQuery<Libro> q = em.createQuery(
                "SELECT l FROM Libro l JOIN l.categorias c WHERE LOWER(c.nombre) LIKE LOWER(:nombre)",
                Libro.class
            );
            q.setParameter("nombre", "%" + nombreCategoria + "%");
            return q.getResultList();
        } finally {
            em.close();
        }
    }
}
