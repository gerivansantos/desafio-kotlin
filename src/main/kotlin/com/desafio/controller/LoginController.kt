package com.desafio.controller

import com.desafio.services.LoginService
import com.desafio.dto.LoginDTO
import com.desafio.dto.UserDTO

class LoginController(private val loginService: LoginService) {

    fun logar(login: LoginDTO): UserDTO {
        return loginService.login(login)
    }
}