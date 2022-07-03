package org.vso.domain;

import org.junit.jupiter.api.Test;
import org.vso.constants.LoginStatus;
import org.vso.constants.RegistrationStatus;
import org.vso.dto.UserLoginDTO;
import org.vso.dto.UserRegistrationDTO;

import static org.junit.jupiter.api.Assertions.*;

class AuthenticationServiceTest {

    @Test
    void testRegisterUserWhenUserExistsThenRegistrationFail() {
        UserRegistrationDTO userRegistrationDTO = new UserRegistrationDTO("ivan@gmail.com", "111",
                "Ivan", "Tsenov", 31);
        var authenticationService = AuthenticationService.getInstance();
        assertEquals(RegistrationStatus.REGISTRATION_FAILED, authenticationService.registerUser(userRegistrationDTO));
    }

    @Test
    void testRegisterUserWhenUserNotExistsThenRegistrationSuccess() {
        UserRegistrationDTO userRegistrationDTO = new UserRegistrationDTO("georgi@gmail.com", "161",
                "Georgi", "Ivanov", 21);
        var authenticationService = AuthenticationService.getInstance();
        assertEquals(RegistrationStatus.REGISTRATION_SUCCESSFUL, authenticationService.registerUser(userRegistrationDTO));
    }

    @Test
    void testLoginWhenUserExistsThenLoginSuccessful() {
        UserLoginDTO userLoginDTO = new UserLoginDTO("ivan@gmail.com", "111");
        var authenticationService = AuthenticationService.getInstance();
        assertEquals(LoginStatus.LOGIN_SUCCESSFUL, authenticationService.login(userLoginDTO));
    }

    @Test
    void testLoginWhenUserNotExistsThenLoginFailed() {
        UserLoginDTO userLoginDTO = new UserLoginDTO("ivan111@gmail.com", "909");
        var authenticationService = AuthenticationService.getInstance();
        assertEquals(LoginStatus.LOGIN_FAILED, authenticationService.login(userLoginDTO));
    }
}