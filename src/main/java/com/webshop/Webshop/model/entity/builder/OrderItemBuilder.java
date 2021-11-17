package com.webshop.Webshop.model.entity.builder;

import com.webshop.Webshop.model.entity.Order;
import com.webshop.Webshop.model.entity.OrderItem;
import com.webshop.Webshop.model.entity.Product;

public final class OrderItemBuilder {
    private Long id;
    private Product product;
    private Order order;
    private Long quantity;

    private OrderItemBuilder() {
    }

    public static OrderItemBuilder anOrderItem() {
        return new OrderItemBuilder();
    }

    public OrderItemBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public OrderItemBuilder withProduct(Product product) {
        this.product = product;
        return this;
    }

    public OrderItemBuilder withOrder(Order order) {
        this.order = order;
        return this;
    }

    public OrderItemBuilder withQuantity(Long quantity) {
        this.quantity = quantity;
        return this;
    }

    public OrderItem build() {
        OrderItem orderItem = new OrderItem();
        orderItem.setId(id);
        orderItem.setProduct(product);
        orderItem.setOrder(order);
        orderItem.setQuantity(quantity);
        return orderItem;
    }
}
