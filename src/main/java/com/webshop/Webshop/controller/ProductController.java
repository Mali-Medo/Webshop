package com.webshop.Webshop.controller;

import com.webshop.Webshop.model.dto.ProductDTO;
import com.webshop.Webshop.model.entity.Product;
import com.webshop.Webshop.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping("/get/{id}")
    public Product getProduct(@PathVariable("id") Long id){
        Product product = productService.getProduct(id);
        if(product != null){
            return product;
        }
        logger.error("product should not be null");
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found!");
    }

    @GetMapping("/get")
    public List<Product> getProducts(){
        List<Product> products = productService.getProducts();
        if(products != null){
            return products;
        }
        logger.error("products should not be null");
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Products not found!");
    }

    @PostMapping("/create")
    public Product createProduct(@Valid @RequestBody ProductDTO productDTO){
        return productService.createProduct(productDTO);
    }

    @PutMapping("/update/{id}")
    public void updateProduct(@PathVariable("id") Long id, ProductDTO productDTO){
        Product product = productService.getProduct(id);
        if(product != null){
            productService.updateProduct(id, productDTO);
        }
        logger.error("product should not be null");
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found!");
    }

    @DeleteMapping("/delete/{id}")
    public HttpStatus deleteProduct(@PathVariable("id") Long id){
        Product product = productService.getProduct(id);
        if(product != null){
            return productService.deleteProduct(id);
        }
        logger.error("product should not be null");
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found!");
    }
}
