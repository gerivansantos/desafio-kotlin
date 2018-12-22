package com.desafio.controller

import com.desafio.Services.LoginService
import com.desafio.dto.LoginDTO
import com.desafio.utils.md5
import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder.post

class LoginController(private val loginService: LoginService,private val app: Javalin) {
    fun router()
    {
        app.routes(){

            post("/login") { ctx ->
                val login = ctx.body<LoginDTO>()
                login.password = login.password.toString().md5()
                val loginStatus = loginService.login(login)
                ctx.json(loginStatus).status(200)
            }
        }

    }
}