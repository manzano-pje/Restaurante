package com.projeto.restaurante.repository;

import com.projeto.restaurante.identities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    Optional<Product> findByNameProduct(String nameProduct);

    List<Product> findByProductGroup_id(int poductGroupId);

}
