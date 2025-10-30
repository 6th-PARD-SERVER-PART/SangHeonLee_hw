package pard.server.com.hw4.user;


import lombok.*;
import java.util.List;

public class UserRes {
    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class U_Res1{
        private String userName;
        private List<String> postTitles;
    }

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class U_Res2{
        private Long userId;
        private String userName;
    }

}
