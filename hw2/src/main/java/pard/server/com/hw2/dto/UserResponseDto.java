package pard.server.com.hw2.dto;
import lombok.*;
@AllArgsConstructor @NoArgsConstructor
@Builder @Getter @Setter
//할일의 tile과 duedate만 다루는 DTO입니다.
public class UserResponseDto {
    private String title;
    private String dueDate;
}
