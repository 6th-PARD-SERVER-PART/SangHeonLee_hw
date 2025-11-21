package pard.server.com.shortkerthon.reserveRoom;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReserveRoomRepo extends JpaRepository<ReservedRoom, Long> {
    List<ReservedRoom> findAllByDateAndFloor(String date, Integer floor);

    ReservedRoom deleteByRoomIdAndDate(Long roomId, String date);

    ReservedRoom findByRoomIdAndDateAndFloor(Long roomId, String date, int floor);
}
