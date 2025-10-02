package pard.server.com.hw2.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pard.server.com.hw2.dto.UserDto;
import pard.server.com.hw2.dto.UserResponseDto;
import pard.server.com.hw2.service.UserService;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/todo")
public class UserController {
    private final UserService userService;

    @PostMapping("")
    public String save(@RequestBody UserDto userdto) { //DTO 형태로 service에 넘기면 service는 Entity로 바꿔서 리포로 넘긴다.
        userService.saveUser(userdto); //서비스에 넘김
        return "To Do List 저장완료!";
    }

    @GetMapping("/{todoID}") //id로 해당 할일을 찾아라 + DTO형태의 값을 리턴하라
    public UserDto findById(@PathVariable Long todoID){ //PathVariable로 조회할 할일의 id를 입력받는다
        return userService.findById(todoID); //서비스로 조회할 id를 넘김
    }

    @GetMapping("/{todoID}/titleDueDate") //해당 할일의 id를 통해 제목과 마감기한을 조회,리턴하라
    public UserResponseDto titleDueDate(@PathVariable Long todoID){
        return userService.titleDueDate(todoID);
    }

    @GetMapping("/{todoID}/statusCheck")
    public String statusCheck(@PathVariable Long todoID){
        return userService.statusCheck(todoID);
    }

    @PatchMapping("/{todoID}/{status}") //할일 완료시 상태 업데이트.
    public String updateStatusById(@PathVariable Long todoID, @PathVariable String status){ //업뎃 할 할일 id와 업뎃할 상태 입력받음
        userService.updateStatusById(todoID, status); //service로 넘김
        return "수정완료!";
    }

    @DeleteMapping("/{todoID}")
    public String delete(@PathVariable Long todoID){
        userService.delete(todoID);
        return "삭제완료!";
    }

    @GetMapping("")
    public List<UserDto> findAll(){
        return userService.findAll();
    }


}
