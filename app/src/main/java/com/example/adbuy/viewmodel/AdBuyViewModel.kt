package com.example.adbuy.viewmodel

import android.provider.ContactsContract
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.adbuy.model.UserData
import com.example.adbuy.other.Resource
import com.example.adbuy.repository.AdBuyRepository
import retrofit2.Response
import java.io.IOException

class AdBuyViewModel @ViewModelInject constructor(private val adBuyRepository: AdBuyRepository) : ViewModel() {
    val userData: MutableLiveData<Resource<UserData>> = MutableLiveData()
    var loginResponse : UserData?=null
    private fun handleLoginResponse(response: Response<UserData>): Resource<UserData> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->

                    loginResponse = resultResponse
                return Resource.Success(loginResponse ?: resultResponse)
            }
        }
        return Resource.Error(response.message())
    }




    suspend fun safeLoginCall(email:String,password:String) {
        userData.postValue(Resource.Loading())
        try {
                val response = adBuyRepository.loginUser(email, password)
                userData.postValue(handleLoginResponse(response))

        } catch (t: Throwable) {
            when (t) {
                is IOException -> userData.postValue(Resource.Error("Network Failure"))
                else -> userData.postValue(Resource.Error("Conversion Error"))
            }
        }
    }
    suspend fun safeSignupCall(firstName:String,lastName:String, email:String,
                              password:String, confirmPassword:String, mobile:String) {
        userData.postValue(Resource.Loading())
        try {
            val response = adBuyRepository.signUp(firstName,lastName,email,password,confirmPassword,mobile)
            userData.postValue(handleLoginResponse(response))

        } catch (t: Throwable) {
            when (t) {
                is IOException -> userData.postValue(Resource.Error("Network Failure"))
                else -> userData.postValue(Resource.Error("Conversion Error"))
            }
        }
    }
}