package com.desafio.repositories

import com.desafio.dto.PhoneDTO
import com.desafio.models.Phone
import com.desafio.models.User
import org.jetbrains.exposed.sql.transactions.transaction

class PhoneRepository {

    fun findAll(): List<PhoneDTO> {
        return transaction {
            Phone.all().map { phone -> PhoneDTO(
                number = phone.number,
                ddd = phone.ddd

            ) }
        }
    }

    fun save(phones: List<PhoneDTO>, userPhone: User): List<Phone>{

        return transaction {
            phones.map { phone ->
                Phone.new {
                    ddd = phone.ddd
                    number = phone.number
                    user = userPhone
                }
            }
        }





    }
}