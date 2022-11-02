package by.tms.order.services;

import by.tms.order.entities.Item;
import by.tms.order.entities.Order;
import by.tms.order.entities.Product;
import by.tms.order.repositories.OrderRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDateTime;
public class OrderService {

    private static Logger log = LogManager.getLogger(OrderService.class);

    private OrderRepository orderRepository;
    private ProductService productService;

    public OrderService(OrderRepository orderRepository, ProductService productService){
        this.orderRepository = orderRepository;
        this.productService = productService;
    }

    public Order createOrder(){
        Order order = new Order();
        Order createdOrder = orderRepository.create(order);
        log.info("OrderService: Order was created: {}",order.getId());
        return createdOrder;
    }

    public void addItem(int orderId, int productId, int count){
        log.info("OrderService: Method addItem was called with orderId={}, productId={}, count={}", orderId, productId, count);
        Item item = new Item();
        item.setCount(count);
        Product product = productService.getProduct(productId);
        if(product == null) {
            log.error("OrderService: Product id={} not found.", productId);
            return;
        }
        item.setProductId(product.getId());
        item.setPrice(product.getPrice());

        Order order = getOrder(orderId);
        if(order == null) {
            log.error("OrderService: Order id={} not found.", orderId);
            return;
        }
        order.getItems().add(item);
        orderRepository.update(order);
    }

    public void finishOrder(int orderId) {
        log.info("OrderService: Method finishOrder was called with orderId={}", orderId);
       Order order = getOrder(orderId);
       if (order == null) {
           log.error("OrderService: Order not found.");
         return;
       }
        order.setFinished(true);
        order.setDateTime(LocalDateTime.now());
        orderRepository.update(order);
    }

    public Order getOrder(int orderId) {
        log.info("OrderService: Method getOrder was called with orderId={}", orderId);
        return orderRepository.getById(orderId);
    }
}
