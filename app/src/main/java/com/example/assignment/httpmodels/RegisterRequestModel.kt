package com.example.assignment.httpmodels

import com.fasterxml.jackson.annotation.JsonProperty

//Model gửi đi dành cho đăng kí
data class RegisterRequestModel(
    @JsonProperty("name") val name: String,
    @JsonProperty("email") val email: String,
    @JsonProperty("password") val password: String
)
