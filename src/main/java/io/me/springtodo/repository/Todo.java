package io.me.springtodo.repository;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Entity
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "todoid", nullable = false)
    private Long todoId;
    private String title;
    private String content;
    private String userName;
    private String password;
    private LocalDateTime createdAt;

    @Builder
    // 투두 생성자 생성
    public Todo(String title, String content, String userName, String password) {
        this.title = title;
        this.content = content;
        this.userName = userName;
        this.password = password;
        this.createdAt = LocalDateTime.now();
    }
}
