package io.me.springtodo.controller;

import io.me.springtodo.dto.TodoRequestDto;
import io.me.springtodo.dto.TodoResponseDto;
import io.me.springtodo.repository.Todo;
import io.me.springtodo.repository.TodoRepository;
import io.me.springtodo.service.Todoservice;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/api/todo")
public class TodoController {
    public final Todoservice todoservice;
    private final TodoRepository todoRepository;

    // 아무것도 없는 상태이더라도 postman 에서 호출을 하게 된다면 200 코드가 나오면서 정상호출이 되었음을 알린다.
    // 일정을 입력받고 저장하는 Post 형태의 api 생성
    @PostMapping
    public ResponseEntity<TodoResponseDto> postTodo(@RequestBody TodoRequestDto dto) {

        Todo todo = todoservice.createTodo(dto);
        TodoResponseDto response = new TodoResponseDto(todo);
        return ResponseEntity.ok().body(response);
    }
    // todoId 를 사용하여 원하는 일정의 id를 입력하여 단건 조회 기능을 하는 api 생성
    // 경로변수{todoId}로 todoId 받아오기.
    @GetMapping("/{todoId}")
    public ResponseEntity<TodoResponseDto> getTodo(@PathVariable Long todoId) {
        Todo todo = todoservice.getTodo(todoId);
        TodoResponseDto response = new TodoResponseDto(todo);
        return ResponseEntity.ok().body(response);
    }

    // 일정의 전체 목록을 조회하는 api.
    // List를 사용하여 저장되어있는 정보를 컬렉션으로 불러오고 stream 으로 mapping 한다.
    @GetMapping
    public ResponseEntity<List<TodoResponseDto>> getTodos() {
        List<Todo> todos = todoservice.getTodos();
        List<TodoResponseDto> response = todos.stream()
                .map(TodoResponseDto::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(response);
    }

    // 일정을 수정하는 api.
    //
    @PutMapping("/{todoId}")
    public ResponseEntity<TodoResponseDto> putTodo(@PathVariable Long todoId, @RequestBody TodoRequestDto dto) {
        Todo todo = todoservice.updateTodo(todoId, dto);
        TodoResponseDto response = new TodoResponseDto(todo);
        return ResponseEntity.ok().body(new TodoResponseDto(todo));
    }

}
