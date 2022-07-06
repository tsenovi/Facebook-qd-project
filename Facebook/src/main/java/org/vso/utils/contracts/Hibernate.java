package org.vso.utils.contracts;

import jakarta.persistence.EntityManagerFactory;

public interface Hibernate {
    EntityManagerFactory buildEntityManagerFactory();
}
