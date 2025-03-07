package com.k2infosoft.Todo.dto

import com.k2infosoft.Todo.entity.Todo

data class TodoDto(val title: String, val description: String, val completed: Boolean){
    fun toEntity(): Todo = Todo(title = title, description = description,completed = completed)
}