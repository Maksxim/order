package by.tms.order.repositories;

import by.tms.order.entities.Order;
import by.tms.order.services.OrderService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class OrderRepository {

    private static Logger log = LogManager.getLogger(OrderRepository.class);
    private List<Order> orders = new ArrayList<>();

    public Order create(Order order){
        log.info("OrderRepository: Method create was called");
        orders.add(order);
        return order;
    }

    public Order getById(int orderId) {
        log.info("OrderRepository: Method getById was called with orderId={}", orderId);
        Order order = null;
        for (int i = 0; i < orders.size();i++) {
            if (orderId == orders.get(i).getId()) {
                order = orders.get(i);
            }
        }
        return order;
    }

    public void update(Order order){
        log.info("OrderRepository: update was called for orderId={}", order.getId());
        Order existingOrder = getById(order.getId());
        existingOrder.setDateTime(order.getDateTime());
        existingOrder.setFinished(order.isFinished());
        existingOrder.setItems(order.getItems());
    }
}
