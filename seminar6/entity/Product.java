package pard.server.com.seminar6.entity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "color")
    private String color;

    @Column(name = "price")
    private int price;

    @Column(name = "count")
    private int count;

    @Column(name = "sellable")
    private Boolean sellable;

    public void updateQuantity(int count) {
        this.count = count;
    }
}
