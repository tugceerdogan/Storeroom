package com.example.storeroom.repository

import com.example.storeroom.domain.UserLogin
import com.example.storeroom.domain.UserRegister

interface UserRepository {
    suspend fun registerUser(userRegister: UserRegister): Boolean
    suspend fun loginUser(userLogin: UserLogin): Boolean
}