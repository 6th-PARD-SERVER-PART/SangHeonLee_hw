package pard.server.com.shortkerthon.reserveRoom;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ReserveRoomReq {
    @AllArgsConstructor
    @Builder
    @Getter
    @NoArgsConstructor
    public static class Req1 {
        private int floor;
        private String date;
    }

    @AllArgsConstructor
    @Builder
    @Getter
    @NoArgsConstructor
    public static class Req2 {
        private int roomNumber;
        private String date;
        private int floor;
    }


    @AllArgsConstructor
    @Builder
    @Getter
    @NoArgsConstructor
    public static class Req3 {
        private int roomNumber;
        private String date;
    }
}
