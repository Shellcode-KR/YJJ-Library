package com.yjj.library.model.dao;

import com.yjj.library.model.entities.Editorial;
import com.yjj.library.utils.JpaUtil;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

public class EditorialDAO extends BaseDAO<Editorial> {

    public EditorialDAO() {
        super(Editorial.class);
    }

    public Editorial buscarPorNombre(String nombre) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            TypedQuery<Editorial> q = em.createQuery(
                "SELECT e FROM Editorial e WHERE e.nombre = :nombre", Editorial.class);
            q.setParameter("nombre", nombre);
            return q.getResultStream().findFirst().orElse(null);
        } finally {
            em.close();
        }
    }
}
