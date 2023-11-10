package com.enesergen.demoproject.repository;

import com.enesergen.demoproject.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    Set<Product> searchProductsByName(String name);

    Set<Product> searchProductsByBrand(String brand);
}
