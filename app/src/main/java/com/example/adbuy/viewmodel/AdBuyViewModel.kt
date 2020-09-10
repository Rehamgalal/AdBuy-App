package com.example.adbuy.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.adbuy.model.`object`.login.Login
import com.example.adbuy.model.`object`.register.Register
import com.example.adbuy.model.response.login.UserData
import com.example.adbuy.model.response.register.RegisterResponse
import com.example.adbuy.other.Resource
import com.example.adbuy.repository.AdBuyRepository
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException

class AdBuyViewModel @ViewModelInject constructor(private val adBuyRepository: AdBuyRepository) : ViewModel() {
    val userData: MutableLiveData<Resource<UserData>> = MutableLiveData()
    var loginResponse: UserData? = null

    val registerData: MutableLiveData<Resource<RegisterResponse>> = MutableLiveData()
    var registeResponse: RegisterResponse? = null


    fun loginAdBuy (login: Login)=viewModelScope.launch {
        safeLoginCall(login)
    }
    fun registerAdBuy (register: Register)=viewModelScope.launch {
        safeSignupCall(register)
    }

    private fun handleLoginResponse(response: Response<UserData>): Resource<UserData> {
        if (response.body()?.status==200) {
            response.body()?.let { resultResponse ->

                loginResponse = resultResponse
                return Resource.Success(loginResponse ?: resultResponse)
            }
        }
        return Resource.Error(response.body()?.message)
    }

    private fun handleRegisterResponse(response: Response<RegisterResponse>): Resource<RegisterResponse> {
        if (response.body()?.status==200) {
            response.body()?.let { resultResponse ->

                registeResponse = resultResponse
                return Resource.Success(registeResponse ?: resultResponse)
            }
        }
        return Resource.Error(response.body()?.message)
    }
    suspend fun safeLoginCall(login: Login) {
        userData.postValue(Resource.Loading())
        try {
            val response = adBuyRepository.loginUser(login)
            userData.postValue(handleLoginResponse(response))

        } catch (t: Throwable) {
            when (t) {
                is IOException -> userData.postValue(Resource.Error(t.message))
                else -> userData.postValue(Resource.Error("Conversion Error"))
            }
        }
    }

    suspend fun safeSignupCall(
        register: Register
    ) {
        userData.postValue(Resource.Loading())
        try {
            val response = adBuyRepository.signUp(register)
            registerData.postValue(handleRegisterResponse(response))

        } catch (t: Throwable) {
            when (t) {
                is IOException -> registerData.postValue(Resource.Error(t.message))
                else -> registerData.postValue(Resource.Error("Conversion Error"))
            }
        }
    }
}