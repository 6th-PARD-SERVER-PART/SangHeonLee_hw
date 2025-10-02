package pard.server.com.hw2.dto;
import lombok.*;
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter @Builder

public class UserDto {
    private long todoID;
    private String title;
    private String dueDate;
    private String status;

    public void updateStatus(String status) { //Todolist 완료했을때 상태를 완료로 바꾸기위함.
        this.status = status;
    }
}
