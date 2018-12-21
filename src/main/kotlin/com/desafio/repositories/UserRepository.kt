package com.desafio.repositories

import com.desafio.models.User
import com.desafio.models.UserDTO
import com.desafio.models.Users
import org.jetbrains.exposed.sql.transactions.transaction
import org.joda.time.DateTime
import java.util.*

class UserRepository(){

    fun findAll(): List<UserDTO> {
        return transaction {
                User.all().map { user -> UserDTO(
                    usersId = user.id.value,
                    name = user.name,
                    email = user.email,
                    password =  user.password,
                    created = user.created,
                    modified = user.modified,
                    last_login = user.last_login,
                    token = user.token ) }
            }
}

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