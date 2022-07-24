package org.vso.domain;

import org.junit.jupiter.api.Test;
import org.vso.constants.LoginStatus;
import org.vso.constants.RegistrationStatus;
import org.vso.models.services.contracts.AuthenticationService;
import org.vso.models.services.implementations.AuthenticationServiceImpl;
import org.vso.models.dto.UserLoginDTO;
import org.vso.models.dto.UserRegistrationDTO;

import static org.junit.jupiter.api.Assertions.*;

class AuthenticationServiceTest {

    @Test
    void testRegisterUserWhenUserExistsThenRegistrationFail() {
        UserRegistrationDTO userRegistrationDTO = new UserRegistrationDTO("ivan@gmail.com", "111",
                "111", "Ivan", "Tsenov", 31);
        AuthenticationService authenticationService = AuthenticationServiceImpl.getInstance();
        assertEquals(RegistrationStatus.REGISTRATION_FAILED, authenticationService.registerUser(userRegistrationDTO));
    }

    @Test
    void testRegisterUserWhenUserNotExistsThenRegistrationSuccess() {
        UserRegistrationDTO userRegistrationDTO = new UserRegistrationDTO("georgi@gmail.com", "161",
                "161", "Georgi", "Ivanov", 21);
        AuthenticationService authenticationService = AuthenticationServiceImpl.getInstance();
        assertEquals(RegistrationStatus.REGISTRATION_SUCCESSFUL, authenticationService.registerUser(userRegistrationDTO));
    }

    @Test
    void testLoginWhenUserExistsThenLoginSuccessful() {
        UserLoginDTO userLoginDTO = new UserLoginDTO("ivan@gmail.com", "111");
        AuthenticationService authenticationService = AuthenticationServiceImpl.getInstance();
        assertEquals(LoginStatus.LOGIN_SUCCESSFUL, authenticationService.login(userLoginDTO));
    }

    @Test
    void testLoginWhenUserNotExistsThenLoginFailed() {
        UserLoginDTO userLoginDTO = new UserLoginDTO("ivan111@gmail.com", "909");
        AuthenticationService authenticationService = AuthenticationServiceImpl.getInstance();
        assertEquals(LoginStatus.LOGIN_FAILED, authenticationService.login(userLoginDTO));
    }
}