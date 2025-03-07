package com.k2infosoft.Todo.repository

import com.k2infosoft.Todo.entity.Todo
import org.springframework.data.jpa.repository.JpaRepository

interface TodoRepository: JpaRepository<Todo, Long>