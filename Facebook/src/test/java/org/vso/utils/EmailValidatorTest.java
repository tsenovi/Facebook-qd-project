package org.vso.utils;

import org.junit.jupiter.api.Test;
import org.vso.utils.contracts.EmailValidator;
import org.vso.utils.implementations.EmailValidatorImpl;

import static org.junit.jupiter.api.Assertions.*;

class EmailValidatorTest {

    @Test
    void testIsValidEmailWhenEmailIsCorrectThenEmailIsValid() {
        EmailValidator emailValidator = new EmailValidatorImpl();
        String email = "vandertsen@gmail.com";
        assertTrue(emailValidator.isValidEmail(email));
    }

    @Test
    void testIsValidEmailWhenEmailIsNotCorrectThenEmailIsNotValid() {
        EmailValidator emailValidator = new EmailValidatorImpl();
        String email = "vandertsen.gmail@com";
        assertFalse(emailValidator.isValidEmail(email));
    }
}