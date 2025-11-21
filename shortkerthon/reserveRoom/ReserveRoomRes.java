package pard.server.com.shortkerthon.reserveRoom;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class ReserveRoomRes {
    private int roomNumber;
    private String date;

}
