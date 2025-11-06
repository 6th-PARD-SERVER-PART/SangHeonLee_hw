package pard.server.com.hw4.user;
import pard.server.com.hw4.likes.LikesRepository;
import pard.server.com.hw4.post.Post;
import pard.server.com.hw4.post.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final LikesRepository likesRepository;

    public void createUser(UserReq userReq){ //dto Req를 받아서
        User user = new User(); //앤티티 새로 생성
        user.setUserName(userReq.getUserName()); //Req를 엔티티로 옮기고 리포에 저장
        userRepository.save(user);
    }

    public UserRes.U_Res1 getUser(Long id){ //유저의 이름과 유저가 작성한 게시글 목록을 리스트로 리턴하는 DTO생성
        User user = userRepository.findById(id) //일단 해당 아이디를 찾아서 엔티티 생성
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        List<Post> userPosts = postRepository.findByUserId(id); //post리포에 만들어놓은 findByUserId로
        //입력한 u_id 값과 동일한 u_id를 fk로 가진 post들을 목록으로 저장 (many to one)

        List <String> postTitles = userPosts.stream()
                .map(Post::getPostTitle) // 각 Post 객체에서 제목을 가져옴
                .toList(); //제목들을 리스트로 만들었음

        return UserRes.U_Res1.builder() //dto 필드인 이름과 제목 리스트를 넣어서 리턴
                .userName(user.getUserName()) //이름 넣고
                .postTitles(postTitles) // 필드에 위에서 만든 리스트 넣는다
                .build();
    }

    public List<UserRes.U_Res2> findAll(){ //유저의 id와 이름을 필드로 가지는 dto U_Res2를 모두 담은 리스트 리턴
        List<UserRes.U_Res2> uRes = new ArrayList<>(); //일단 최종 리턴할 리스트 uRes 만듦.

        userRepository.findAll().forEach(user -> { //유저 리포에서 findAll해서 모든 user엔티티를 차례로 불러온다.
            UserRes.U_Res2 userRes = UserRes.U_Res2.builder() // dto타입 userRes를 빌드
                    .userId(user.getId()) //빌드한 dto인 userRes에 필드를 차례로 채워나간다.
                    .userName(user.getUserName())
                    .build();
            uRes.add(userRes);//위에서 만든 리스트에 저장
        });
        return uRes; //리스트 리턴
    }

    @Transactional
    public void deleteUser(Long u_id){ //양방향이 아니라 단방향이라 cascade, orphanremoval 사용 못함.
        //지우고자하는 u_id를 fk로 가지는 모든 post, likes 테이블을 삭제해야하니까
        //post와 likes 리포에 각각 삭제 메서드를 만들어서 개별 삭제 진행.

        // 삭제할 유저가 작성한 모든 게시글을 싹다 조회
        List<Post> postsToDelete = postRepository.findByUserId(u_id);

        // 해당 게시글들에 달린 모든 조아요 삭제.
        for (Post post : postsToDelete) {
            likesRepository.deleteByPostId(post.getId());
        }

        likesRepository.deleteByUserId(u_id); //얘도 ㄷㄷ
        postRepository.deleteByUserId(u_id); //jpa성능 개좋아서 deleteByUserId인식하고 fk가 지우고자하는 u_id인애들 삭제해줌
        userRepository.deleteById(u_id); //얜 u_id가 pk니까 그냥 deleteyId로
    }
}
