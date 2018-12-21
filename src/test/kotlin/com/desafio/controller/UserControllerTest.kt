package com.desafio.controller

import com.desafio.JavalinApp
import com.desafio.models.UserDTO
import io.javalin.Javalin
import io.javalin.json.JavalinJackson
import junit.framework.TestCase

class UserControllerTest: TestCase() {

    private lateinit var app: Javalin
    private val url = "http://localhost:7000"

    override fun setUp() {
        app = JavalinApp(port = 7000).init()
    }

    override fun tearDown(){
        app.stop()
    }


    fun `testar se o usuario é cadastrado e o retorno é 201`() {
        val user = UserDTO(name = "User Teste", email = "userteste@gmail.com", password = "teste123")
        val body = JavalinJackson.getObjectMapper().writeValueAsString(user)
        val response = khttp.get(url = "$url/users", data = body)
        assertEquals(201, response.statusCode)
    }

/*
    fun `testar se está sendo validado campos obrigatorio`() {
        val user = UserDTO(name = "User Teste", email = "userteste@gmail.com", password = "teste123")
        val body = JavalinJackson.getObjectMapper().writeValueAsString(user)
        val response = khttp.get(url = "$url/users", data = body)
        assertEquals(201, response.statusCode)
    }
*/

}