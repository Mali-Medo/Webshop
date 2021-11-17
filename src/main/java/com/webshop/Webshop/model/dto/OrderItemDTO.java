package com.webshop.Webshop.model.dto;

import com.webshop.Webshop.model.entity.Order;
import com.webshop.Webshop.model.entity.OrderItem;
import com.webshop.Webshop.model.entity.Product;
import com.webshop.Webshop.model.entity.builder.OrderItemBuilder;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

public class OrderItemDTO {

    private Long id;
    @NotNull
    private Long product_id;
    @NotNull
    private Order order;
    @NotNull
    private Long quantity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Long product_id) {
        this.product_id = product_id;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public static OrderItem fromDTO(OrderItemDTO orderItemDTO, Product product, Order order){
        return OrderItemBuilder.anOrderItem()
                .withId(orderItemDTO.id)
                .withProduct(product)
                .withOrder(orderItemDTO.order)
                .withQuantity(orderItemDTO.quantity)
                .build();
    }

    //one
    public static OrderItemDTO toOrderItemDTO(OrderItem orderItem){
        OrderItemDTO orderItemDTO = new OrderItemDTO();
        orderItemDTO.id = orderItem.getId();
        orderItemDTO.product_id = orderItem.getProduct().getId();
        orderItemDTO.quantity = orderItem.getQuantity();

        return orderItemDTO;
    }

    //all
    public static List<OrderItemDTO> toOrderItemsDTO(List<OrderItem> orderItems){
        return orderItems.stream()
                .map(OrderItemDTO::toOrderItemDTO)
                .collect(Collectors.toList());
    }


}
