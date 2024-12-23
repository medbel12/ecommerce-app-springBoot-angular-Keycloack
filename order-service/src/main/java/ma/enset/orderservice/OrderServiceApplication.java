package ma.enset.orderservice;


import ma.enset.orderservice.entities.Order;
import ma.enset.orderservice.entities.ProductItem;
import ma.enset.orderservice.enums.OrderState;
import ma.enset.orderservice.model.Product;
import ma.enset.orderservice.repository.OrderRepository;
import ma.enset.orderservice.repository.ProductItemRepository;
import ma.enset.orderservice.restclients.InventoryRestClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@SpringBootApplication
@EnableFeignClients
public class OrderServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class, args);
    }
    @Bean
    CommandLineRunner commandLineRunner(OrderRepository orderRepository,
                                        ProductItemRepository productItemRepository,
                                        InventoryRestClient inventoryRestClient) {
        return args -> {
            //List<Product> allproducts = inventoryRestClient.getAllProducts();
            List<String> producrtIds = List.of("P1","P2","P3");
            for(int i=0;i<5;i++){
                Order order = Order.builder()
                        .id(UUID.randomUUID().toString())
                        .date(LocalDate.now())
                        .state(OrderState.values()[new Random().nextInt(OrderState.values().length)])
                        .build();
                Order savedOrder =orderRepository.save(order);

                producrtIds.forEach(productId -> {
                    ProductItem productItem = ProductItem.builder()
                            .productId(productId)
                            .price(1000 + new Random().nextInt(10000))
                            .quantity(new Random().nextInt(100))
                            .order(savedOrder)
                            .build();

                    productItemRepository.save(productItem);
                });

            }

        };
    }

}
