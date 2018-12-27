package com.desafio

import com.desafio.config.AppConfig
import com.desafio.config.ModuleConfig
import com.desafio.config.Routes
import com.desafio.config.configureMapper
import com.desafio.config.exception.ExceptionHandler
import com.desafio.constants.ApplicationConstants.DEFAUL_SERVER_PORT
import io.javalin.Javalin
import org.jetbrains.exposed.sql.Database
import org.koin.standalone.KoinComponent
import org.koin.standalone.StandAloneContext
import org.koin.standalone.inject
import java.lang.Exception

fun main(args: Array<String>){
    AppConfig(DEFAUL_SERVER_PORT).setup().start()
}