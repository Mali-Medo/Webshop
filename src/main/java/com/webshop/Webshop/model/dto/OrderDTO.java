package com.webshop.Webshop.model.dto;

import com.webshop.Webshop.model.entity.Customer;
import com.webshop.Webshop.model.entity.Order;
import com.webshop.Webshop.model.entity.Status;
import com.webshop.Webshop.model.entity.builder.OrderBuilder;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

public class OrderDTO {

    private Long id;
    @NotNull
    private Long customer_id;
    @NotNull
    private List<OrderItemDTO> orderItemsDTO;
    private Status status;
    private BigDecimal total_price_hrk;
    private BigDecimal total_price_eur;

    public OrderDTO(){

    }

    public static OrderDTO toOrderDTO(Order order){
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.id = order.getId();
        orderDTO.customer_id = order.getCustomer().getId();
        orderDTO.orderItemsDTO = OrderItemDTO.toOrderItemsDTO(order.getOrderItems());
        orderDTO.status = order.getStatus();
        orderDTO.total_price_hrk = order.getTotal_price_hrk();
        orderDTO.total_price_eur = order.getTotal_price_eur();
        return orderDTO;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(Long customer_id) {
        this.customer_id = customer_id;
    }

    public List<OrderItemDTO> getOrderItemsDTO() {
        return orderItemsDTO;
    }

    public void setOrderItemsDTO(List<OrderItemDTO> orderItemsDTO) {
        this.orderItemsDTO = orderItemsDTO;
    }

    public Status getStatus() {
        return status;
    }

    public BigDecimal getTotal_price_hrk() {
        return total_price_hrk;
    }

    public BigDecimal getTotal_price_eur() {
        return total_price_eur;
    }

    public Order fromDTO(Customer customer){
        return OrderBuilder.anOrder().withCustomer(customer).build();
    }
}
