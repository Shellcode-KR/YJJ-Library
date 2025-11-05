package com.yjj.library.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;



public class JpaUtil {
    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("BibliotecaPU");

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
}
