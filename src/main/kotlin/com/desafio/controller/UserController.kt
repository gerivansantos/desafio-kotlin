package com.desafio.controller

import com.desafio.models.User
import com.desafio.models.UserDTO
import com.desafio.repositories.UserRepository
import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder.get
import io.javalin.apibuilder.ApiBuilder.post
import kotlinx.coroutines.runBlocking


class UserController(private val userRepository: UserRepository, private val app: Javalin) {

    fun router()
    {
        app.routes(){
//            get("/users"){ctx ->
//                runBlocking {
//                    ctx.json(userRepository.findAll().await())
//                }
//            }

            post("/users"){ ctx ->
                val user = ctx.body<UserDTO>()
                val userCreated = userRepository.save(user)
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