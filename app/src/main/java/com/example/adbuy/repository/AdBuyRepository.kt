package com.example.adbuy.repository

import com.example.adbuy.api.AdBuyApi
import com.example.adbuy.model.`object`.login.Login
import com.example.adbuy.model.`object`.register.Register
import javax.inject.Inject

class AdBuyRepository @Inject constructor(private val api: AdBuyApi) {
    suspend fun loginUser(login: Login) =
        api.login(login)

    suspend fun signUp(
        register: Register
    ) =
        api.signup(register)
}