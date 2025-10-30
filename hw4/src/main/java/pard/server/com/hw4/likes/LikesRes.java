package pard.server.com.hw4.likes;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LikesRes {
    private Long likesId;
    private String userName;
    private String postTitle;

}
