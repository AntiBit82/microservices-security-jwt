package at.antonio.restaurantservice.dao;

import at.antonio.restaurantservice.dto.OrderResponseDTO;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class RestaurantOrderDAO {

    private Map<String, OrderResponseDTO> orders = generateRandomOrders();
    public OrderResponseDTO getOrders(String orderId) {
        return orders.get(orderId);
    }

    private Map<String, OrderResponseDTO> generateRandomOrders() {
        Map<String, OrderResponseDTO> orderMap = new HashMap<>();
        orderMap.put("1", new OrderResponseDTO("1", "Schnitzel", 1, 16, new Date(), "READY", 15));
        orderMap.put("2", new OrderResponseDTO("2", "Sake Sushi", 2, 30, new Date(), "PREPARING", 59));
        orderMap.put("3", new OrderResponseDTO("3", "Koboldeintopf", 1, 325, new Date(), "DELIVERED", 0));
        return orderMap;
    }
}
