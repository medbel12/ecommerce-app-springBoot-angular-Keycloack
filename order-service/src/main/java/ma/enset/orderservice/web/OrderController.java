package ma.enset.orderservice.web;

import ma.enset.orderservice.entities.Order;
import ma.enset.orderservice.repository.OrderRepository;
import ma.enset.orderservice.restclients.InventoryRestClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderController {

    private OrderRepository orderRepository;
    private InventoryRestClient inventoryRestClient;

    public OrderController(OrderRepository orderRepository, InventoryRestClient inventoryRestClient) {
        this.orderRepository = orderRepository;
        this.inventoryRestClient = inventoryRestClient;
    }

    @GetMapping("/orders")
    public List<Order> getAllOrders(){
        List<Order> orders = orderRepository.findAll();
        orders.forEach(order ->{
            order.getProductItems().forEach(productItem -> {
                productItem.setProduct(inventoryRestClient.getProductById(productItem.getProductId()));
            });
        });
        return orders;
    }
    @GetMapping("/orders/{id}")
    private Order getOrderById(@PathVariable String id){
        Order order = orderRepository.findById(id).get();
        order.getProductItems().forEach(productItem -> {
            productItem.setProduct(inventoryRestClient.getProductById(productItem.getProductId()));
        });
        return order;



    }
}
