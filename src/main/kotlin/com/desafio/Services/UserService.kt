package com.desafio.Services

import com.desafio.models.UserDTO
import com.desafio.repositories.UserRepository
import com.desafio.utils.fail
import com.desafio.utils.md5

class UserService(){

    lateinit var userRepository: UserRepository

    fun save(user: UserDTO): UserDTO{

        if(user.name.isNullOrBlank()){
            fail("Campo nome obrigatório")
        }
        else if(user.email.isNullOrBlank()){
            fail("Campo email obrigatório")
        }
        else  if(user.password.isNullOrBlank()) {
            fail("Campo password obrigatório")
        }
        else{
            user.password = user.password.md5()
            return userRepository.save(user)
        }
    }


}