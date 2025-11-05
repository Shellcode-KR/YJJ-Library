package com.yjj.library.model.dao;

import com.yjj.library.model.entities.Ejemplar;
import com.yjj.library.utils.JpaUtil;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

public class EjemplarDAO extends BaseDAO<Ejemplar> {

    public EjemplarDAO() {
        super(Ejemplar.class);
    }

    public List<Ejemplar> buscarPorEstado(String estado) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            TypedQuery<Ejemplar> q = em.createQuery(
                "SELECT e FROM Ejemplar e WHERE e.estado = :estado", Ejemplar.class);
            q.setParameter("estado", estado);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Ejemplar> buscarPorLibro(int idLibro) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            TypedQuery<Ejemplar> q = em.createQuery(
                "SELECT e FROM Ejemplar e WHERE e.libro.idLibro = :idLibro", Ejemplar.class);
            q.setParameter("idLibro", idLibro);
            return q.getResultList();
        } finally {
            em.close();
        }
    }
}
