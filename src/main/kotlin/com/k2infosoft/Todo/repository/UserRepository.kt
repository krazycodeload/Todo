package com.k2infosoft.Todo.repository

import com.k2infosoft.Todo.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional


interface UserRepository : JpaRepository<User, Long> {
    fun findByUsername(username: String?): Optional<User>

    fun existsByEmail(email: String): Boolean

    fun findByUsernameOrEmail(username: String, email: String): Optional<User>
}
