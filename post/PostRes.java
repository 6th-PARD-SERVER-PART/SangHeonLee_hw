package pard.server.com.hw4.post;
import lombok.*;
import java.util.List;


public class PostRes {
    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class P_Res1 {
        private String userName;
        private String postTitle;
        private String postContext;
        private Long likesNum;
    }

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class P_Res2{
        private String postTitle;
        private Long postId;
    }
}
