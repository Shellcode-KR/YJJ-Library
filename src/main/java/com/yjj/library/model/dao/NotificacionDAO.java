package com.yjj.library.model.dao;

import com.yjj.library.model.entities.Notificacion;
import com.yjj.library.utils.JpaUtil;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

public class NotificacionDAO extends BaseDAO<Notificacion> {

    public NotificacionDAO() {
        super(Notificacion.class);
    }

    public List<Notificacion> buscarPorUsuario(int idUsuario) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            TypedQuery<Notificacion> q = em.createQuery(
                "SELECT n FROM Notificacion n WHERE n.usuario.idUsuario = :idUsuario ORDER BY n.fechaEnvio DESC",
                Notificacion.class);
            q.setParameter("idUsuario", idUsuario);
            return q.getResultList();
        } finally {
            em.close();
        }
    }
}
