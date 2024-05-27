package io.me.springtodo.dto;

import io.me.springtodo.entity.Comment;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentRequestDto {
    private String comment;
    private String userName;

    // 빌더 패턴을 사용함
    public CommentRequestDto(String comment, String userName) {
        this.comment = comment;
        this.userName = userName;
    }

    public Comment toEntity() {
        return Comment.builder()
                .comment(comment)
                .userName(userName)
                .build();
    }
}
