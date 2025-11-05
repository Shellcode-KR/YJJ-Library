package com.yjj.library.model.dao;

import com.yjj.library.model.entities.Prestamo;
import com.yjj.library.utils.JpaUtil;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;



public class PrestamoDAO extends BaseDAO<Prestamo> {

    public PrestamoDAO() {
        super(Prestamo.class);
    }

    public List<Prestamo> buscarPorUsuario(Integer idUsuario) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            TypedQuery<Prestamo> q = em.createQuery(
                "SELECT p FROM Prestamo p WHERE p.usuario.idUsuario = :idUsuario ORDER BY p.fechaPrestamo DESC",
                Prestamo.class
            );
            q.setParameter("idUsuario", idUsuario);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Prestamo> buscarActivos() {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            TypedQuery<Prestamo> q = em.createQuery(
                "SELECT p FROM Prestamo p WHERE p.estado = 'ACTIVO'", Prestamo.class
            );
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Prestamo> buscarAtrasados() {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            TypedQuery<Prestamo> q = em.createQuery(
                "SELECT p FROM Prestamo p WHERE p.estado = 'ATRASADO'", Prestamo.class
            );
            return q.getResultList();
        } finally {
            em.close();
        }
    }
}
