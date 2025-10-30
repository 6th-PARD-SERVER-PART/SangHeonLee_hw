package pard.server.com.hw4.post;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {
    private final PostService postService;

    @PostMapping("") //계정 생성
    public String createPost(@RequestBody PostReq postReq) {
        postService.createPost(postReq);
        return "게시글 생성 완료";
    }

    @GetMapping("/{id}")
    public PostRes.P_Res1 getPost(@PathVariable Long id) {
        return postService.getPostById(id);
    }

    @GetMapping("findAll")
    public List<PostRes.P_Res2> findAll() {
        return postService.findAll();
    }

    @DeleteMapping("/{id}")
    public String deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return "삭제완료";
    }
}
