package pard.server.com.hw4.post;

import jakarta.persistence.*;
import lombok.*;
import pard.server.com.hw4.user.User;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "p_id")
    private Long id;

    @Column(name = "p_title")
    private String postTitle;

    @Column(name = "p_context")
    private String postContext;

    @ManyToOne //단방향으로 user_id 참조
    @JoinColumn(name = "u_id") //컬럼이름을 user_id로
    private User user;

    @Builder // Builder 패턴을 위한 생성자
    public Post(String p_title, String p_context, User user) {
        this.postTitle = p_title;
        this.postContext = p_context;
        this.user = user;
    }
}
