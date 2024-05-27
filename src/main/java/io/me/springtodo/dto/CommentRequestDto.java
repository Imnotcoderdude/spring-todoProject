package io.me.springtodo.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentRequestDto {
    private String comment;
    private String userName;

    // 빌더 패턴을 사용함
    @Builder
    public CommentRequestDto(String comment, String userName) {
        this.comment = comment;
        this.userName = userName;
    }
}
