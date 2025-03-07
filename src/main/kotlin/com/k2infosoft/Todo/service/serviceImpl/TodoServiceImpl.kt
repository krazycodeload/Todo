package com.k2infosoft.Todo.service.serviceImpl

import com.k2infosoft.Todo.dto.TodoDto
import com.k2infosoft.Todo.entity.Todo
import com.k2infosoft.Todo.exception.ResourceNotFoundException
import com.k2infosoft.Todo.repository.TodoRepository
import com.k2infosoft.Todo.service.TodoService
import jakarta.transaction.Transactional
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service


@Service
class TodoServiceImpl(private val todoRepository: TodoRepository) : TodoService {

    @Transactional
    override fun addTodo(todoDto: TodoDto): ResponseEntity<TodoDto> {
//        var todo = todoDto.toEntity()
//        todoRepository.save<Todo>(todo)
//        return ResponseEntity.ok(todo.toDTO())
        var user = todoDto.toEntity()
        return ResponseEntity.ok(todoRepository.save(user).toDTO())
    }
    @Transactional
    override fun getTodo(id: Long): ResponseEntity<TodoDto> {
        return todoRepository.findById(id).map { ResponseEntity.ok(it.toDTO()) }.orElse(ResponseEntity.notFound().build())
    }
    @Transactional
    override fun getAllTodos(): ResponseEntity<MutableList<TodoDto>> {
        return ResponseEntity.ok(todoRepository.findAll().map { it.toDTO() }.toMutableList())
    }
    @Transactional
    override fun updateTodo(
        id: Long,
        todoDto: TodoDto
    ): ResponseEntity<TodoDto> {
//        return todoRepository.findById(id).map {
//            var todo = todoDto.toEntity().copy(title = it.title, description = it.description, completed = it.completed)
//            todoRepository.save<Todo>(todo)
//            ResponseEntity.ok(todo.toDTO())
//        }.orElse(ResponseEntity.notFound().build())
        val todo = todoRepository.findById(id).orElseThrow {
            IllegalArgumentException("Todo not found with id $id")
        }
        todo.apply { title = todoDto.title; description = todoDto.description; completed = todoDto.completed; }
        return ResponseEntity.ok(todoRepository.save(todo).toDTO())
    }
    @Transactional
    override fun deleteTodo(id: Long): ResponseEntity<Void> {
       return todoRepository.findById(id).map { it ->
           todoRepository.delete(it)
           ResponseEntity<Void>(HttpStatus.OK)
       }.orElse(ResponseEntity.notFound().build())
    }

    @Transactional
    override fun completeTodo(id: Long): ResponseEntity<TodoDto> {
        return todoRepository.findById(id).map {
            var todo = it.copy(completed = true)
            todoRepository.save<Todo>(todo)
            ResponseEntity.ok(todo.toDTO())
        }.orElseThrow { ResourceNotFoundException("Todo not found with id : $id") }
    }

    @Transactional
    override fun inCompleteTodo(id: Long): ResponseEntity<TodoDto> {
        return todoRepository.findById(id).map {
            var todo = it.copy(completed = false)
            todoRepository.save<Todo>(todo)
            ResponseEntity.ok(todo.toDTO())
        }.orElseThrow { ResourceNotFoundException("Todo not found with id : $id") }
    }

}
