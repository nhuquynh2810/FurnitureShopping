package com.example.assignment.helpers

import com.example.assignment.httpmodels.LoginRequestModel
import com.example.assignment.httpmodels.LoginResponseModel
import com.example.assignment.httpmodels.ProductDetailResponseModel
import com.example.assignment.httpmodels.ProductResponseModel
import com.example.assignment.httpmodels.RegisterRequestModel
import com.example.assignment.httpmodels.RegisterResponseModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface IRetrofit {
    // Khai báo API dành cho app

    //API đăng kí
    //http://localhost:7777/users/register_App
    @POST("users/register_App")
    fun register(@Body registerRequestModel: RegisterRequestModel)
            : Call<RegisterResponseModel>

    //API đăng nhập
    //http://localhost:7777/users/login
    @POST("users/login")
    fun login(@Body loginRequestModel: LoginRequestModel)
            : Call<LoginResponseModel>

    //API lấy danh sách sản phẩm
    //http://localhost:7777/products/category?id=65f2031afc13ae2f66316ebc
    @GET("products/category")
    fun getProductsByCategoryId(@Query("id") category: String?): Call<ProductResponseModel>

    //API lấy chi tiết sản phẩm theo id
    //http://localhost:7777/products/getProductById_App/665f42a357007ed4d4b1172f
    @GET("products/getProductById_App/{id}")
    fun getProductById(@Path("id") id: String?) : Call<ProductDetailResponseModel>
}