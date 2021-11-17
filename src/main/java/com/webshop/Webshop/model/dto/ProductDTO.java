package com.webshop.Webshop.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.webshop.Webshop.model.entity.Product;
import com.webshop.Webshop.model.entity.builder.ProductBuilder;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

public class ProductDTO {

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
    @JsonProperty("isAvailable")
    private Boolean isAvailable;

    public ProductDTO(){

    }

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

    public Boolean getAvailable() {
        return isAvailable;
    }

    public void setAvailable(Boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public Product fromDTO(){
        return ProductBuilder
                .aProduct()
                .withCode(this.code)
                .withDescription(this.description)
                .withName(this.name)
                .withPrice_hrk(this.price_hrk)
                .withIsAvailable(this.isAvailable)
                .build();
    }
}
