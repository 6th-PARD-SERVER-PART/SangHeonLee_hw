package pard.server.com.shortkerthon.emptyRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmptyRoomRepo extends JpaRepository<EmptyRoom, Long> {
    EmptyRoom findByRoomId(Long roomId);
    EmptyRoom findByRoomNumber(int roomNumber);
}
