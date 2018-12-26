package com.desafio.repositories

import com.desafio.dto.UserDTO
import com.desafio.models.User
import com.desafio.models.Users
import org.jetbrains.exposed.sql.Transaction
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update
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
                    token = user.token
                    ) }
            }
    }

    fun save(user: UserDTO): User {

        return transaction {

            var dateNow = DateTime()
            var newToken = UUID.randomUUID().toString()

            User.new {
                name = user.name.toString()
                email = user.email.toString()
                password = user.password.toString()

                created = dateNow
                modified = dateNow
                last_login = dateNow

                token = newToken

            }

        }
    }

    fun findByEmail(email: String?): User? {
        return transaction {
            User.find{ Users.email eq email.toString() }.firstOrNull()
        }

    }

    fun findByToken(token: String?): User? {
        return transaction {
            User.find{ Users.token eq token.toString() }.firstOrNull()
        }

    }

    fun findById(userId: Int?): User? {
        return transaction{
            User.find{ Users.id eq userId }.firstOrNull()
        }
    }

    fun updateLastLoginAndToken(user: User){
        return transaction{
            Users.update ({ Users.id eq user.id}){
                it[last_login] = DateTime()
                it[token] = UUID.randomUUID().toString()
            }
        }
    }



}