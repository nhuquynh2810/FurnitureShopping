package com.example.assignment.httpmodels

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.Date

data class LoginResponseModel(
    @JsonProperty("status") val status: Boolean,
    @JsonProperty("data") val data: DataLogin
)

data class DataLogin(
    @JsonProperty("_id") val _id: String,
    @JsonProperty("email") val email: String,
    @JsonProperty("name") val name: String,
    @JsonProperty("role") val role: String,
    @JsonProperty("carts") val carts: List<CartItem>,
    @JsonProperty("code") val code: Int,
    @JsonProperty("phoneNumber") val phoneNumber: String,
    @JsonProperty("address") val address: String,
    @JsonProperty("avatar") val avatar: String,
    @JsonProperty("isVerified") val isVerified: Int,
    @JsonProperty("available") val available: Boolean,
    @JsonProperty("createAt") val createAt: Date,
    @JsonProperty("updateAt") val updateAt: Date,
    @JsonProperty("__v") val version: Int,
)

