package pard.server.com.hw4.likes;
import org.springframework.data.jpa.repository.JpaRepository;
import pard.server.com.hw4.post.Post;
import pard.server.com.hw4.user.User;

import java.util.Optional;

public interface LikesRepository extends JpaRepository<Likes, Long> {
    //UserService에서 호출받는 애들들
    void deleteByUserId(Long userId); //User 서비스에서 유저가 삭제될때 유저가 작성한 좋아요도 사라지게끔

    //PostService에서 호출받는 애들
    Long countByPostId(Long postId);
    void deleteByPostId(Long postId);

    //LikesService에서 호출받는 애들
    Optional<Likes> findByUserAndPost(User user, Post post);
}