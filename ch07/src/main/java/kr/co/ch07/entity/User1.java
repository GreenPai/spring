package kr.co.ch07.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import kr.co.ch07.dto.User1DTO;
import lombok.*;

// 엔티티는 @Setter은 사용하지 않는다.
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity               // 엔티티 정의 어노테이션
@Table(name="user1")  // 매핑 테이블 어노테이션
public class User1 {

    @Id                  // PK 컬럼 설정 어노테이션(생략불가)
    private String uid;

    @Column(name="name") // 매핑 컬럼 설정 어노테이션(생략가능, 속성명과 컬러몀이 동일할 경우 생략가능)
    private String name;

    @Column(name="hp")
    private String hp;

    // 생략가능
    private int age;

    // DTO 변환 메서드 정의
    public User1DTO toDTO(){
        return User1DTO.builder()
                .uid(uid)
                .name(name)
                .hp(hp)
                .age(age)
                .build();
    }

}
