package ma.enset.invetoryservice.repositories;

import ma.enset.invetoryservice.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {
}
