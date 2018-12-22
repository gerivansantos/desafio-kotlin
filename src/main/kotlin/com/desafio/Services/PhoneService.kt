package com.desafio.Services

import com.desafio.dto.PhoneDTO
import com.desafio.models.Phone
import com.desafio.models.User
import com.desafio.repositories.PhoneRepository

class PhoneService {
    var phoneRepository: PhoneRepository = PhoneRepository()

    fun save(phones: List<PhoneDTO>, userPhone: User): List<Phone> {
       return phoneRepository.save(phones, userPhone)
    }

    fun findAll(): List<PhoneDTO> {
        return phoneRepository.findAll()
    }
}