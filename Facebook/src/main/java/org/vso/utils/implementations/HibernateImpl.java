package org.vso.utils.implementations;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.vso.utils.contracts.Hibernate;

public class HibernateImpl implements Hibernate {
    public static final String PERSISTENCE_UNIT_NAME = "Facebook";

    @Override
    public EntityManagerFactory buildEntityManagerFactory() {
        try {
            return Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        } catch (Throwable ex) {
            ex.printStackTrace();
            throw new ExceptionInInitializerError(ex);
        }
    }
}
