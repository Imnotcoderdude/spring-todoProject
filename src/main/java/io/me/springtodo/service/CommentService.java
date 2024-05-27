package io.me.springtodo.service;

import io.me.springtodo.dto.CommentRequestDto;
import io.me.springtodo.entity.Comment;
import io.me.springtodo.repository.CommentRepository;
import io.me.springtodo.repository.Todo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CommentService {
    private final TodoService todoService;
    private final CommentRepository commentRepository;

    //댓글을 추가하는 메서드
    public Comment addComment(Long todoId, CommentRequestDto commentRequestDto) {
        // 입력받은 todoId 를 이용해서 해당하는 일정을 가져온다.
        Todo todo = todoService.getTodobyId(todoId);
        // 요청된 Dto 로 부터 댓글 entity 생성
        Comment comment = requestDto.toEntity();
        // 댓글에 해당하는 일정을 설정한다.
        comment.setTodo(todo);
        // 입력받은 댓글을 저장하고 저장된 댓글을 반환함
        return commentRepository.save(comment);
    }
}
