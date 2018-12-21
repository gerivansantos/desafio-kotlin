package com.desafio.utils

import io.javalin.json.JavalinJackson
import java.math.BigInteger
import java.security.MessageDigest

fun String.md5(): String {
    val md = MessageDigest.getInstance("MD5")
    return BigInteger(1, md.digest(toByteArray())).toString(16).padStart(32, '0')
}


fun fail(message: String): Nothing {
    val throwable = Throwable(message)
    Thread.setDefaultUncaughtExceptionHandler{ t, e -> System.err.println(e.message)}
    throw throwable
}

fun <T> String.toJsonObject(valueType: Class<T>): T {
    return JavalinJackson.getObjectMapper().readValue(this, valueType)
}
