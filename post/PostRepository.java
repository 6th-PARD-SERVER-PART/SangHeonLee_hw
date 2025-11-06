package pard.server.com.hw4.post;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
    //UserService에서 사용되는 애들
    List<Post> findByUserId(Long userId); //User 서비스에서 사용하기 위해 u_id를 가진 Post를 리스트로저장
    void deleteByUserId(Long userId); //User 서비스에서 유저가 삭제될때 유저가 작성한 게시글도 같이 삭제되게끔


}
