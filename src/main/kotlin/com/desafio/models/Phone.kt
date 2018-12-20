package com.desafio.models

data class Phone(var id: String? = null,
                var number: String,
                var ddd: String)

var phones = hashMapOf(
    "dc16aa15-61e4-41c7-b913-be5266d5e32c" to Phone("dc16aa15-61e4-41c7-b913-be5266d5e32c", "995784030", "81"),
    "b8e54ab0-fbd8-4641-a648-3d0954fe9b0f" to Phone("b8e54ab0-fbd8-4641-a648-3d0954fe9b0f", "33610320", "81"))