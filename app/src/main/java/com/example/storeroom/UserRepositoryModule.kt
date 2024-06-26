package com.example.storeroom

import com.example.storeroom.domain.link.AddLinkRepository
import com.example.storeroom.domain.link.AddLinkRepositoryImpl
import com.example.storeroom.domain.link.GetLinkRepository
import com.example.storeroom.domain.link.GetLinkRepositoryImpl
import com.example.storeroom.domain.link.UpdateLinkRepository
import com.example.storeroom.domain.link.UpdateLinkRepositoryImpl
import com.example.storeroom.domain.login.LoginRepository
import com.example.storeroom.domain.login.LoginRepositoryImpl
import com.example.storeroom.domain.register.RegisterRepository
import com.example.storeroom.domain.register.RegisterRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UserRepositoryModule {

    @Binds
    abstract fun bindLoginRepository(impl: LoginRepositoryImpl): LoginRepository

    @Binds
    abstract fun bindRegisterRepository(impl: RegisterRepositoryImpl): RegisterRepository

    @Binds
    abstract fun bindAddLinkRepository(impl: AddLinkRepositoryImpl): AddLinkRepository

    @Binds
    abstract fun bindGetLinkRepository(impl: GetLinkRepositoryImpl): GetLinkRepository

    @Binds
    abstract fun bindUpdateLinkRepository(impl: UpdateLinkRepositoryImpl): UpdateLinkRepository
}