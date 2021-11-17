package com.webshop.Webshop.service;

import com.webshop.Webshop.model.dto.OrderDTO;
import com.webshop.Webshop.model.entity.Order;
import org.springframework.http.HttpStatus;

import java.util.List;

public interface OrderService {
    Order getOrder(Long id);
    List<Order> getOrders();
    Order createOrder(OrderDTO order);
    void updateOrder(Long id, OrderDTO order);
    HttpStatus deleteOrder(Long orderId);
    Order finalizeOrder(Long id);
}
