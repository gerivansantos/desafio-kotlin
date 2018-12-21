package com.desafio.Services

import com.desafio.models.User
import com.desafio.models.UserDTO
import com.desafio.repositories.UserRepository
import com.desafio.utils.fail
import com.desafio.utils.md5
import io.javalin.HttpResponseException
import org.jetbrains.exposed.sql.transactions.transaction

class UserService(){

    var userRepository: UserRepository = UserRepository()

    fun save(user: UserDTO): UserDTO{

        if(user.name.isNullOrBlank()){
            throw IllegalArgumentException("Campo nome obrigatório")
        }
        else if(user.email.isNullOrBlank()){
            throw IllegalArgumentException("Campo email obrigatório")
        }
        else  if(user.password.isNullOrBlank()) {
            throw IllegalArgumentException("Campo paswword obrigatório")
        }
        else{
            user.password = user.password.toString().md5()
            return userRepository.save(user)
        }
    }

    fun findAll(): List<UserDTO> {
        return userRepository.findAll()
    }


}