package io.me.springtodo.controller;

import io.me.springtodo.CommonResponse;
import io.me.springtodo.dto.CommentRequestDto;
import io.me.springtodo.dto.CommentResponseDto;
import io.me.springtodo.dto.TodoRequestDto;
import io.me.springtodo.dto.TodoResponseDto;
import io.me.springtodo.entity.Comment;
import io.me.springtodo.repository.Todo;
import io.me.springtodo.service.CommentService;
import io.me.springtodo.service.TodoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/api/todo")
public class TodoController {
    public final TodoService todoservice;
    private final CommentService commentService;

    // 아무것도 없는 상태이더라도 postman 에서 호출을 하게 된다면 200 코드가 나오면서 정상호출이 되었음을 알린다.
    // 일정을 입력받고 저장하는 Post 형태의 api 생성
    @PostMapping
    public ResponseEntity<CommonResponse<TodoResponseDto>> postTodo(@RequestBody TodoRequestDto dto) {

        Todo todo = todoservice.createTodo(dto);
        TodoResponseDto response = new TodoResponseDto(todo);
        return ResponseEntity.ok()
                .body(CommonResponse.<TodoResponseDto>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("생성이 완료 되었습니다.")
                        .data(response)
                        .build());
    }

    // todoId 를 사용하여 원하는 일정의 id를 입력하여 단건 조회 기능을 하는 api 생성
    // 경로변수{todoId}로 todoId 받아오기.
    @GetMapping("/{todoId}")
    public ResponseEntity<CommonResponse<TodoResponseDto>> getTodo(@PathVariable Long todoId) {
        Todo todo = todoservice.getTodo(todoId);
        TodoResponseDto response = new TodoResponseDto(todo);
        return ResponseEntity.ok()
                .body(CommonResponse.<TodoResponseDto>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("한건 조회가 완료 되었습니다.")
                        .data(response)
                        .build());
    }

    // 일정의 전체 목록을 조회하는 api.
    // List를 사용하여 저장되어있는 정보를 컬렉션으로 불러오고 stream 으로 mapping 한다.
    @GetMapping
    public ResponseEntity<CommonResponse<List<TodoResponseDto>>> getTodos() {
        List<Todo> todos = todoservice.getTodos();
        List<TodoResponseDto> response = todos.stream()
                .map(TodoResponseDto::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok()
                .body(CommonResponse.<List<TodoResponseDto>>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("전체 조회가 완 되었습니다.")
                        .data(response)
                        .build());
    }

    // 일정을 수정하는 api.
    //
    @PutMapping("/{todoId}")
    public ResponseEntity<CommonResponse<TodoResponseDto>> putTodo(@PathVariable Long todoId, @RequestBody TodoRequestDto dto) {
        Todo todo = todoservice.updateTodo(todoId, dto);
        TodoResponseDto response = new TodoResponseDto(todo);
        return ResponseEntity.ok()
                .body(CommonResponse.<TodoResponseDto>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("일정 수정이 완료 되었습니다.")
                        .data(response)
                        .build());
    }

    // 선택한 일정 삭제 api
    @DeleteMapping("/{todoId}")
    public ResponseEntity<CommonResponse> deleteTodo(@PathVariable Long todoId, @RequestBody TodoRequestDto dto) {
        todoservice.deleteTodo(todoId, dto.getPassword());
        return ResponseEntity.ok()
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("삭제가 완료 되었습니다.")
                        .build());
    }

    // 일정에 댓글을 추가하는 api
    @PostMapping("/{todoId}/comments")
    public ResponseEntity<CommonResponse<CommentResponseDto>> addComment(@PathVariable Long todoId, @RequestBody CommentRequestDto dto) {
        // 원하는 일정에 새로운 댓글을 추가하고 추가된 댓글을 반환하는 코드
        Comment comment = commentService.addComment(todoId,dto);
        // 추가된 댓글 정보를 ResponseDto 에 담아서 클라이언트에 반환함
        CommentResponseDto responseDto = new CommentResponseDto(comment);
        return ResponseEntity.ok().body(CommonResponse.<CommentResponseDto>builder()
                .statusCode(HttpStatus.OK.value())
                .message("댓글이 성공적으로 추가되었습니다!")
                .data(responseDto)
                .build());

    }

}
