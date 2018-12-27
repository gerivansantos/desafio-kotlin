package com.desafio.config

import com.desafio.config.exception.ExceptionHandler
import com.desafio.config.persistence.DatabaseFactory
import com.desafio.constants.ApplicationConstants
import io.javalin.Javalin
import io.javalin.JavalinEvent
import org.koin.standalone.KoinComponent
import org.koin.standalone.StandAloneContext
import org.koin.standalone.StandAloneContext.stopKoin
import org.koin.standalone.getProperty
import org.koin.standalone.inject
import java.lang.Exception

class AppConfig(private val port: Int) : KoinComponent{

    private val routes: Routes by inject()

    fun setup():Javalin{
        StandAloneContext.startKoin(listOf(ModuleConfig.myModule))
        configureMapper()

        DatabaseFactory.init()
        DatabaseFactory.drop()

        /*val app = Javalin.create().apply {
                exception(Exception::class.java) {
                        e, ctx -> e.printStackTrace()
                }
                error(404){
                        ctx -> ctx.json("not found")
                }
            }.start(port)*/

        val app = Javalin.create().apply {
            port(getProperty("server_port", ApplicationConstants.DEFAUL_SERVER_PORT))
        }.event(JavalinEvent.SERVER_STOPPING) {
            stopKoin()
            DatabaseFactory.drop()
        }.start()

        routes.register(app)
        ExceptionHandler.register(app)

        return app
    }

}