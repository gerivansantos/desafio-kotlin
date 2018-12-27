package com.desafio.controller

import com.desafio.services.UserService
import com.desafio.dto.UserDTO


class UserController(private val userService: UserService) {

    fun findAll(): List<UserDTO> {
        return userService.findAll()
    }

    fun getUser(userId: Int, token: String?): UserDTO {
        return userService.getUser(userId, token)
    }

    fun createUser(user: UserDTO): UserDTO {
        return userService.save(user)
    }

}