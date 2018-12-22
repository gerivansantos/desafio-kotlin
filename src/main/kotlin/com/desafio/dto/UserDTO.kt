package com.desafio.dto

import org.joda.time.DateTime

data class UserDTO(var usersId: Int? = null,
                   var name: String? = null,
                   var email: String? = null,
                   var password: String? = null,
                   var created: DateTime? = null,
                   var modified: DateTime? = null,
                   var last_login: DateTime? = null,
                   var token: String? = null,
                   var phones: List<(PhoneDTO)>? = listOf()
)