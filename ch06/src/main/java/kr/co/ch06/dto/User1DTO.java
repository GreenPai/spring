package kr.co.ch06.dto;


import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor // 모든 속성에 대해 생성자를 생성한다.
@NoArgsConstructor  // default 기본 생성자를 생성한다.
@Builder

public class User1DTO {

    private String uid;
    private String name;
    private String hp;
    private int age;

}
