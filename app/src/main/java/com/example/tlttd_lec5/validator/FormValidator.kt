package com.example.tlttd_lec5.validator

object FormValidator {
    private val EMAIL_REGEX = Regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")
    private val PHONE_REGEX = Regex("^(0|\\+?84)[35789]\\d{8}$")

    fun isValidName(name: String) = name.trim().length >= 3
    fun isValidEmail(email: String) = email.isNotBlank() && EMAIL_REGEX.matches(email.trim())
    fun isValidPassword(pass: String) = pass.length >= 6
    fun isValidPhone(phone: String) = PHONE_REGEX.matches(phone.trim())
    fun isValidDob(dob: String) = dob.trim().isNotBlank()
    fun isValidAddress(address: String) = address.trim().length >= 3
}