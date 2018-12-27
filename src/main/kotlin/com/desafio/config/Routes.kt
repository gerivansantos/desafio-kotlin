package com.desafio.config

import com.desafio.controller.LoginController
import com.desafio.controller.UserController
import com.desafio.dto.LoginDTO
import com.desafio.dto.UserDTO
import com.desafio.utils.md5
import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder.*

class Routes(private val userController: UserController,
             private val loginController: LoginController){
    fun register(app: Javalin) {
        app.routes {
            path("users") {
                post{ctx ->
                    val user = ctx.body<UserDTO>()
                    val userCreated = userController.createUser(user)
                    ctx.json(userCreated).status(201)}
                get{ctx ->
                    val users = userController.findAll()
                    ctx.json(users).status(200)
                }
                get("/:user-id") { ctx ->
                    val userId = ctx.pathParam("user-id").toInt()
                    val tokenAuthorization = ctx.header("Authorization")?.replace("Bearer ", "")
                    val userFound: UserDTO = userController.getUser(userId, tokenAuthorization)
                    ctx.json(userFound).status(200)
                }
            }
            path("login"){
                post{ ctx ->
                    val login = ctx.body<LoginDTO>()
                    login.password = login.password.toString().md5()
                    val loginStatus = loginController.logar(login)
                    ctx.json(loginStatus).status(200)
                }
            }

        }
    }
}




