package com.example.adbuy.repository

import com.example.adbuy.api.AdBuyApi
import javax.inject.Inject

class AdBuyRepository @Inject constructor(private val apiService:AdBuyApi) {
    suspend fun loginUser(email: String, password:String) =
        apiService.login(email, password)
    suspend fun signUp(firstName:String,lastName:String, email:String,
                      password:String, confirmPassword:String, mobile:String) =
        apiService.signup(firstName,lastName,email,password,confirmPassword,mobile)
}