package pard.server.com.hw4.likes;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pard.server.com.hw4.post.Post;

import java.util.List;

@RestController
@RequiredArgsConstructor

@RequestMapping("/likes")
public class LikesController {
    private final LikesService likesService;

    @PostMapping("") //좋아요 달기. 어떤 계정으로 어떤 계시물에?
    public String createLike(@RequestBody LikesReq likesReq) {
        likesService.createLikes(likesReq);
        return "좋아요를 달았습니다!";
    }

    @GetMapping("findAll")
    public List<LikesRes> findAll() {
        return likesService.findAll();
    }


}
