package com.webshop.Webshop.service;

import com.webshop.Webshop.model.dto.ProductDTO;
import com.webshop.Webshop.model.entity.Order;
import com.webshop.Webshop.model.entity.Product;
import com.webshop.Webshop.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{
    private final ProductRepository productRepository;
    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @Override
    public Product getProduct(Long id) {
        Optional<Product> product = productRepository.findById(id);
        return product.orElse(null);
    }

    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product createProduct(ProductDTO product) {
        return productRepository.save(product.fromDTO());
    }

    @Override
    public void updateProduct(Long id, ProductDTO productDTO) {
        try{
            Optional<Product> productToUpdate = this.productRepository.findById(id);

            productToUpdate.get().setName(productDTO.getName());
            productToUpdate.get().setDescription(productDTO.getDescription());
            productToUpdate.get().setCode(productDTO.getCode());
            productToUpdate.get().setPrice_hrk(productDTO.getPrice_hrk());
            productToUpdate.get().setIsAvailable(productDTO.getAvailable());

            productRepository.save(productToUpdate.get());
        }catch (Throwable t){
            logger.error(t.getMessage());
            throw new ResponseStatusException(
                    HttpStatus.I_AM_A_TEAPOT,
                    "Product with id: " + id + " not updated!");
        }
    }

    @Override
    public HttpStatus deleteProduct(Long productId) {
        try{
            productRepository.deleteById(productId);
            return HttpStatus.OK;
        }catch (Throwable t){
            logger.error(t.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Cannot delete");
        }
    }
}
