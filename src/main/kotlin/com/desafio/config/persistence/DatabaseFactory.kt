package com.desafio.config.persistence

import com.desafio.constants.ApplicationConstants.PASSWORD_DATABASE
import com.desafio.constants.ApplicationConstants.URL_DATABASE
import com.desafio.constants.ApplicationConstants.USER_DATABASE
import org.jetbrains.exposed.sql.Database

object DatabaseFactory{

    fun init(){
        Database.connect(URL_DATABASE,
            driver = "org.postgresql.Driver",
            user = USER_DATABASE,
            password = PASSWORD_DATABASE)
    }

}