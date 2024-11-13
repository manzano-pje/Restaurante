package com.projeto.restaurante.repository;

import com.projeto.restaurante.dto.ProductReturnDto;
import com.projeto.restaurante.identities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    Optional<Product> findBynameProduct(String nameProduct);


//    @Query("SELECT new com.projeto.restaurante.dto.ProductReturnDTO(p.nameProduct, g.name, p.salePrice, p.costPrice, p.unidadeDeMedida, p.registrationDate, p.stock, p.minimumStock) " +
//            "FROM Product p JOIN Group g ON p.productGroup = g.id")
//    List<Product> findAllWithGroup();
}
