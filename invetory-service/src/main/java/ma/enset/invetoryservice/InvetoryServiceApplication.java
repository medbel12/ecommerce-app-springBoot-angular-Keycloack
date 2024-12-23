package ma.enset.invetoryservice;

import ma.enset.invetoryservice.entities.Product;
import ma.enset.invetoryservice.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.UUID;

@SpringBootApplication
public class InvetoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InvetoryServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(ProductRepository productRepository) {
        return args -> {
            productRepository.save(
                    Product.builder()
                            .id("P1")
                            .name("Laptop")
                            .price(1000)
                            .quantity(10)
                            .build()
            );
            productRepository.save(
                    Product.builder()
                            .id("P2")
                            .name("Smartphone")
                            .price(500)
                            .quantity(20)
                            .build()
            );
            productRepository.save(
                    Product.builder()
                            .id("P3")
                            .name("Tablet")
                            .price(300)
                            .quantity(15)
                            .build()
            );
        };
    }

}
