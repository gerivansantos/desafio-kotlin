package com.desafio.repositories

import com.desafio.models.User
import com.desafio.models.Users
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.sql.Query
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.statements.InsertStatement
import org.jetbrains.exposed.sql.transactions.transaction
import org.joda.time.DateTime
import java.util.*

class UserRepository(){

    fun findAll() = GlobalScope.async {
        transaction {
            Users.selectAll().map {
                User(
                    it[Users.usersId],
                    it[Users.name],
                    it[Users.email],
                    it[Users.password],
                    it[Users.created],
                    it[Users.modified],
                    it[Users.last_login],
                    it[Users.token]
                )
            }
        }
    }

    /*val movie = StarWarsFilm.new {
        name = "The Last Jedi"
        sequelId = 8
        director = "Rian Johnson"
    }*/

    fun save(user: User): Int {

        val id = transaction {
            Users.insertAndGetId {
                it[Users.name] = user.name
                it[Users.email] = user.email
                it[Users.password] = user.password
                it[Users.created] = DateTime()
                it[Users.modified] = DateTime()
                it[Users.last_login] = DateTime()
                it[Users.token] = UUID.randomUUID().toString()
            }
        }

        return id.value


    }

    fun selectById(id: Int){

        val query: Query = Users.select{ Users.usersId eq id}

        println(query)

    }





}