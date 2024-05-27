package io.me.springtodo.entity;

import io.me.springtodo.repository.Todo;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // 댓글의 부여할 id
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "todo_id")
    // 댓글이 속하게될 일정
    private Todo todo;

    private String content; // 댓글 내용
    private String userName; // 댓글 작성자
    private LocalDateTime createdAt; // 댓글 작성 시간

    // TODO [질문사항] : 분명히 Lombok 의 @Getter 어노테이션을 사용해서 @Getter 를 추가했는데도 CommentResponseDto 클래스의 Builder 에서 Getter 를 추가하라고 에러를 내보냄.
    public Long getCommentId() {
        return id;
    }

    // TODO [질문사항] : TodoEntity 클래스와 다른것이 무엇이길래 commentEntity 클래스는 @prePersist 어노테이션을 달아야 하는가?
    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }
}
