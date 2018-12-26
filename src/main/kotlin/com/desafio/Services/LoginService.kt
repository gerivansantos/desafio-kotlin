package com.desafio.Services

import com.desafio.config.exception.ValidationException
import com.desafio.dto.LoginDTO
import com.desafio.dto.UserDTO
import com.desafio.repositories.UserRepository
import org.eclipse.jetty.http.HttpStatus
import org.joda.time.DateTime
import java.util.*

class LoginService(){

    val userRepository: UserRepository = UserRepository()
    val userService: UserService = UserService()

    fun login(login: LoginDTO): UserDTO {

        if(login.email.isNullOrBlank()){
            throw IllegalArgumentException("Campo login obrigatório")
        }
        else if (login.password.isNullOrBlank())
        {
            throw IllegalArgumentException("Campo password obrigatório")
        }
        else
        {
           var userByEmail = userRepository.findByEmail(login.email)

            if(userByEmail != null)
            {
                if(userByEmail.password == login.password)
                {
                    userRepository.updateLastLoginAndToken(userByEmail)
                    userByEmail = userRepository.findByEmail(login.email)

                    return userService.userToUserDTO(userByEmail)
                }
                else
                {
                    throw ValidationException("Usuário e/ou senha inválidos", HttpStatus.UNAUTHORIZED_401)
                }
            }
            else
            {
                throw ValidationException("Usuário e/ou senha inválidos", HttpStatus.BAD_REQUEST_400)
            }
        }


    }
}