package kr.co.ch07.entity.board;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@Table(name="Board_Article")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment 어노테이션
    private int no;
    private String title;
    private String content;

    /*
    * @ManyToOne
    * - N:1 단방향 관계설정 어노테이션
    * - 현재 엔티티인 Article과 User 엔티티를 N:1 관계설정
    * - 기본 fetch 전략 Eager를 Lazy로 설정
    *
    * @OneToMany
    * - 1:N 양방향 관계설정 어노테이션
    * - mappingBy 속성은 양방향 관계설정에서 기준이 되는 엔티티의 속성을 설정, FK가 되는 엔티티 속성명을 적용
    * - 참조타입은 List
    * 
    * @JoinColumn
    *  - User 엔티티가 참조되는 테이블 컬럼명
    *  - 외래키 참조 컬럼(FK)
    * 
    * Fetch 전략
    *  - 관계설정된 엔티티간 어느한쪽의 엔티티가 조회될 때 다른 한쪽이 조회되는 로딩 방식
    *  - 1:1, N:1은 즉시로딩(Eager) 전략
    *  - 성능 문제로 즉시로딩 대신 Lazy 전략 권장
    * 
    * 추가 연구과제
    * - N+1 문제 이해하기
    * 고아객체 무넺 이해하기
    * 
    * 
    * */



    // Article: Many
    // User   : One

    /*
    *   eager : 따로 조회하지 않아도 Article을 조회할 때
    *   User이 같이 조회됨으로서 별로 좋지 않음.
    *
    *   Lazy : User에 대한 조회를 원할때만 조회 할 수 있음.
    * */

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="writer")
    private User user;

    /* OneToMany -> List */
    /*
        mappedBy
        기준이 되는 엔티티를 적어준다.
     */
    @OneToMany(mappedBy = "article")
    private List<Comment> comment;

    @OneToMany(mappedBy = "article")
    private List<File> file;

    @CreationTimestamp
    private LocalDateTime wdate;

}
