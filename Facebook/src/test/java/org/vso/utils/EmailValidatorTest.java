package org.vso.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmailValidatorTest {

    @Test
    void testIsValidEmailWhenEmailIsCorrectThenEmailIsValid() {
        var emailValidator = new EmailValidator();
        String email = "vandertsen@gmail.com";
        assertTrue(emailValidator.isValidEmail(email));
    }

    @Test
    void testIsValidEmailWhenEmailIsNotCorrectThenEmailIsNotValid() {
        var emailValidator = new EmailValidator();
        String email = "vandertsen.gmail@com";
        assertFalse(emailValidator.isValidEmail(email));
    }
}