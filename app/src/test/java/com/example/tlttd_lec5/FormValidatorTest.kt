package com.example.tlttd_lec5

import com.example.tlttd_lec5.validator.FormValidator
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class FormValidatorTest {

    @Test
    fun isValidName_validNames_returnsTrue() {
        assertTrue(FormValidator.isValidName("Anh"))
        assertTrue(FormValidator.isValidName("Nguyễn Văn A"))
    }

    @Test
    fun isValidName_invalidNames_returnsFalse() {
        assertFalse(FormValidator.isValidName("An")) // length 2 (< 3)
        assertFalse(FormValidator.isValidName("  An ")) // length 2
        assertFalse(FormValidator.isValidName(""))
        assertFalse(FormValidator.isValidName("  "))
    }

    @Test
    fun isValidEmail_validEmails_returnsTrue() {
        assertTrue(FormValidator.isValidEmail("user@example.com"))
        assertTrue(FormValidator.isValidEmail("test.user@domain.co.uk"))
        assertTrue(FormValidator.isValidEmail("admin@company.technology"))
    }

    @Test
    fun isValidEmail_invalidEmails_returnsFalse() {
        assertFalse(FormValidator.isValidEmail("invalid-email"))
        assertFalse(FormValidator.isValidEmail("@domain.com"))
        assertFalse(FormValidator.isValidEmail("user@.com"))
        assertFalse(FormValidator.isValidEmail(""))
    }

    @Test
    fun isValidPassword_validPasswords_returnsTrue() {
        assertTrue(FormValidator.isValidPassword("123456"))
        assertTrue(FormValidator.isValidPassword("password123"))
    }

    @Test
    fun isValidPassword_invalidPasswords_returnsFalse() {
        assertFalse(FormValidator.isValidPassword("12345"))
        assertFalse(FormValidator.isValidPassword(""))
    }

    @Test
    fun isValidPhone_validPhones_returnsTrue() {
        assertTrue(FormValidator.isValidPhone("0912345678"))
        assertTrue(FormValidator.isValidPhone("0389998888"))
        assertTrue(FormValidator.isValidPhone("+84912345678"))
        assertTrue(FormValidator.isValidPhone("84912345678"))
    }

    @Test
    fun isValidPhone_invalidPhones_returnsFalse() {
        assertFalse(FormValidator.isValidPhone("123456789"))
        assertFalse(FormValidator.isValidPhone("0123456789"))
        assertFalse(FormValidator.isValidPhone("091234567"))
        assertFalse(FormValidator.isValidPhone("abcdefghij"))
        assertFalse(FormValidator.isValidPhone(""))
    }

    @Test
    fun isValidDob_validDob_returnsTrue() {
        assertTrue(FormValidator.isValidDob("15/08/2000"))
    }

    @Test
    fun isValidDob_invalidDob_returnsFalse() {
        assertFalse(FormValidator.isValidDob(""))
        assertFalse(FormValidator.isValidDob("   "))
    }

    @Test
    fun isValidAddress_validAddress_returnsTrue() {
        assertTrue(FormValidator.isValidAddress("TP. Hồ Chí Minh"))
    }

    @Test
    fun isValidAddress_invalidAddress_returnsFalse() {
        assertFalse(FormValidator.isValidAddress("HN")) // length 2 (< 3)
        assertFalse(FormValidator.isValidAddress(""))
        assertFalse(FormValidator.isValidAddress("   "))
    }
}