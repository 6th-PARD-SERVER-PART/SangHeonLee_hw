package pard.server.com.hw4.user;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @PostMapping("") //계정 생성
    public String createUser(@RequestBody UserReq userReq) { //dto를 서비스로 넘긴다
        userService.createUser(userReq);
        return "계정 생성완료 ";
    }

    @GetMapping("/{id}") //id로 이름과 게시글 작성 목록 리턴
    public UserRes.U_Res1 getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @GetMapping("findAll")
    public List<UserRes.U_Res2> findAll() {
        return userService.findAll();
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "삭제완료";
    }
}
