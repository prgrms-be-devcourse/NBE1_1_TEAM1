package com.programmers.mycoffee.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmailTest {

    @Test
    void testInvalidEmail() {
        assertThrows(IllegalArgumentException.class, () -> {
            var email = new Email("accccc");
        });
    }

    @Test
    void testValidEmail() {
        var email = new Email("hello@gmail.com");
        assertEquals("hello@gmail.com", email.getAddress());
    }

    @Test
    void testEqEmail() {
        Email email1 = new Email("hello@gmail.com");
        Email email2 = new Email("hello@gmail.com");
        assertEquals(email1, email2);
    }
}