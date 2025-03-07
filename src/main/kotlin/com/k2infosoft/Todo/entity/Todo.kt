package com.k2infosoft.Todo.entity

import com.k2infosoft.Todo.dto.TodoDto
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.validation.constraints.NotEmpty
import java.time.LocalDateTime

@Entity
data class Todo(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @NotEmpty(message = "title must not be blank")
    var title: String,
    @NotEmpty(message = "description must not be blank")
    var description: String,
    var completed: Boolean,
    val createdDate: String = LocalDateTime.now().toString(),
    val updatedDate: String = LocalDateTime.now().toString()
){
    fun toDTO(): TodoDto = TodoDto(title, description, completed)
}
