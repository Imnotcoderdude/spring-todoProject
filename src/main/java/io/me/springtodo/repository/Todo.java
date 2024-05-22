package io.me.springtodo.repository;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "todoid", nullable = false)
    private Long todoId;

}
