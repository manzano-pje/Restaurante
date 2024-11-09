package com.projeto.restaurante.repository;

import com.projeto.restaurante.identities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    Optional<Product> findBynameProduct(String nameProduct);
}