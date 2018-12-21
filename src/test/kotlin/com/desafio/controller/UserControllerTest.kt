package com.desafio.controller

import com.desafio.JavalinApp
import com.desafio.models.UserDTO
import com.desafio.utils.toJsonObject
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


    fun `testar se está sendo validado o campos obrigatorio nome`() {
        val user = UserDTO( email = "userteste@gmail.com", password = "teste123")
        val body = JavalinJackson.getObjectMapper().writeValueAsString(user)
        val response = khttp.get(url = "$url/users", data = body)
        assertEquals(500, response.statusCode)
    }

    fun `testar se está sendo validado o campos obrigatorio email`() {
        val user = UserDTO(name = "User Teste", password = "teste123")
        val body = JavalinJackson.getObjectMapper().writeValueAsString(user)
        val response = khttp.get(url = "$url/users", data = body)
        assertEquals(500, response.statusCode)
    }

    fun `testar se está sendo validado o campos obrigatorio password`() {
        val user = UserDTO(name = "User Teste", email = "userteste@gmail.com")
        val body = JavalinJackson.getObjectMapper().writeValueAsString(user)
        val response = khttp.get(url = "$url/users", data = body)
        assertEquals(500, response.statusCode)
    }

    fun `testar se o retorno do cadastro de usuario retorna todos os campos obrigatorios`() {
        val user = UserDTO(name = "User Teste", email = "userteste@gmail.com", password = "teste123")
        val body = JavalinJackson.getObjectMapper().writeValueAsString(user)
        val response = khttp.get(url = "$url/users", data = body)
        val userResponse = response.text.toJsonObject(UserDTO::class.java)

        assertEquals(201, response.statusCode)
        assertNotNull(userResponse.usersId)
        assertNotNull(userResponse.name)
        assertNotNull(userResponse.password)
        assertNotNull(userResponse.email)
        assertNotNull(userResponse.created)
        assertNotNull(userResponse.modified)
        assertNotNull(userResponse.last_login)
        assertNotNull(userResponse.token)
    }

    fun `testar se o findAll retorna o status 200`() {
        //val user = UserDTO(name = "User Teste", email = "userteste@gmail.com")
        //val body = JavalinJackson.getObjectMapper().writeValueAsString(user)
        val response = khttp.get(url = "$url/users")
        assertEquals(200, response.statusCode)
    }


}