package com.desafio.models

import org.jetbrains.exposed.dao.IntIdTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table
import org.joda.time.DateTime

data class User(var usersId: Int? = null,
                var name: String,
                var email: String,
                var password: String,
                var created: DateTime? = null,
                var modified: DateTime? = null,
                var last_login: DateTime? = null,
                var token: String? = null)

object Users : IntIdTable() {

    var usersId: Column<Int> = integer("id").primaryKey().autoIncrement()
    val name: Column<String> = varchar("name", 255)
    val email: Column<String> = varchar("email", 100)
    val password: Column<String> = varchar("password", 50)
    val created: Column<DateTime> = datetime("created")
    val modified: Column<DateTime> = datetime("modified")
    val last_login: Column<DateTime> = datetime("last_login")
    val token: Column<String> = varchar("token", 50)
}



/*
var users = hashMapOf(
    "dc16aa15-61e4-41c7-b913-be5266d5e32c" to User("dc16aa15-61e4-41c7-b913-be5266d5e32c", "Kleber", "kleber@gmail.com", "1234",listOf(Phone(UUID.randomUUID().toString(), "33610320", "81")), LocalDate.now(), LocalDate.now(), LocalDate.now(), UUID.randomUUID().toString() ),
    "b8e54ab0-fbd8-4641-a648-3d0954fe9b0f" to User("b8e54ab0-fbd8-4641-a648-3d0954fe9b0f", "Caio", "caio@gmail.com", "1234", listOf(Phone(UUID.randomUUID().toString(), "33610320", "81")), LocalDate.now(), LocalDate.now(), LocalDate.now(), UUID.randomUUID().toString()))*/
