package com.example.storeroom

import com.example.storeroom.domain.login.LoginUseCase
import com.example.storeroom.domain.login.LoginUseCaseImpl
import com.example.storeroom.domain.register.RegisterUseCase
import com.example.storeroom.domain.register.RegisterUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class UseCaseModule {

    @Binds
    abstract fun bindLoginUseCase(loginUseCaseImpl: LoginUseCaseImpl): LoginUseCase


    @Binds
    abstract fun bindRegisterUseCase(registerUseCaseImpl: RegisterUseCaseImpl): RegisterUseCase
}
