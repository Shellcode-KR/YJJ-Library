package com.yjj.library.model.dao;

import com.yjj.library.model.entities.Autor;
import com.yjj.library.utils.JpaUtil;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

public class AutorDAO extends BaseDAO<Autor> {

    public AutorDAO() {
        super(Autor.class);
    }

    public Autor buscarPorNombre(String nombre) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            TypedQuery<Autor> q = em.createQuery(
                "SELECT a FROM Autor a WHERE a.nombre = :nombre", Autor.class);
            q.setParameter("nombre", nombre);
            return q.getResultStream().findFirst().orElse(null);
        } finally {
            em.close();
        }
    }
}
