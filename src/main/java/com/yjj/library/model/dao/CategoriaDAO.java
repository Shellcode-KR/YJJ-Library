package com.yjj.library.model.dao;

import com.yjj.library.model.entities.Categoria;
import com.yjj.library.utils.JpaUtil;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

public class CategoriaDAO extends BaseDAO<Categoria> {

    public CategoriaDAO() {
        super(Categoria.class);
    }

    public List<Categoria> listarSubcategorias(Integer idPadre) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            TypedQuery<Categoria> q = em.createQuery(
                "SELECT c FROM Categoria c WHERE c.padre.idCategoria = :idPadre", Categoria.class);
            q.setParameter("idPadre", idPadre);
            return q.getResultList();
        } finally {
            em.close();
        }
    }
}
