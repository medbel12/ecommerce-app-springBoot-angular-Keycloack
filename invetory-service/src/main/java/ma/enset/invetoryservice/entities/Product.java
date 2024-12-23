package ma.enset.invetoryservice.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;


@Entity
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@Builder


public class Product {
    @Id
    private String id;
    private String name;
    private double price;
    private double quantity;
}
