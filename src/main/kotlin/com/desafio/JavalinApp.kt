@file:JvmName("AppStart")
package com.desafio

import com.desafio.config.AppConfig
import com.desafio.constants.ApplicationConstants.DEFAUL_SERVER_PORT

class JavalinApp{
    companion object {
        @JvmStatic
        fun main(args: Array<String>){
            AppConfig(DEFAUL_SERVER_PORT).setup().start()
        }
    }
}
