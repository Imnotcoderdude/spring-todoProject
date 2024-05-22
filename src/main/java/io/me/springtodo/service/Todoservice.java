package io.me.springtodo.service;

import io.me.springtodo.dto.TodoRequestDto;
import io.me.springtodo.repository.Todo;
import io.me.springtodo.repository.TodoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class Todoservice {
    private final TodoRepository todoRepository;

    // 할일을 생성하는 메서드
    public Todo createTodo(TodoRequestDto dto) {
    var newTodo = dto.toEntity();
    return todoRepository.save(newTodo);
    }
}
