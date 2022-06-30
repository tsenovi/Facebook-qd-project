package org.vso.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HibernateUtilTest {

    @Test
    void testBuildEntityManagerFactoryWhenThrowExceptionThenReturnExceptionInInitializerError() {
        var util = new HibernateUtil();
        assertThrows(ExceptionInInitializerError.class, util::buildEntityManagerFactory);
    }

}