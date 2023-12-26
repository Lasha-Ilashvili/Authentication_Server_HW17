package com.example.authentication_server_hw17.di

import com.example.authentication_server_hw17.data.login.LoginService
import com.example.authentication_server_hw17.data.register.RegisterService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private const val BASE_URL = "https://reqres.in/api/"

    @Singleton
    @Provides
    fun provideRetrofitClient(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideRegisterResult(retrofit: Retrofit): RegisterService =
        retrofit.create(RegisterService::class.java)

    @Singleton
    @Provides
    fun provideLoginResult(retrofit: Retrofit): LoginService =
        retrofit.create(LoginService::class.java)
}