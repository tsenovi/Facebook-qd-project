package org.vso.dao;

import org.junit.jupiter.api.Test;
import org.vso.models.dao.contracts.UserDao;
import org.vso.models.dao.implementations.UserDaoImpl;
import org.vso.models.data.User;

import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryTest {

    @Test
    void testReadUserByEmailWhenEmailNotExistsThenUserNotExists() {
        String email = "van@gmail.com";
        UserDao<User> userDao = new UserDaoImpl();
        assertNull(userDao.getByEmail(email));
    }

    @Test
    void testReadUserByEmailWhenEmailExistsThenUserExists() {
        String email = "vandertsen@gmail.com";
        UserDao<User> userDao = new UserDaoImpl();
        assertNotNull(userDao.getByEmail(email));
    }
}