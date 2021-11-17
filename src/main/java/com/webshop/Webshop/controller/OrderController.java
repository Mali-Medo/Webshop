package com.webshop.Webshop.controller;

import com.webshop.Webshop.model.dto.OrderDTO;
import com.webshop.Webshop.model.entity.Order;
import com.webshop.Webshop.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }

    @GetMapping("/get/{id}")
    public Order getOrder(@PathVariable("id") Long id){
        Order order = orderService.getOrder(id);
        if(order != null){
            return order;
        }
        logger.error("Order should not be null");
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found!");
    }

    @GetMapping("/get")
    public List<Order> getOrders(){
        List<Order> orders = orderService.getOrders();
        if(orders != null){
            return orders;
        }
        logger.error("Orders should not be null");
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Orders not found!");
    }

    @PostMapping("/create")
    public Order createProduct(@Valid @RequestBody OrderDTO orderDTO){
        return orderService.createOrder(orderDTO);
    }

    @PutMapping("/update/{id}")
    public void updateOrder(@PathVariable("id") Long id, OrderDTO orderDTO){
        Order order = orderService.getOrder(id);
        if(order != null){
            orderService.updateOrder(id, orderDTO);
        }
        logger.error("Order should not be null");
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found!");
    }

    @DeleteMapping("/delete/{id}")
    public HttpStatus deleteOrder(@PathVariable("id") Long id){
        Order order = orderService.getOrder(id);
        if(order != null){
            return orderService.deleteOrder(id);
        }
        logger.error("Order should not be null");
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found!");
    }
}