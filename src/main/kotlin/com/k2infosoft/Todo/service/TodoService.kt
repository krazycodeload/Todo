package com.k2infosoft.Todo.service

import com.k2infosoft.Todo.dto.TodoDto
import org.springframework.http.ResponseEntity


interface TodoService {
    fun addTodo(todoDto: TodoDto): ResponseEntity<TodoDto>

    fun getTodo(id: Long): ResponseEntity<TodoDto>

    fun getAllTodos(): ResponseEntity<MutableList<TodoDto>>

    fun updateTodo(todoDto: TodoDto, id: Long): ResponseEntity<TodoDto>

    fun deleteTodo(id: Long): ResponseEntity<Void>

    fun completeTodo(id: Long): ResponseEntity<TodoDto>

    fun inCompleteTodo(id: Long): ResponseEntity<TodoDto>
}