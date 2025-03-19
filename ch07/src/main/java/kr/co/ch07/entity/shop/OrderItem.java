package kr.co.ch07.entity.shop;


import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@Table(name="shop_orderitem")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int itemNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="orderId")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="productId")
    private Product product;
}
