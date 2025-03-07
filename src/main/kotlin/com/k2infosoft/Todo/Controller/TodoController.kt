package com.k2infosoft.Todo.Controller

import com.k2infosoft.Todo.dto.TodoDto
import com.k2infosoft.Todo.service.TodoService
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/api/v1/todos")
@Tag(name = "Todo V1 API", description = "Sample Todo API")
class TodoController(private val todoService: TodoService) {

    val subscriptionKeyvalue = "B0L7iJ2sytuz4iOM2DpK06pkHdhZEV8t"

    private fun validateSuscriptionKey(@RequestHeader("Ocp-Apim-Subscription-Key") subscriptionKey: String) {
        if (subscriptionKey != subscriptionKeyvalue) {
            throw ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid subscription key")
        }
    }

    @PostMapping
    fun saveTodo(@RequestHeader("Ocp-Apim-Subscription-Key") subscriptionKey: String, @Valid @RequestBody todoDto: TodoDto): ResponseEntity<TodoDto> {
        validateSuscriptionKey(subscriptionKey)
         var savedTodo: ResponseEntity<TodoDto> = todoService.addTodo(todoDto)
        return ResponseEntity<TodoDto>(savedTodo.body, HttpStatus.CREATED)
    }



    @PutMapping("{id}")
    fun updateTodo(@RequestHeader("Ocp-Apim-Subscription-Key") subscriptionKey: String,
                   @PathVariable(value = "id") todoid: Long,
                   @Valid @RequestBody todoDto: TodoDto): ResponseEntity<TodoDto> {
        validateSuscriptionKey(subscriptionKey)
        var updatedTodo: ResponseEntity<TodoDto> = todoService.updateTodo(todoid,todoDto)
         return ResponseEntity<TodoDto>(updatedTodo.body, HttpStatus.OK)
    }

    @GetMapping
    fun getAllTodos(@RequestHeader("Ocp-Apim-Subscription-Key") subscriptionKey: String): ResponseEntity<List<TodoDto>> {
        validateSuscriptionKey(subscriptionKey)
        var todos: ResponseEntity<MutableList<TodoDto>> = todoService.getAllTodos()
        return ResponseEntity<List<TodoDto>>(todos.body, HttpStatus.OK)
    }

    @GetMapping("{id}")
    fun getTodo(@RequestHeader("Ocp-Apim-Subscription-Key") subscriptionKey: String,@PathVariable(value = "id") id: Long): ResponseEntity<TodoDto> {
        validateSuscriptionKey(subscriptionKey)
        var todo: ResponseEntity<TodoDto> = todoService.getTodo(id)
        return ResponseEntity<TodoDto>(todo.body, HttpStatus.OK)
    }

    @DeleteMapping("{id}")
    fun deleteTodo(@RequestHeader("Ocp-Apim-Subscription-Key") subscriptionKey: String,@PathVariable(value = "id") id: Long): ResponseEntity<Void> {
        validateSuscriptionKey(subscriptionKey)
        var deletedTodo: ResponseEntity<Void> = todoService.deleteTodo(id)
        return ResponseEntity<Void>(deletedTodo.body, HttpStatus.OK)
    }

    @GetMapping("/isCompleted/{id}")
    fun isCompleted(@RequestHeader("Ocp-Apim-Subscription-Key") subscriptionKey: String,@PathVariable(value = "id") id: Long): ResponseEntity<TodoDto> {
        validateSuscriptionKey(subscriptionKey)
        var todo: ResponseEntity<TodoDto> = todoService.completeTodo(id)
        return ResponseEntity<TodoDto>(todo.body, HttpStatus.OK)
    }

    @GetMapping("/isInCompleted/{id}")
    fun isInCompleted(@RequestHeader("Ocp-Apim-Subscription-Key") subscriptionKey: String,@PathVariable(value = "id") id: Long): ResponseEntity<TodoDto> {
        validateSuscriptionKey(subscriptionKey)
        var todo: ResponseEntity<TodoDto> = todoService.inCompleteTodo(id)
        return ResponseEntity<TodoDto>(todo.body, HttpStatus.OK)
    }

}