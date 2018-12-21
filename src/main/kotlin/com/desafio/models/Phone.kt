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

// Mappeamento Banco
object Phones: IntIdTable(){
    var user = reference("user", Users).nullable()
    var number = varchar("name", 255)
    var ddd = varchar("email", 100)
}

// Class
class Phone(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Phone>(Phones)

    var user by User optionalReferencedOn  Phones.user
    var number by Phones.number
    var ddd by Phones.ddd
}

