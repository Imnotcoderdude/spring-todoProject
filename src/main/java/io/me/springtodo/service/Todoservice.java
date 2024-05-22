package io.me.springtodo.service;

import io.me.springtodo.dto.TodoRequestDto;
import io.me.springtodo.repository.Todo;
import io.me.springtodo.repository.TodoRepository;
import lombok.AllArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class Todoservice {
    private final TodoRepository todoRepository;

    // 할일을 생성하는 메서드
    public Todo createTodo(TodoRequestDto dto) {
    var newTodo = dto.toEntity();
    return todoRepository.save(newTodo);
    }

    // 할일 단건 조회
    public Todo getTodo(Long todoId) {
        return todoRepository.findById(todoId)
                .orElseThrow(IllegalArgumentException::new);
    }

    // 할일 전체 조회
    // 오름차순 정렬을 하기 위해 findAll 에서 Sort.by 정렬을 사용하고 createdAt 을 기준 삼아 descending 으로 오름차순 정렬 실행
    public List<Todo> getTodos() {
        return todoRepository.findAll(Sort.by(("createdAt")).descending());
    }

    // 할일 수정
    public Todo updateTodo(Long todoId, TodoRequestDto dto) {
        Todo todo = checkPWAndGetTodo(todoId, dto.getPassword());

        todo.setTitle(dto.getTitle());
        todo.setContent(dto.getContent());
        todo.setUserName(dto.getUserName());
        return todoRepository.save(todo);

    }

    private Todo checkPWAndGetTodo(Long todoId, String password) {
        Todo todo = getTodo(todoId);

        //todo 조회를 했을때 비밀번호 체크하는 조건문
        if (todo.getPassword() != null && !Objects.equals(todo.getPassword(), password)) {
            throw new IllegalArgumentException("비밀번호가 맞지 않습니다.");
        }
        return todo;
    }

    // 지정된 일정을 삭제하는 기능
    public void deleteTodo(Long todoId, String password) {
        Todo todo = checkPWAndGetTodo(todoId, password);

        todoRepository.delete(todo);
    }
}
