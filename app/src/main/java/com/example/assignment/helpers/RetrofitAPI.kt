package com.example.assignment.helpers

import android.util.Log
import com.example.assignment.httpmodels.LoginRequestModel
import com.example.assignment.httpmodels.LoginResponseModel
import com.example.assignment.httpmodels.ProductDetailResponseModel
import com.example.assignment.httpmodels.ProductResponseModel
import com.example.assignment.httpmodels.RegisterRequestModel
import com.example.assignment.httpmodels.RegisterResponseModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RetrofitAPI {
private val retrofit = RetrofitClient.getClient()
    private val api = retrofit.create(IRetrofit::class.java)

    //fun đăng kí
    fun register(
        user: RegisterRequestModel,
        callback: (RegisterResponseModel?) -> Unit
    ) {
        api.register(user).enqueue(object : Callback<RegisterResponseModel> {
            override fun onResponse(
                call: Call<RegisterResponseModel>,
                response: Response<RegisterResponseModel>
            ) {
                if (response.isSuccessful) {
                    val registerResponse = response.body()
                    callback(registerResponse)
                } else {
                    Log.d("Failed to register", response.message())
                }
            }

            override fun onFailure(call: Call<RegisterResponseModel>, t: Throwable) {
                Log.d("Failed to register", t.message ?: "Unknown error")
            }
        })

    }

    //fun đăng nhập
    fun login(
        user: LoginRequestModel,
        callback: (LoginResponseModel?) -> Unit
    ){
        api.login(user).enqueue(object : Callback<LoginResponseModel> {
            override fun onResponse(
                call: Call<LoginResponseModel>,
                response: Response<LoginResponseModel>
            ) {
                if (response.isSuccessful) {
                    val loginResponse = response.body()
                    callback(loginResponse)
                } else {
                    Log.d("Failed to login", response.message())
                }
            }

            override fun onFailure(call: Call<LoginResponseModel>, t: Throwable) {
                Log.d("Failed to login", t.message ?: "Unknown error")
            }
        })
    }

    //fun lấy danh sách sản phẩm
    fun getProductsByCategoryId(
        category: String?,
        callback: (ProductResponseModel?) -> Unit
    ){
        api.getProductsByCategoryId(category).enqueue(object : Callback<ProductResponseModel> {
            override fun onResponse(
                call: Call<ProductResponseModel>,
                response: Response<ProductResponseModel>
            ) {
                if (response.isSuccessful) {
                    val productResponse = response.body()
                    callback(productResponse)
                } else {
                    Log.d("Failed to get products", response.message())
                }
            }

            override fun onFailure(call: Call<ProductResponseModel>, t: Throwable) {
                Log.d("Failed to get products", t.message ?: "Unknown error")
            }
        })
    }

    //fun lấy chi tết sản phẩm
    fun getProductById(
        id: String?,
        callback: (ProductDetailResponseModel?) -> Unit
    ){
        api.getProductById(id).enqueue(object : Callback<ProductDetailResponseModel> {
            override fun onResponse(
                call: Call<ProductDetailResponseModel>,
                response: Response<ProductDetailResponseModel>
            ) {
                if (response.isSuccessful) {
                    val productDetailResponse = response.body()
                    callback(productDetailResponse)
                } else {
                    Log.d("Failed to get product detail", response.message())
                }
            }

            override fun onFailure(call: Call<ProductDetailResponseModel>, t: Throwable) {
                Log.d("Failed to get product detail", t.message ?: "Unknown error")
            }
        })
    }
}