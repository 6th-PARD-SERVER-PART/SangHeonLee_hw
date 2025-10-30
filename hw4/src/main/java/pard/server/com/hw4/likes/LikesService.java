package pard.server.com.hw4.likes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pard.server.com.hw4.post.Post;
import pard.server.com.hw4.post.PostRepository;
import pard.server.com.hw4.user.User;
import pard.server.com.hw4.user.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class LikesService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final LikesRepository likesRepository;

    public void createLikes(LikesReq likeReq) {
        //일단 Likes 엔티티를 만들어서 저장해야한다.
        User users = userRepository.findById(likeReq.getUserId()) //dto로 입력받은 계정id로 엔티티 필드에 넣을 계정을 찾고
                .orElseThrow(() -> new IllegalArgumentException("좋아요를 누르려는 계정을 찾을 수 없습니다."));

        Post posts = postRepository.findById(likeReq.getPostId()) //dto로 입력받은 게시글 id로 엔티티 필드에 넣을 게시글을 찾고
                .orElseThrow(() -> new IllegalArgumentException("좋아요를 누를 게시글을 찾을 수 없습니다."));


        //만약 like리포에 이미 같은 유저, 같은 포스트에 좋아요를 누른 기록이 있는지 확인
        Optional<Likes> existingLike = likesRepository.findByUserAndPost(users, posts);

        if (existingLike.isPresent()) { //기록이 있으면 조회안 existingLike를 삭제
            likesRepository.delete(existingLike.get());
        } else { //없으면 좋아요 추가
            Likes newLike = Likes.builder()
                    .user(users)
                    .post(posts)
                    .build();
            likesRepository.save(newLike);
        }
    }

    public List<LikesRes> findAll() {
        List<LikesRes> likesList = new ArrayList<>();

        likesRepository.findAll().forEach(likes -> {
            LikesRes likesRes = LikesRes.builder()
                    .likesId(likes.getId())
                    .userName(likes.getUser().getUserName())
                    .postTitle(likes.getPost().getPostTitle())
                    .build();
            likesList.add(likesRes);
        });
        return likesList;
    }


}
