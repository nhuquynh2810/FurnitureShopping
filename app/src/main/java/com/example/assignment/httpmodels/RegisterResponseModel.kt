package com.example.assignment.httpmodels

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.Date

data class CartItem(
    @JsonProperty("_id") val _id: String,
    @JsonProperty("name") val name: String,
    @JsonProperty("quantity") val quantity: Int,
    @JsonProperty("status") val status: Int,
    @JsonProperty("images") val images: List<String>,
    @JsonProperty("date") val date: Date,
)


data class RegisterResponseModel(
    @JsonProperty("status") val status: Boolean,
    @JsonProperty("data") val data: Data
)

data class Data(
    @JsonProperty("email") val email: String,
    @JsonProperty("password") val password: String,
    @JsonProperty("name") val name: String,
    @JsonProperty("role") val role: Int,
    @JsonProperty("carts") val carts: List<CartItem>,
    @JsonProperty("code") val code: Int,
    @JsonProperty("phoneNumber") val phoneNumber: String,
    @JsonProperty("address") val address: String,
    @JsonProperty("avatar") val avatar: String,
    @JsonProperty("isVerified") val isVerified: Int,
    @JsonProperty("available") val available: Boolean,
    @JsonProperty("_id") val id: String,
    @JsonProperty("createAt") val createAt: Date,
    @JsonProperty("updateAt") val updateAt: Date,
    @JsonProperty("__v") val version: Int
)
