package com.enesergen.demoproject.service;

import com.enesergen.demoproject.model.Product;
import com.enesergen.demoproject.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Set<Product> searchProductsByName(String name) {
        return productRepository.searchProductsByName(name);
    }

    public Set<Product> searchProductsByBrand(String brand) {
        return productRepository.searchProductsByBrand(brand);
    }

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(Product product) {
        return productRepository.save(product);
    }

    public boolean deleteProduct(String objId) {
        productRepository.deleteById(objId);
        var isExists = productRepository.existsById(objId);
        return !isExists;
    }

    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }
}
