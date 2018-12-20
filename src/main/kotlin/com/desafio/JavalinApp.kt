package com.desafio

import com.desafio.controller.UserController
import com.desafio.models.User
import com.desafio.repositories.UserRepository
import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import java.lang.Exception

class JavalinApp(private val port: Int) {

    //val controller = UserController(users)

    fun init() : Javalin {

        Database.connect("jdbc:postgresql://localhost:5432/desafio-concrete",
            driver = "org.postgresql.Driver",
            user = "postgres",
            password = "postgres")


        val app = Javalin.create().apply {
            exception(Exception::class.java) { e, ctx -> e.printStackTrace()}
            error(404){ctx -> ctx.json("not found")}
        }.start(port)

        app.get("/") { ctx -> ctx.json(mapOf("message" to "ola, mundo")) }

        val userRepository = UserRepository()
        val users = UserController(userRepository,app)
        users.router()


        /*app.routes{
            path("api") {
                path("users") {
                   // get(UserController::getAllUserIds)
                    //post(UserController::createUser)
                }
            }

        }*/

        return app
    }

}

fun main(args: Array<String>){
    JavalinApp(7000).init()
}