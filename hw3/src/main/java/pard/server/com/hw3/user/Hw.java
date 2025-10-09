package pard.server.com.hw3.user;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;



@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Hw {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String status;

    private String dueDate;

    private int level;

    public void updateStatus(String newStatus) {
        this.status = newStatus;
    }
}
