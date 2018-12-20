package com.desafio.auth

import io.javalin.Context
import io.javalin.Handler
import io.javalin.security.Role

object Auth {

    fun accessManager(handler: Handler, ctx: Context, permittedRoles: List<Role>) {
        when {
            permittedRoles.contains(ApiRole.ANYONE) -> handler.handle(ctx)
            ctx.userRoles.any { it in permittedRoles } -> handler.handle(ctx)
            else -> ctx.status(401).json("Unauthorized")
        }
    }

    private val userRoleMap = hashMapOf(
        Pair("alice", "weak-password") to listOf(ApiRole.USER_READ),
        Pair("bob", "better-password") to listOf(ApiRole.USER_READ, ApiRole.USER_WRITE)
    )

    private val Context.userRoles: List<ApiRole>
        get() = this.basicAuthCredentials()?.let { (username, password) ->
            userRoleMap[Pair(username, password)] ?: listOf()
        } ?: listOf()

}