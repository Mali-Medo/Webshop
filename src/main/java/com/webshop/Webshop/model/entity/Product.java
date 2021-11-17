package com.webshop.Webshop.model.entity;

import org.springframework.validation.annotation.Validated;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Objects;

@Validated
@Entity
public class Product {

    @Id
    @GeneratedValue
    private Long id;

    @Size(min = 10, max=10)
    @NotNull
    private String code;

    @NotNull
    private String name;

    @NotNull
    @Min(0)
    private BigDecimal price_hrk;

    private String description;

    @NotNull
    private Boolean isAvailable;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice_hrk() {
        return price_hrk;
    }

    public void setPrice_hrk(BigDecimal price_hrk) {
        this.price_hrk = price_hrk;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(Boolean available) {
        isAvailable = available;
    }

    @Override
    public boolean equals(Object o){
        if (o == this) {
            return true;
        }

        if (!(o instanceof Product)) {
            return false;
        }

        Product product = (Product) o;

        return Objects.equals(id, product.id) &&
                Objects.equals(code, product.code) &&
                Objects.equals(name, product.name) &&
                Objects.equals(price_hrk, product.price_hrk) &&
                Objects.equals(description, product.description) &&
                Objects.equals(isAvailable, product.isAvailable);
    }

    @Override
    public int hashCode(){
        return Objects.hash(id, code, name, price_hrk, description, isAvailable);
    }
}
