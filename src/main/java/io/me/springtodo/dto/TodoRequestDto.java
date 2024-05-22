package io.me.springtodo.dto;

import io.me.springtodo.repository.Todo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TodoRequestDto {
    private String title;
    private String content;
    private String userName;
    private String password;

    // Builder 패턴
    public Todo toEntity() {
        return Todo.builder()
                .title(title)
                .content(content)
                .userName(userName)
                .password(password)
                .build();
    }
}
