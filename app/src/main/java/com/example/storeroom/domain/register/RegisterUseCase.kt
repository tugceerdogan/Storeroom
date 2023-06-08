package com.example.storeroom.domain.register

import com.example.storeroom.data.register.UserRegister
import javax.inject.Inject

interface RegisterUseCase {
    suspend operator fun invoke(userRegister: UserRegister): Boolean
}

class RegisterUseCaseImpl @Inject constructor(
    private val registerRepository: RegisterRepository
) : RegisterUseCase {

    override suspend fun invoke(userRegister: UserRegister): Boolean {
        return registerRepository(userRegister)
    }
}
