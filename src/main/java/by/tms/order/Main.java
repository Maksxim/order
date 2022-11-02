package by.tms.order;

import by.tms.order.entities.Order;
import by.tms.order.entities.Product;
import by.tms.order.repositories.OrderRepository;
import by.tms.order.repositories.ProductRepository;
import by.tms.order.services.OrderService;
import by.tms.order.services.ProductService;

public class Main {

    public static void main(String[] args) {

        OrderRepository orderRepository = new OrderRepository();
        ProductRepository productRepository = new ProductRepository();

        ProductService productService = new ProductService(productRepository);
        OrderService orderService = new OrderService(orderRepository, productService);

        Product iphone = new Product(1,"iphone 14",1000);
        Product xiaomi = new Product(2,"xiaomi redmi note pro 12",250);
        Product computer = new Product(3,"MultiGame",1000);
        Product computer1 = new Product(4,"X552M",300);
        productService.createProduct(iphone);
        productService.createProduct(xiaomi);

        productService.delete(xiaomi.getId());
        productService.createProduct(computer);
        iphone.setPrice(900);
        productService.updateProduct(iphone);

        Order newOrder = orderService.createOrder();

        orderService.addItem(newOrder.getId(),iphone.getId(),3);
        orderService.addItem(newOrder.getId(),computer.getId(),5);

        orderService.finishOrder(newOrder.getId());

        Order order = orderService.getOrder(newOrder.getId());
        System.out.println(order);
    }
}
