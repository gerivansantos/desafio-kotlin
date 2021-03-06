package com.desafio.config.exception

import com.desafio.config.exception.response.BadRequestErrorResponse
import io.javalin.BadRequestResponse
import io.javalin.HttpResponseException
import io.javalin.Javalin
import org.eclipse.jetty.http.HttpStatus

class ErrorResponse : HashMap<String,  Any>()

object ExceptionHandler {
    fun register(app: Javalin){
        app.exception(Exception::class.java){exception, ctx ->
            exception.printStackTrace()
            val error = ErrorResponse()
            error["messagem"] = listOf(exception.message)
            ctx.json(error).status(HttpStatus.INTERNAL_SERVER_ERROR_500)
        }
        app.exception(ValidationException::class.java) { exception, ctx ->
            ctx.json(exception.error).status(exception.statusCode)
        }
    }
}


