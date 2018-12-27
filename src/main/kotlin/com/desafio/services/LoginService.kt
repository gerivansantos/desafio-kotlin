package com.desafio.services

import com.desafio.config.exception.ValidationException
import com.desafio.dto.LoginDTO
import com.desafio.dto.UserDTO
import com.desafio.repositories.UserRepository
import org.eclipse.jetty.http.HttpStatus

class LoginService(private val userRepository: UserRepository,
                   private val userService: UserService){



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