package com.desafio.controller

import com.desafio.Services.UserService
import com.desafio.dto.UserDTO
import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder.get
import io.javalin.apibuilder.ApiBuilder.post
import kotlinx.coroutines.runBlocking


class UserController(private val userService: UserService/*, private val phoneService: PhoneService*/, private val app: Javalin) {

    fun router()
    {
        app.routes(){
            get("/users"){ctx ->
                runBlocking {
                    var users = userService.findAll()
                    ctx.json(users).status(200)
                }
            }

            get("/users/:user-id"){ ctx ->
                runBlocking {
                    var userId = ctx.pathParam("user-id").toInt()
                    var tokenAuthorization = ctx.header("Authorization")?.replace("Bearer ","")


                    var userFound: UserDTO = userService.getUser(userId, tokenAuthorization)
                    //var users = userService.findAll()
                    ctx.json(userFound).status(200)
                }
            }

            post("/users"){ ctx ->
                val user = ctx.body<UserDTO>()
                val userCreated = userService.save(user)
                ctx.json(userCreated).status(201)

            }
        }

    }

}