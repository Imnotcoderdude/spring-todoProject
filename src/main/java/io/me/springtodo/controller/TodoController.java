package io.me.springtodo.controller;

import io.me.springtodo.dto.TodoRequestDto;
import io.me.springtodo.dto.TodoResponseDto;
import io.me.springtodo.repository.Todo;
import io.me.springtodo.service.Todoservice;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class TodoController {
    public final Todoservice todoservice;
    // 아무것도 없는 상태이더라도 postman 에서 호출을 하게 된다면 200 코드가 나오면서 정상호출이 되었음을 알린다.
    @PostMapping("/v1.0/todo")
    public ResponseEntity<TodoResponseDto> postTodo(@RequestBody TodoRequestDto dto) {

        Todo todo = todoservice.createTodo(dto);
        TodoResponseDto response = new TodoResponseDto(todo);
        return ResponseEntity.ok().body(response);

    }
}
