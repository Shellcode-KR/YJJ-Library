package com.yjj.library.model.dao;

import com.yjj.library.model.entities.Usuario;
import com.yjj.library.utils.JpaUtil;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;



public class UsuarioDAO extends BaseDAO<Usuario> {

    public UsuarioDAO() {
        super(Usuario.class);
    }

    public Usuario buscarPorUsername(String username) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            TypedQuery<Usuario> q = em.createQuery(
                "SELECT u FROM Usuario u WHERE u.username = :username", Usuario.class
            );
            q.setParameter("username", username);
            return q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

    public List<Usuario> buscarPorEstado(String estado) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            TypedQuery<Usuario> q = em.createQuery(
                "SELECT u FROM Usuario u WHERE u.estado = :estado", Usuario.class
            );
            q.setParameter("estado", estado);
            return q.getResultList();
        } finally {
            em.close();
        }
    }
}
