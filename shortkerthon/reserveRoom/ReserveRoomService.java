package pard.server.com.shortkerthon.reserveRoom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import pard.server.com.shortkerthon.emptyRoom.EmptyRoom;
import pard.server.com.shortkerthon.emptyRoom.EmptyRoomRepo;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReserveRoomService {
    private final ReserveRoomRepo reserveRoomRepo;
    private final EmptyRoomRepo emptyRoomRepo;


    public List<ReserveRoomRes> findAll() {
        List<ReserveRoomRes> resDtos = new ArrayList<>();

        reserveRoomRepo.findAll().forEach(reserveRoom -> {
            EmptyRoom emptyRoom = emptyRoomRepo.findById(reserveRoom.getRoomId())
                    .orElseThrow(()-> new RuntimeException());
            ReserveRoomRes res = ReserveRoomRes.builder()
                    .date(reserveRoom.getDate())
                    .roomNumber(emptyRoom.getRoomNumber())
                    .build();
            resDtos.add(res);
        });
        return resDtos;
    }

    public List<Integer> reservedRoomNumbers(ReserveRoomReq.Req1 req1){
        List<Integer> resDtos = new ArrayList<>();

        List<ReservedRoom> reservedRooms = reserveRoomRepo.findAllByDateAndFloor(
                req1.getDate(),
                req1.getFloor()
        );

        for (ReservedRoom res : reservedRooms) {
            EmptyRoom emptyRoom = emptyRoomRepo.findByRoomId(res.getRoomId());

            resDtos.add(emptyRoom.getRoomNumber());
        }

        return resDtos;
    }

    public void reserveRoom(ReserveRoomReq.Req2 req2) {
        EmptyRoom emptyRoom = emptyRoomRepo.findByRoomNumber(req2.getRoomNumber());
        ReservedRoom reservedRoomForCheck = reserveRoomRepo.findByRoomIdAndDateAndFloor(emptyRoom.getRoomId(), req2.getDate(), req2.getFloor());

        //중복체크
        if (reservedRoomForCheck != null) {
            throw new IllegalStateException("해당 날짜(" + req2.getDate() +
                    ") 및 층(" + req2.getFloor() +
                    ")의 방(" + req2.getRoomNumber() +
                    ")은 이미 예약되었습니다.");
        }
        ReservedRoom reservedRoom = ReservedRoom.builder()
                .roomId(emptyRoom.getRoomId())
                .date(req2.getDate())
                .floor(req2.getFloor())
                .build();
        reserveRoomRepo.save(reservedRoom);
    }

    @Transactional
    public void deleteRoom(ReserveRoomReq.Req3 req3) {
        EmptyRoom emptyRoom = emptyRoomRepo.findByRoomNumber(req3.getRoomNumber());
        reserveRoomRepo.deleteByRoomIdAndDate(emptyRoom.getRoomId(), req3.getDate());
    }
}
