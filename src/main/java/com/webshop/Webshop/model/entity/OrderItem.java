package com.webshop.Webshop.model.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "order_item")
public class OrderItem {
    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    private Product product;

    @ManyToOne
    private Order order;
    private Long quantity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o){
        if (o == this) {
            return true;
        }

        if (!(o instanceof OrderItem)) {
            return false;
        }

        OrderItem orderItem = (OrderItem) o;

        return Objects.equals(id, orderItem.id) &&
                Objects.equals(product, orderItem.product) &&
                Objects.equals(order, orderItem.order) &&
                Objects.equals(quantity, orderItem.quantity);
    }

    @Override
    public int hashCode(){
        return Objects.hash(id, product, order, quantity);
    }
}
