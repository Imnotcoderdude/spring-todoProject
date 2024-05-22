package io.me.springtodo.controller;

import io.me.springtodo.dto.TodoRequestDto;
import io.me.springtodo.dto.TodoResponseDto;
import io.me.springtodo.repository.Todo;
import io.me.springtodo.repository.TodoRepository;
import io.me.springtodo.service.Todoservice;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class TodoController {
    public final Todoservice todoservice;
    private final TodoRepository todoRepository;

    // 아무것도 없는 상태이더라도 postman 에서 호출을 하게 된다면 200 코드가 나오면서 정상호출이 되었음을 알린다.
    @PostMapping("/todo")
    public ResponseEntity<TodoResponseDto> postTodo(@RequestBody TodoRequestDto dto) {

        Todo todo = todoservice.createTodo(dto);
        TodoResponseDto response = new TodoResponseDto(todo);
        return ResponseEntity.ok().body(response);
    }
    // 경로변수{todoId}로 todoId 받아오기.
    @GetMapping("/todo/{todoId}")
    public ResponseEntity<TodoResponseDto> getTodo(@PathVariable Long todoId) {
        Todo todo = todoservice.getTodo(todoId);
        TodoResponseDto response = new TodoResponseDto(todo);
        return ResponseEntity.ok().body(response);
    }
}
