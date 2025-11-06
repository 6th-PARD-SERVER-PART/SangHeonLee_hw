package pard.server.com.hw4.likes;

import jakarta.persistence.*;
import lombok.*;
import pard.server.com.hw4.user.User;
import pard.server.com.hw4.post.Post;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Likes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "l_id")
    private Long id;

    @ManyToOne //단방향으로 user_id 참조
    @JoinColumn(name = "u_id") //컬럼이름을 user_id로
    private User user;

    @ManyToOne //단방향으로 user_id 참조
    @JoinColumn(name = "p_id") //컬럼이름을 user_id로
    private Post post;

    @Builder // Builder 패턴을 위한 생성자
    public Likes(User user, Post post) {
        this.user = user;
        this.post = post;
    }
}
