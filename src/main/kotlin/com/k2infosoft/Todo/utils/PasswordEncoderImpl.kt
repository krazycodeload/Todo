package com.k2infosoft.Todo.utils

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder


object PasswordEncoderImpl {
    @JvmStatic
    fun main(args: Array<String>) {
        val passwordEncoder: PasswordEncoder = BCryptPasswordEncoder()

        println(passwordEncoder.encode("kaushal"))

        println(passwordEncoder.encode("admin"))
    }
}