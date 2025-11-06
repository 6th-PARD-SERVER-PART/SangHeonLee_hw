package pard.server.com.hw4.post;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
@Tag(name = "Post", description = "게시물을 작성, 조회, 삭제.")
public class PostController {
    private final PostService postService;

    @PostMapping("") //계정 생성
    @Operation(summary = "Post", description = "게시글을 생성")
    public String createPost(@RequestBody PostReq postReq) {
        postService.createPost(postReq);
        return "게시글 생성 완료";
    }

    @Operation(summary = "Post", description = "특정 게시물을 조회")
    @GetMapping("/{id}")
    public PostRes.P_Res1 getPost(@PathVariable Long id) {
        return postService.getPostById(id);
    }

    @Operation(summary = "Post", description = "모든 게시물을 조회")
    @GetMapping("findAll")
    public List<PostRes.P_Res2> findAll() {
        return postService.findAll();
    }

    @Operation(summary = "Post", description = "게시글을 삭제")
    @DeleteMapping("/{id}")
    public String deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return "삭제완료";
    }
}
