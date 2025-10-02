package pard.server.com.hw2.repository;
import org.springframework.stereotype.Repository;
import pard.server.com.hw2.dto.UserResponseDto;
import pard.server.com.hw2.entity.User;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
@Repository
public class UserRepository {
    public Map<Long, User> userRepository = new HashMap<>(); //데이터를 저장할 해시맵 생성

    public void save(User user) { //UserService에서 넘겨받은 Entity를 Map에 저장 (key값: Id / value값: Entity 자체)
        userRepository.put(user.getTodoID(), user); //getTodoID는 @Getter 덕분에 구현안해도 그냥 사용가능
    }

    public User findById(Long todoID) {
        return userRepository.get(todoID); //데이터중에서 id와 key값이 겹치는 value (User 타입)리턴
    }

    public void delete(Long todoID) {
        userRepository.remove(todoID);
    }

    public List<User> findAll() { //유저타입 객체들을 담은 MAP을 유저 타입을 담은 List로 변환하여 리턴하는 메서드
        return userRepository.values().stream().toList();
        //value: userRepository는 Map 형태이므로,Map에 저장된 모든 값(value)들, 즉 모든 User 객체들을 컬렉션 형태로 가져온다.
        //stream: values()가 반환한 컬렉션을 스트림(Stream)으로 변환한다. 스트림은 데이터를 처리하기 위한 다양한 중간 연산과 최종 연산을 제공한다.
        //toList: 스트림의 모든 요소를 최종적으로 새로운 List 객체에 담아 반환한다.
    }

}
