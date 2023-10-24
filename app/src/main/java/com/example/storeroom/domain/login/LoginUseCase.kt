package com.example.storeroom.domain.login

import com.example.storeroom.data.login.UserLogin
import javax.inject.Inject

interface LoginUseCase {
    suspend operator fun invoke(userLogin: UserLogin): Boolean
    suspend fun getLoggedInStatus() : Boolean
}

class LoginUseCaseImpl @Inject constructor(
    private val loginRepository: LoginRepository
) : LoginUseCase {

    override suspend fun invoke(userLogin: UserLogin): Boolean {
        return loginRepository(userLogin)
    }

    override suspend fun getLoggedInStatus(): Boolean {
        return loginRepository.getLoggedInStatus()
    }
}
