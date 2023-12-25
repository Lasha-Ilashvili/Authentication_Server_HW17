package com.example.authentication_server_hw17.di

import com.example.authentication_server_hw17.data.login.LogInRepositoryImpl
import com.example.authentication_server_hw17.data.login.LoginService
import com.example.authentication_server_hw17.data.register.RegisterRepositoryImpl
import com.example.authentication_server_hw17.data.register.RegisterService
import com.example.authentication_server_hw17.domain.login.LogInRepository
import com.example.authentication_server_hw17.domain.register.RegisterRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideRegisterRepository(registerService: RegisterService): RegisterRepository =
        RegisterRepositoryImpl(registerService)

    @Singleton
    @Provides
    fun provideLoginRepository(loginService: LoginService): LogInRepository =
        LogInRepositoryImpl(loginService)

}