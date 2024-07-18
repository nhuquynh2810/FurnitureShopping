package com.example.assignment.models

import com.example.assignment.httpmodels.ProductModel

data class Cart(
    val product: ProductModel,
    val quantity: Int,
)
