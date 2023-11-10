package com.enesergen.demoproject.controller;

import com.enesergen.demoproject.model.Product;
import com.enesergen.demoproject.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/user/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/search-product-name")
    public ResponseEntity<Set<Product>> searchProductsByName(@RequestBody String name) {
        return ResponseEntity.ok(productService.searchProductsByName(name));
    }

    @PostMapping("/search-product-brand")
    public ResponseEntity<Set<Product>> searchProductsByBrand(@RequestBody String brand) {
        return ResponseEntity.ok(productService.searchProductsByBrand(brand));
    }

    @PostMapping("/add-product")
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        return ResponseEntity.ok(productService.addProduct(product));
    }

    @PostMapping("/update-product")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
        return ResponseEntity.ok(productService.updateProduct(product));
    }

    @PostMapping("/delete-product")
    public ResponseEntity<Boolean> deleteProduct(@RequestBody String objId) {
        return ResponseEntity.ok(productService.deleteProduct(objId));
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProduct());
    }

}
