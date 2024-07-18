package com.example.assignment.httpmodels

import com.fasterxml.jackson.annotation.JsonProperty


data class CategoryItem(
    @JsonProperty("category_id") val category_id: String,
    @JsonProperty("category_name") val category_name: String,
)

data class ProductModel(
    @JsonProperty("_id") val _id: String,
    @JsonProperty("name") val name: String,
    @JsonProperty("price") val price: Int,
    @JsonProperty("quantity") val quantity: Int,
    @JsonProperty("images") val images: List<String>,
    @JsonProperty("description") val description: String,
    @JsonProperty("category") val category: CategoryItem,
    @JsonProperty("createAt") val createAt: String,
    @JsonProperty("updateAt") val updateAt: String,
    @JsonProperty("__v") val __v: Int,
)

// lấy danh sách
data class ProductResponseModel(
    @JsonProperty("status") val status: Boolean,
    @JsonProperty("data") val data: List<ProductModel>
)

//lấy chi tiết
data class ProductDetailResponseModel(
    @JsonProperty("status") val status: Boolean,
    @JsonProperty("data") val data: ProductModel
)

