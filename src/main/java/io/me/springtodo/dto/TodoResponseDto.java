package io.me.springtodo.dto;

import io.me.springtodo.repository.Todo;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TodoResponseDto {
    private Long todoId;
    private String title;
    private String content;
    private String userName;
    private LocalDateTime createdAt;

    // Builder 패턴
    public TodoResponseDto(Todo todo) {
        this.todoId = todo.getTodoId();
        this.title = todo.getTitle();
        this.content = todo.getContent();
        this.userName = todo.getUserName();
        this.createdAt = todo.getCreatedAt();
    }
}
