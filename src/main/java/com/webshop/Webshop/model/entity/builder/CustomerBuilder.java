package com.webshop.Webshop.model.entity.builder;

import com.webshop.Webshop.model.entity.Customer;
import com.webshop.Webshop.model.entity.Order;

import java.util.ArrayList;
import java.util.List;

public final class CustomerBuilder {
    private Long id;
    private String first_name;
    private String last_name;
    private String email;
    private List<Order> orders = new ArrayList<>();

    private CustomerBuilder() {
    }

    public static CustomerBuilder aCustomer() {
        return new CustomerBuilder();
    }

    public CustomerBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public CustomerBuilder withFirst_name(String first_name) {
        this.first_name = first_name;
        return this;
    }

    public CustomerBuilder withLast_name(String last_name) {
        this.last_name = last_name;
        return this;
    }

    public CustomerBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public CustomerBuilder withOrders(List<Order> orders) {
        this.orders = orders;
        return this;
    }

    public Customer build() {
        Customer customer = new Customer();
        customer.setId(id);
        customer.setFirst_name(first_name);
        customer.setLast_name(last_name);
        customer.setEmail(email);
        customer.setOrders(orders);
        return customer;
    }
}
