package com.example.adbuy.model.response.register

import com.google.gson.annotations.SerializedName

data class RegisterResponse(
    val response: Response? = null,
    val message: String? = null,
    val errors: Any? = null,
    val status: Int? = null
)

data class Data(
    val mobile: Int? = null,
    val lastName: String? = null,
    val id: Int? = null,
    val firstName: String? = null,
    val email: String? = null,
    val token: String? = null
)

data class Response(
    val data: Data? = null,
    val meta: Any? = null
)
