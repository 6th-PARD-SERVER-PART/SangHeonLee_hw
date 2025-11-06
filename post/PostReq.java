package pard.server.com.hw4.post;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostReq {
    private String postTitle;
    private String postContext;
    private Long userId; //게시글을 작성할 계정 아이디를 입력받아 나중에 서비스에서
    //u_id를 findById를 해서 Entity로 변환시켜 테이블에 저장할거임
}
