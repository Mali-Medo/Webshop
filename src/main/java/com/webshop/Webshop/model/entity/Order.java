package com.webshop.Webshop.model.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "webshop_order")
public class Order{

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne()
    private Customer customer;

    @OneToMany
    private List<OrderItem> orderItems = new ArrayList<>();
    private Status status;
    private BigDecimal total_price_hrk;
    private BigDecimal total_price_eur;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public BigDecimal getTotal_price_hrk() {
        return total_price_hrk;
    }

    public void setTotal_price_hrk(BigDecimal total_price_hrk) {
        this.total_price_hrk = total_price_hrk;
    }

    public BigDecimal getTotal_price_eur() {
        return total_price_eur;
    }

    public void setTotal_price_eur(BigDecimal total_price_eur) {
        this.total_price_eur = total_price_eur;
    }

    @Override
    public boolean equals(Object o){
        if (o == this) {
            return true;
        }

        if (!(o instanceof Order)) {
            return false;
        }

        Order order = (Order) o;

        return Objects.equals(id, order.id) &&
                Objects.equals(customer, order.customer) &&
                Objects.equals(orderItems, order.orderItems) &&
                Objects.equals(status, order.status) &&
                Objects.equals(total_price_hrk, order.total_price_hrk) &&
                Objects.equals(total_price_eur, order.total_price_eur);
    }

    @Override
    public int hashCode(){
        return Objects.hash(id, customer, orderItems, status, total_price_hrk, total_price_eur);
    }
}
