package com.desafio.controller

import com.desafio.JavalinApp
import io.javalin.Javalin
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

    fun `testar se o root da api responde 200`() {
        val response = khttp.get(url = "$url/users")
        assertEquals(200, response.statusCode)
    }

}