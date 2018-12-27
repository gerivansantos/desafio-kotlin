package com.desafio.config

import com.desafio.controller.LoginController
import com.desafio.services.LoginService
import com.desafio.services.PhoneService
import com.desafio.services.UserService
import com.desafio.controller.UserController
import com.desafio.repositories.PhoneRepository
import com.desafio.repositories.UserRepository
import org.koin.dsl.module.module

object ModuleConfig{
    val myModule = module {
        single { UserController(get())}
        single { LoginController(get())}
        single { UserService(get(), get())}
        single { UserRepository() }
        single { PhoneService(get()) }
        single { PhoneRepository() }
        single { LoginService(get(), get())}
        single { Routes(get(), get()) }
    }
}