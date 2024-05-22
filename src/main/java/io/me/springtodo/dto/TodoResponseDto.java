package io.me.springtodo.dto;

import io.me.springtodo.repository.Todo;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TodoResponseDto {
    private String title;
    private String content;
    private String userName;
    // Builder 패턴
    private LocalDateTime createdAt;

    public TodoResponseDto(Todo todo) {
        this.title = todo.getTitle();
        this.content = todo.getContent();
        this.userName = todo.getUserName();
        this.createdAt = todo.getCreatedAt();
    }
}
