package com.desafio.repositories

import com.desafio.models.User
import com.desafio.models.UserDTO
import org.jetbrains.exposed.sql.transactions.transaction
import org.joda.time.DateTime
import java.util.*

class UserRepository(){

    /*fun findAll() = GlobalScope.async {
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
    }*/

    /*val movie = StarWarsFilm.new {
        name = "The Last Jedi"
        sequelId = 8
        director = "Rian Johnson"
    }*/

    fun save(user: UserDTO): UserDTO {

        var userCreated = transaction {

            var dateNow = DateTime()
            var newToken = UUID.randomUUID().toString()

            User.new {
                name = user.name
                email = user.email
                password = user.password

                created = dateNow
                modified = dateNow
                last_login = dateNow

                token = newToken

            }

        }
        return UserDTO(
            usersId = userCreated.id.value,
            name = userCreated.name,
            email = userCreated.email,
            password =  userCreated.password,
            created = userCreated.created,
            modified = userCreated.modified,
            last_login = userCreated.last_login,
            token = userCreated.token

        )


    }






}