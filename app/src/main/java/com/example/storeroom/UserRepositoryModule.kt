package com.example.storeroom

import com.example.storeroom.repository.FirebaseUserRepository
import com.example.storeroom.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UserRepositoryModule {

    @Binds
    abstract fun bindUserRepository(impl:FirebaseUserRepository):UserRepository
}