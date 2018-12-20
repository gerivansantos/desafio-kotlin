package com.desafio.models

import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.IntIdTable
import org.joda.time.DateTime

/*
data class PhDTO(var usersId: Int? = null,
                   var name: String,
                   var email: String,
                   var password: String,
                   var created: DateTime? = null,
                   var modified: DateTime? = null,
                   var last_login: DateTime? = null,
                   var token: String? = null)
*/


// Class
class Phone(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Phone>(Users)
    var name by Users.name
    var email by Users.email
    var password by Users.password
    var created by Users.created
    var modified by Users. modified
    var last_login by Users.last_login
    var token by Users.token
}

// Mappeamento Banco
object Phones: IntIdTable(){
    var number = varchar("name", 255)
    var ddd = varchar("email", 100)
}