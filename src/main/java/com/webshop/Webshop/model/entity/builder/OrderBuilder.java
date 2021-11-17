package com.webshop.Webshop.model.entity.builder;

import com.webshop.Webshop.model.entity.Customer;
import com.webshop.Webshop.model.entity.Order;
import com.webshop.Webshop.model.entity.OrderItem;
import com.webshop.Webshop.model.entity.Status;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public final class OrderBuilder{
    private Long id;
    private Customer customer;
    private List<OrderItem> orderItems = new ArrayList<>();
    private Status status;
    private BigDecimal total_price_hrk;
    private BigDecimal total_price_eur;

    private OrderBuilder() {
    }

    public static OrderBuilder anOrder() {
        return new OrderBuilder();
    }

    public OrderBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public OrderBuilder withCustomer(Customer customer) {
        this.customer = customer;
        return this;
    }

    public OrderBuilder withOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
        return this;
    }

    public OrderBuilder withStatus(Status status) {
        this.status = status;
        return this;
    }

    public OrderBuilder withTotal_price_hrk(BigDecimal total_price_hrk) {
        this.total_price_hrk = total_price_hrk;
        return this;
    }

    public OrderBuilder withTotal_price_eur(BigDecimal total_price_eur) {
        this.total_price_eur = total_price_eur;
        return this;
    }

    public Order build() {
        Order order = new Order();
        order.setId(id);
        order.setCustomer(customer);
        order.setOrderItems(orderItems);
        order.setStatus(status);
        order.setTotal_price_hrk(total_price_hrk);
        order.setTotal_price_eur(total_price_eur);
        return order;
    }
}
