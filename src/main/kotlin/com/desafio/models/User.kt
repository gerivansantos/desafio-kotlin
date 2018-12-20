package com.desafio.models

import org.jetbrains.exposed.dao.*
import org.joda.time.DateTime
import java.util.*


data class UserDTO( var usersId: Int? = null,
                    var name: String,
                    var email: String,
                    var password: String,
                    var created: DateTime? = null,
                    var modified: DateTime? = null,
                    var last_login: DateTime? = null,
                    var token: String? = null)


// Class
class User(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<User>(Users)
    var name by Users.name
    var email by Users.email
    var password by Users.password
    var created by Users.created
    var modified by Users. modified
    var last_login by Users.last_login
    var token by Users.token
}

// Mappeamento Banco
object Users: IntIdTable(){
    var name = varchar("name", 255)
    var email = varchar("email", 100)
    var password = varchar("password", 50)
    var created = datetime("created")
    var modified = datetime("modified")
    var last_login = datetime("last_login")
    var token = varchar("token", 50)
}

