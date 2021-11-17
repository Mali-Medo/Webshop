package com.webshop.Webshop.model.entity.builder;

import com.webshop.Webshop.model.entity.Product;

import java.math.BigDecimal;

public final class ProductBuilder {
    private Long id;
    private String code;
    private String name;
    private BigDecimal price_hrk;
    private String description;
    private Boolean isAvailable;

    private ProductBuilder() {
    }

    public static ProductBuilder aProduct() {
        return new ProductBuilder();
    }

    public ProductBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public ProductBuilder withCode(String code) {
        this.code = code;
        return this;
    }

    public ProductBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public ProductBuilder withPrice_hrk(BigDecimal price_hrk) {
        this.price_hrk = price_hrk;
        return this;
    }

    public ProductBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public ProductBuilder withIsAvailable(Boolean isAvailable) {
        this.isAvailable = isAvailable;
        return this;
    }

    public void setAvailable(Boolean available) {
        isAvailable = available;
    }

    public Product build() {
        Product product = new Product();
        product.setId(id);
        product.setCode(code);
        product.setName(name);
        product.setPrice_hrk(price_hrk);
        product.setDescription(description);
        product.setIsAvailable(isAvailable);
        return product;
    }
}
