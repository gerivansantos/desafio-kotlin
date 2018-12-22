package com.desafio.Services

import com.desafio.config.exception.ValidationException
import com.desafio.dto.PhoneDTO
import com.desafio.dto.UserDTO
import com.desafio.models.Phone
import com.desafio.models.User
import com.desafio.repositories.UserRepository
import com.desafio.utils.md5
import org.eclipse.jetty.http.HttpStatus
import org.joda.time.DateTime
import java.util.*
import org.joda.time.Minutes


class UserService(){

    var userRepository: UserRepository = UserRepository()
    var phoneService: PhoneService = PhoneService()

    fun logar(user: UserDTO){
        var userFound: User? = userRepository.findById(user.usersId)

    }

    fun save(user: UserDTO): UserDTO{

        var userFoundByEmail = findByEmail(user.email)

        if(userFoundByEmail != null)
        {
            throw IllegalArgumentException("Email já cadastrado")
        }
        else
        {
            if(user.name.isNullOrBlank()){
                throw IllegalArgumentException("Campo nome obrigatório")
            }
            else if(user.email.isNullOrBlank()){
                throw IllegalArgumentException("Campo email obrigatório")
            }
            else  if(user.password.isNullOrBlank()) {
                throw IllegalArgumentException("Campo password obrigatório")
            }
            else{
                user.password = user.password.toString().md5()
                var userCreated: User = userRepository.save(user)


                user.phones.let {
                    val storedPhones: List<Phone> = phoneService.save(it!!, userCreated)
                    //userCreated.phones  listPhoneToListPhoneDTO(storedPhones)
                }
                return userToUserDTO(userCreated)
            }
        }
    }

    fun findAll(): List<UserDTO> {
        return userRepository.findAll()
    }


    fun findByEmail(email: String?): UserDTO? {
        var userFound: User? = userRepository.findByEmail(email)

        if (userFound == null)
        {
            return null
        }
        else
        {
            return userToUserDTO(userFound)
        }

    }

    fun getUser(userId: Int, token: String?): UserDTO{
        var userFoundToken =  userRepository.findByToken(token)
        if (userFoundToken != null) {
            var userById = userRepository.findById(userId)

            if (userById != null) {

                if(userById.token == token)
                {
                    var dateLastLogin = DateTime(userById.last_login)
                    var dataAtual = DateTime()
                    if(getDateDiffMinutes(dateLastLogin,dataAtual ) > 30)
                    {
                        throw ValidationException("Sessão Invalida", HttpStatus.UNAUTHORIZED_401)
                    }
                    else
                    {
                        return userToUserDTO(userById)
                    }
                }
                else
                {
                    //usuario diferente do token
                    throw ValidationException("Não autorizado", HttpStatus.UNAUTHORIZED_401)
                }

            }
            else
            {
                throw ValidationException("Usuário não encontrado", HttpStatus.NOT_FOUND_404)
            }
        }
        else
        {
            throw ValidationException("Não autorizado", HttpStatus.UNAUTHORIZED_401)
        }
    }

    fun findByToken(token: String?): UserDTO? {
        var userFound: User? = userRepository.findByToken(token)

        if (userFound == null)
        {
            return null
        }
        else
        {
            return userToUserDTO(userFound)
        }

    }


    fun userToUserDTO(user: User?): UserDTO {
        return UserDTO(
            usersId =  user?.id?.value,
            name = user?.name,
            email = user?.email,
            password = user?.password,
            created = user?.created,
            modified = user?.modified,
            last_login = user?.last_login,
            token = user?.token
        )
    }

    private fun listPhoneToListPhoneDTO(phones: List<Phone>): List<PhoneDTO> {
        var listPhoneDTO : ArrayList<PhoneDTO> = ArrayList()
        phones.map { phone ->
            listPhoneDTO.add(PhoneDTO(
                ddd = phone.ddd,
                number = phone.number
            ))
        }
        return listPhoneDTO
    }

    fun getDateDiffMinutes(dateI: DateTime, dateF: DateTime): Int {
        return Minutes.minutesBetween(dateI, dateF).minutes
    }




}