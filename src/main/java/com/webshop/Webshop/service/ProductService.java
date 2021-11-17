package com.webshop.Webshop.service;

import com.webshop.Webshop.model.dto.ProductDTO;
import com.webshop.Webshop.model.entity.Product;
import org.springframework.http.HttpStatus;

import java.util.List;

public interface ProductService {
    Product getProduct(Long id);
    List<Product> getProducts();
    Product createProduct(ProductDTO product);
    void updateProduct(Long id, ProductDTO product);
    HttpStatus deleteProduct(Long productId);
}
