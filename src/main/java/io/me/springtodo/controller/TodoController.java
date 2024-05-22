package io.me.springtodo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TodoController {
    // 아무것도 없는 상태이더라도 postman 에서 호출을 하게 된다면 200 코드가 나오면서 정상호출이 되었음을 알린다.
    @PostMapping("/v1.0/todo")
    public ResponseEntity postTodo() {
        //TODO 할일 일정 작성 기능 구현하기.
        return ResponseEntity.ok().build();

    }
}
