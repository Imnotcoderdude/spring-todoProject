package io.me.springtodo.dto;

import io.me.springtodo.entity.Comment;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommentResponseDto {
    private Long commentId;
    private Long todoId;
    private String content;
    private LocalDateTime createdAt;

    @Builder
    public CommentResponseDto(Long commentId, Long todoId, String content, LocalDateTime createdAt) {
        this.commentId = commentId;
        this.todoId = todoId;
        this.content = content;
        this.createdAt = createdAt;
    }

    // Entity로부터 Dto를 생성하는 메서드
    public static CommentResponseDto fromEntity(Comment comment) {
        return CommentResponseDto.builder()
                .commentId(comment.getCommentId())
                .todoId(comment.getTodo().getTodoId())
                .content(comment.getContent())
                .createdAt(comment.getCreatedAt())
                .build();
    }
}
