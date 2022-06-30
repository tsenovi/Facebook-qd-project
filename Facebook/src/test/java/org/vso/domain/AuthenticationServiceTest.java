package org.vso.domain;

import org.junit.jupiter.api.Test;
import org.vso.dto.UserDTO;

import static org.junit.jupiter.api.Assertions.*;

class AuthenticationServiceTest {

    @Test
    void testRegisterUserWhenUserExistsThenRegistrationFail() {
        UserDTO userDTO = new UserDTO("ivan@gmail.com", "111",
                "Ivan", "Tsenov", 31);
        var authenticationService = new AuthenticationService();
        assertFalse(authenticationService.registerUser(userDTO));
    }

    @Test
    void testRegisterUserWhenUserNotExistsThenRegistrationSuccess() {
        UserDTO userDTO = new UserDTO("georgi@gmail.com", "161",
                "Georgi", "Ivanov", 21);
        var authenticationService = new AuthenticationService();
        assertTrue(authenticationService.registerUser(userDTO));
    }
}