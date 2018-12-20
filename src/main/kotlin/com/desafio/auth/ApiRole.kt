package com.desafio.auth

import io.javalin.security.Role

enum class ApiRole : Role {
                            ANYONE,
                            USER_READ,
                            USER_WRITE }
