package kr.co.ch07.entity.shop;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@Table(name="shop_customer")
public class Customer {

    @Id
    private String custid;
    private String name;
    private int age;
    private String hp;

    @CreationTimestamp
    private LocalDateTime regDate;

}
