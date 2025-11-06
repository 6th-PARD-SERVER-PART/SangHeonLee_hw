package pard.server.com.hw4.post;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pard.server.com.hw4.likes.LikesRepository;
import pard.server.com.hw4.user.User;
import pard.server.com.hw4.user.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor

public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final LikesRepository likesRepository;


    public void createPost(PostReq postReq) {
        //요청받은 u_id로 User 엔티티를 조회
        User user = userRepository.findById(postReq.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        //Builder를 사용하여 DTO를 엔티티로 변환하고, 찾아온 User를 설정
        Post post = Post.builder()
                .p_title(postReq.getPostTitle())
                .p_context(postReq.getPostContext())
                .user(user) // 조회한 User 객체를 설정
                .build();

        //완성된 Post 엔티티를 저장
        postRepository.save(post);
    }

    public PostRes.P_Res1 getPostById(Long id) { //id로 포스트(제목, 내용, 작성자, 좋아요개수)리턴
        Post post = postRepository.findById(id) //일단 입력받은 id로 해당 개시물 entity를 찾아온다
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));

        User user = post.getUser(); //포스트의 필드인 유저 엔티티를 가져온다

        return PostRes.P_Res1.builder() //리턴할 P_Res1 dto를 생성
                .postTitle(post.getPostTitle())//dto의 필드를 채워 넣는다
                .postContext(post.getPostContext())
                .userName(user.getUserName()) //many to one으로 필드이지만 fk역할을 하는 유저 엔티티
                .likesNum(likesRepository.countByPostId(id)) //likeRepo안의 countByPostId를 통해 해당 게시글 p_id를 fk로 가진 좋아요개수 리턴
                .build();
    }

    public List<PostRes.P_Res2> findAll() { //모든 게시물을 제목과 아이디만 가진 dto로 이루어진 리스트로 리턴하라
        List<PostRes.P_Res2> pRes = new ArrayList<>(); //리턴할 P_Res2타입 dto인 리스트 생성

        postRepository.findAll().forEach(post -> { //모든 포스트 엔티티를 차례로 찾아서
           PostRes.P_Res2 postRes = PostRes.P_Res2.builder() //포스트 엔티티의 제목과 아이디를
                   .postTitle(post.getPostTitle()) //dto에 넣는다
                   .postId(post.getId())
                   .build();
           pRes.add(postRes); //생성한 dto를 리스트에 추가
        });
        return pRes; //리스트 리턴
    }

    @Transactional
    public void deletePost(Long p_id) {//양방향이 아니라 단방향이라 cascade, orphanremoval 사용 못함.
        //지우고자하는 p_id를 fk로 가지는 모든 likes 테이블을 삭제해야하니까
        //likes 리포에 삭제 메서드를 만들어서 개별 삭제 진행.
        likesRepository.deleteByPostId(p_id);
        postRepository.deleteById(p_id); //얜 u_id가 pk니까 그냥 deleteyId로
    }


}
