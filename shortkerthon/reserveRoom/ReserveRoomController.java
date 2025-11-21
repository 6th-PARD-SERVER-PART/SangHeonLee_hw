package pard.server.com.shortkerthon.reserveRoom;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservations")
@RequiredArgsConstructor
public class ReserveRoomController {
    private final ReserveRoomService reserveRoomService;

    @GetMapping("findAll")
    public List<ReserveRoomRes> findAll() {
        return reserveRoomService.findAll();
    }

    @GetMapping("") //예약 불가한 방번호 리턴
    public List<Integer> avaliableRoom(@RequestBody ReserveRoomReq.Req1 req1) {
        return reserveRoomService.reservedRoomNumbers(req1);
    }

    @PostMapping("") //예약하기
    public void reserveRoom(@RequestBody ReserveRoomReq.Req2 req2) {
        reserveRoomService.reserveRoom(req2);
    }

    @DeleteMapping("")
    public void deleteRoom(@RequestBody ReserveRoomReq.Req3 req3) {
        reserveRoomService.deleteRoom(req3);
    }
}
