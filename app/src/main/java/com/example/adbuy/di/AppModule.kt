package com.example.adbuy.di

import android.content.Context
import androidx.room.Room
import com.example.adbuy.BuildConfig
import com.example.adbuy.api.AdBuyApi
import com.example.adbuy.other.Constants.BASE_URL
import com.example.adbuy.other.Constants.SHARED_PREFERENCES_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule  {
    @Singleton
    @Provides
    fun providerSharedPreferances(@ApplicationContext app: Context)=app.getSharedPreferences(
        SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE
    )

    /*@Provides
    fun providerRoomDatabase(@ApplicationContext app: Context) = Room.databaseBuilder(
        app,
        BuyDatabase::class.java,
        ADBUY_DATABASE_NAME
    ).build()
    @Singleton
    @Provides
    fun provideRunDao(db: BuyDatabase) = db.getDao()*/
    @Provides
    @Singleton
    fun provideOkHttpClient() = if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    } else OkHttpClient
        .Builder()
        .build()


    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit) = retrofit.create(AdBuyApi::class.java)


}