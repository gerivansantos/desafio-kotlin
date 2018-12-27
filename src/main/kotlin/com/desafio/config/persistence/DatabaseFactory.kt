package com.desafio.config.persistence

import com.desafio.constants.ApplicationConstants.PASSWORD_DATABASE
import com.desafio.constants.ApplicationConstants.URL_DATABASE
import com.desafio.constants.ApplicationConstants.USER_DATABASE
import com.desafio.models.Phones
import com.desafio.models.Users
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.deleteAll
import org.jetbrains.exposed.sql.transactions.transaction

object DatabaseFactory{

    fun init(){
        Database.connect(URL_DATABASE,
            driver = "org.postgresql.Driver",
            user = USER_DATABASE,
            password = PASSWORD_DATABASE)
    }

    fun drop(){
        transaction {
            Users.deleteAll()
            Phones.deleteAll()
        }
    }

}