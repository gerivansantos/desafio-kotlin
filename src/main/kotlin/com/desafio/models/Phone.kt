package com.desafio.models

import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.IntIdTable

// Mappeamento Banco
object Phones: IntIdTable(){
    var user = reference("userid", Users)
    var number = varchar("number", 9)
    var ddd = varchar("ddd", 3)
}

// Class
class Phone(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Phone>(Phones)

    var user by User referencedOn  Phones.user
    var number by Phones.number
    var ddd by Phones.ddd
}

