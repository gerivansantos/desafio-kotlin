package com.desafio.config.exception

import com.desafio.config.exception.response.BadRequestErrorResponse
import org.eclipse.jetty.http.HttpStatus

class ValidationException(message:String, status:Int = HttpStatus.INTERNAL_SERVER_ERROR_500): RuntimeException(message){

    val error: BadRequestErrorResponse = BadRequestErrorResponse(message)
    val statusCode: Int = status
}
