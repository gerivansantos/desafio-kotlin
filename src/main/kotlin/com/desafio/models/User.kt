package com.desafio.models

import org.jetbrains.exposed.dao.*
import org.joda.time.DateTime

data class UserDTO( var usersId: Int? = null,
                    var name: String? = null,
                    var email: String? = null,
                    var password: String? = null,
                    var created: DateTime? = null,
                    var modified: DateTime? = null,
                    var last_login: DateTime? = null,
                    var token: String? = null)

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



