package com.desafio.controller

import com.desafio.Services.UserService
import com.desafio.models.UserDTO
import com.desafio.repositories.UserRepository
import com.desafio.utils.md5
import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder.get
import io.javalin.apibuilder.ApiBuilder.post
import kotlinx.coroutines.runBlocking


class UserController(private val userService: UserService, private val app: Javalin) {

    fun router()
    {
        app.routes(){
            get("/users"){ctx ->
                runBlocking {
                    var users = userService.findAll()
                    ctx.json(users).status(200)
                }
            }

            post("/users"){ ctx ->
                val user = ctx.body<UserDTO>()
                val userCreated = userService.save(user)
                ctx.json(userCreated).status(201)

            }
        }

    }

    /*fun getAllUserIds(ctx: Context) {
        ctx.json(users.keys)
    }

    fun createUser(ctx: Context) {
        val userCreated = ctx.body<User>()
        userCreated.id = randomId()

        val dataCreated = LocalDate.now()

        userCreated.created = dataCreated
        userCreated.modified = dataCreated
        userCreated.last_login = dataCreated

        userCreated.token = randomId()

        users[userCreated.id.toString()] = userCreated
        ctx.json(userCreated).status(200)
    }



    private fun randomId() = UUID.randomUUID().toString()
*/
}