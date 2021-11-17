package com.webshop.Webshop.service;

import com.webshop.Webshop.model.dto.OrderDTO;
import com.webshop.Webshop.model.dto.OrderItemDTO;
import com.webshop.Webshop.model.entity.*;
import com.webshop.Webshop.model.hnb.hnbPOJO;
import com.webshop.Webshop.repository.CustomerRepository;
import com.webshop.Webshop.repository.OrderRepository;
import com.webshop.Webshop.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService{
    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    OrderServiceImpl(CustomerRepository customerRepository, OrderRepository orderRepository, ProductRepository productRepository){
        this.customerRepository = customerRepository;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    @Override
    public Order getOrder(Long id) {
        Optional<Order> order = orderRepository.findById(id);
        return order.orElse(null);
    }

    @Override
    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order createOrder(OrderDTO orderDTO) {
            Optional<Customer> customer = this.customerRepository.findById(orderDTO.getCustomer_id());
            if(customer.isEmpty()){
                logger.error("Customer not found");
                throw new NullPointerException("Customer not found!");
            }
            Map<Long, Product> productMap = this.productRepository
                                            .findAllByIdAndIsAvailable(orderDTO.getOrderItemsDTO()
                                                                        .stream()
                                                                        .map(OrderItemDTO::getProduct_id)
                                                                        .collect(Collectors.toList()))
                                                                                .stream()
                                                                                .collect(Collectors.toMap(Product::getId, n -> n));
            if(productMap.size() != orderDTO.getOrderItemsDTO().size()){
                throw new NullPointerException("Number of products cannot be different than number of items in order");
            }
            Order order = filterOrderItem(productMap, orderDTO, customer.get());
            order = orderRepository.save(order);
            return order;
    }

    private Order filterOrderItem(Map<Long, Product> productMap, OrderDTO orderDTO, Customer customer) {
        Order order = orderDTO.fromDTO(customer);
        List<OrderItem> orderItems = new ArrayList<>();
        orderDTO.getOrderItemsDTO().forEach(orderItemDTO -> {
            Product product = productMap.get(orderItemDTO.getProduct_id());
            orderItems.add(OrderItemDTO.fromDTO(orderItemDTO, product, order));
        });

        order.setOrderItems(orderItems);
        order.setStatus(Status.DRAFT);

        return order;
    }

    @Override
    public void updateOrder(Long id, OrderDTO orderDTO) {
        try{
            Optional<Order> order = this.orderRepository.findById(id);

            Map<Long, Product> productMap = this.productRepository
                    .findAllByIdAndIsAvailable(orderDTO.getOrderItemsDTO()
                            .stream()
                            .map(OrderItemDTO::getProduct_id)
                            .collect(Collectors.toList()))
                    .stream()
                    .collect(Collectors.toMap(Product::getId, n -> n));

            orderDTO.getOrderItemsDTO().forEach(orderItemDTO -> {
                Product product = productMap.get(orderItemDTO.getProduct_id());
                if(product != null){
                    if(orderItemDTO.getQuantity() > 0){
                        orderItemDTO.setQuantity(orderItemDTO.getQuantity());
                    }
                    else{
                        order.get().getOrderItems()
                                .remove(order.get()
                                        .getOrderItems()
                                        .stream()
                                        .filter(n -> Objects.equals(n.getId(), orderItemDTO.getId()))
                                        .findFirst()
                                        .get());
                    }
                }
                else{
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
                }
            });
        }catch (Throwable t){
            logger.error(t.getMessage());
            throw new ResponseStatusException(
                    HttpStatus.I_AM_A_TEAPOT,
                    "Order with id: " + id + " not updated!");
        }
    }

    @Override
    public HttpStatus deleteOrder(Long orderId) {
        try{
            orderRepository.deleteById(orderId);
            return HttpStatus.OK;
        }catch (Throwable t){
            logger.error(t.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Cannot delete");
        }
    }

    @Override
    public Order finalizeOrder(Long id) {
        String url = "https://api.hnb.hr/tecajn/v2?valuta=EUR";
        RestTemplate restTemplate = new RestTemplate();
        hnbPOJO[] hnbPojo = restTemplate.getForObject(url, hnbPOJO[].class);
        Optional<Order> order = orderRepository.findById(id);

        if (order.isPresent()) {
            assert hnbPojo != null;
            calculatePrice(order.get(), new BigDecimal(hnbPojo[0].getSrednji_tecaj().replace(',', '.')));
            orderRepository.save(order.get());
        } else {
            throw new NullPointerException("Order not found!");
        }
        order.get().setStatus(Status.SUBMITTED);

        return order.orElse(null);
    }

    private void calculatePrice(Order order, BigDecimal tecaj){
        order.setTotal_price_hrk(BigDecimal.ZERO);
        order.setTotal_price_eur(BigDecimal.ZERO);

        order.getOrderItems().forEach(orderItem -> {
            order.setTotal_price_hrk(order.getTotal_price_hrk()
                                            .add(orderItem.getProduct().getPrice_hrk()
                                            .multiply(new BigDecimal(orderItem.getQuantity()))));
            order.setTotal_price_eur(order.getTotal_price_eur()
                                            .add(orderItem.getProduct().getPrice_hrk()
                                            .multiply(new BigDecimal(orderItem.getQuantity()))));
        });
        order.setTotal_price_eur(order.getTotal_price_eur()
                                        .divide(tecaj, RoundingMode.HALF_UP));
    }
}