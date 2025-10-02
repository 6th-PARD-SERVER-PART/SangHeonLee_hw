package pard.server.com.hw2.entity;
import lombok.*;
@Getter @Builder //getTodoID() 같은 메서드를 정의하지 않아도 사용할수 있다.
@NoArgsConstructor @AllArgsConstructor

public class User { //todolist의 요소들을 Entity에 지정
    private long todoID;
    private String title;
    private String dueDate;
    private String status;

    public void updateStatus(String status) { //Todolist 완료했을때 상태를 완료로 바꾸기위함.
        this.status = status;
    }
}
