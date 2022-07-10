package org.vso.utils;

import org.junit.jupiter.api.Test;
import org.vso.utils.contracts.Hibernate;
import org.vso.utils.implementations.HibernateImpl;

import static org.junit.jupiter.api.Assertions.*;

class HibernateUtilTest {

    @Test
    void testBuildEntityManagerFactoryWhenThrowExceptionThenReturnExceptionInInitializerError() {
        Hibernate hibernateUtil = new HibernateImpl();
        assertThrows(ExceptionInInitializerError.class, hibernateUtil::buildEntityManagerFactory);
    }

}