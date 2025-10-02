package pard.server.com.hw2.service;
import lombok.RequiredArgsConstructor;
import org.springframework.aop.scope.ScopedProxyUtils;
import org.springframework.stereotype.Service;
import pard.server.com.hw2.entity.User;
import pard.server.com.hw2.dto.UserDto;
import pard.server.com.hw2.dto.UserResponseDto;
import pard.server.com.hw2.repository.UserRepository;

import java.sql.SQLOutput;
import java.util.List;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public void saveUser(UserDto userDto) {//받은 Dto를 Repository로 넘기기위해 Entity로 변환
        User user = User.builder() //build: User 객체를 필드값을 지정하여 생성
                .todoID(userDto.getTodoID())
                .title(userDto.getTitle())
                .dueDate(userDto.getDueDate())
                .status(userDto.getStatus())
                .build();
        System.out.println("id: " + userDto.getTodoID() + " title: " + userDto.getTitle() + " has been saved.");

        userRepository.save(user); //Entity로 변환한것을 리포지토리로 넘긴다.
    }

    public UserDto findById(Long todoID) { //컨트롤러에서 받은 아이디
        User user = userRepository.findById(todoID); //리포지토리로부터 해당 아이디와 겹치는 entity를 리턴받는다.
        return UserDto.builder() //dto 형태로 리턴해야하기 때문에 entity형태인 user의 필드들을 dto형태의 객체를 생성해 안에 넣고 리턴
                .todoID(user.getTodoID())
                .title(user.getTitle())
                .dueDate(user.getDueDate())
                .status(user.getStatus())
                .build();
    }

    public UserResponseDto titleDueDate(Long todoID) { //컨트롤러에서 받은 id
        User user = userRepository.findById(todoID); // id와 겹치는 key값을 찾아서 entity 생성

        return UserResponseDto.builder() //dto 형태로 리턴해야하기 때문에 특수 Dto 생성후 안에 필드값 저장 후 리턴
                .title(user.getTitle())
                .dueDate(user.getDueDate())
                .build();
    }

    public String statusCheck(Long todoID){
        User user = userRepository.findById(todoID); // id와 겹치는 key값을 찾아서 entity 생성

        if(user.getStatus().equalsIgnoreCase("completed") || user.getStatus().equalsIgnoreCase("complete")){
            return "completed";
        }else{
            return "incomplete";
        }
    }

    public void updateStatusById(Long todoID, String status) { //id와 업데이트 할 상태를 컨트롤러에게 받음
        User user = userRepository.findById(todoID); //레포에서 해당 할일을 찾아 entity 생성.
        user.updateStatus(status); // 상태 업데이트 .user 엔티티 자체 메서드.

        userRepository.save(user); //업뎃 후 저장.
    }

    public void delete(Long todoID) {
        userRepository.delete(todoID);
    }

    public List<UserDto> findAll() { //컨트롤러에 dto 형태의 객체를 담은 리스트를 반환해야한다.
        List<UserDto> userDtos = new ArrayList<>(); //Entity 말고 Dto형태의 객체들을 저장할 리스트 새로 만듦.

        //Entity형태의 객체들이 저장된 리스트를 리포로부터 받음.
        userRepository.findAll().forEach(user -> {//리스트의 각 Entity (user) 마다 아래 과정을 수행
            UserDto userDto = UserDto.builder() //UserDto 타입의 객체를 생성
                    .todoID(user.getTodoID()) // Entity 타입 user의 각 필드를 UserDto에 넣는다.
                    .title(user.getTitle())
                    .dueDate(user.getDueDate())
                    .status(user.getStatus())
                    .build();
            userDtos.add(userDto); //그렇게 작성된 userDto를 리스트에 넣는다
        });

        return userDtos; //리스트 반환
    }
}



