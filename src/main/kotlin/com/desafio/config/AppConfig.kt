package com.desafio.config

import com.desafio.config.exception.ExceptionHandler
import com.desafio.config.persistence.DatabaseFactory
import io.javalin.Javalin
import org.koin.standalone.KoinComponent
import org.koin.standalone.StandAloneContext
import org.koin.standalone.inject
import java.lang.Exception

class AppConfig(private val port: Int) : KoinComponent{

    private val routes: Routes by inject()

    fun setup():Javalin{
        StandAloneContext.startKoin(listOf(ModuleConfig.myModule))
        configureMapper()

        DatabaseFactory.init()

        val app = Javalin.create()
            .apply {
                exception(Exception::class.java) {
                        e, ctx -> e.printStackTrace()
                }
                error(404){
                        ctx -> ctx.json("not found")
                }
            }.start(port)
        routes.register(app)

        ExceptionHandler.register(app)

        return app
    }

}