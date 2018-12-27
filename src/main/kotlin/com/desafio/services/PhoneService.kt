package com.desafio.services

import com.desafio.dto.PhoneDTO
import com.desafio.models.Phone
import com.desafio.models.User
import com.desafio.repositories.PhoneRepository

class PhoneService(private val phoneRepository: PhoneRepository){



    fun save(phones: List<PhoneDTO>, userPhone: User): List<Phone> {
       return phoneRepository.save(phones, userPhone)
    }

    fun findAll(): List<PhoneDTO> {
        return phoneRepository.findAll()
    }
}