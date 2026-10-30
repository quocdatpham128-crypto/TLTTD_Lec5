package com.example.tlttd_lec5.model

import java.io.Serializable

data class User(
    val name: String,
    val email: String,
    val phone: String,
    val dob: String,
    val gender: String,
    val address: String,
) : Serializable