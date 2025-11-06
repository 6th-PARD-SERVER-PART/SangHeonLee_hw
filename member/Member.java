package pard.server.com.hw4.member;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Enumerated(EnumType.STRING) // 이거 없으면 admin(0)
    @Builder.Default
    private Role role = Role.USER;

    private String socialID; // 구글에서 할당해주는 사용자 고유의 아이디
}