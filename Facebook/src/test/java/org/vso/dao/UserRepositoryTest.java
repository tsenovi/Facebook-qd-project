package org.vso.dao;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryTest {

    @Test
    void testReadUserByEmailWhenEmailNotExistsThenUserNotExists() {
        String email = "van@gmail.com";
        var userRepository = new UserRepository();
        assertNull(userRepository.readUserByEmail(email));
    }

    @Test
    void testReadUserByEmailWhenEmailExistsThenUserExists() {
        String email = "vandertsen@gmail.com";
        var userRepository = new UserRepository();
        assertNotNull(userRepository.readUserByEmail(email));
    }
}