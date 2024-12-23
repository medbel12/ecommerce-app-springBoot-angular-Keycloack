package ma.enset.orderservice.entities;

import jakarta.persistence.*;
import lombok.*;
import ma.enset.orderservice.enums.OrderState;

import java.time.LocalDate;
import java.util.List;

@Entity
@NoArgsConstructor @AllArgsConstructor @Getter  @Setter @Builder @ToString
@Table(name = "orders")
public class Order {
    @Id
    private String id;
    private LocalDate date;
    @Enumerated(EnumType.STRING)
    private OrderState state;
    @OneToMany(mappedBy = "order")
    private List<ProductItem> productItems;
}
