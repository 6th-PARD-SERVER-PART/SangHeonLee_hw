package pard.server.com.shortkerthon.reserveRoom;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReservedRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reserveRoomId;

    private Long roomId;

    private String date;

    private int floor;
}
