package com.webshop.Webshop.model.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Customer {

    @Id
    @GeneratedValue
    private Long id;

    private String first_name;
    private String last_name;
    private String email;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Order> orders = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public boolean equals(Object o){
        if (o == this) {
            return true;
        }

        if (!(o instanceof Customer)) {
            return false;
        }

        Customer customer = (Customer) o;

        return Objects.equals(id, customer.id) &&
                Objects.equals(first_name, customer.first_name) &&
                Objects.equals(last_name, customer.last_name) &&
                Objects.equals(email, customer.email) &&
                Objects.equals(orders, customer.orders);
    }

    @Override
    public int hashCode(){
        return Objects.hash(id, first_name, last_name, email, orders);
    }
}
