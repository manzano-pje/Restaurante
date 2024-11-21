package com.projeto.restaurante.repository;

import com.projeto.restaurante.identities.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {

    Optional<Company> findByCnpj(String cnpj);

    Optional<Company> findFirstByOrderByIdAsc();
}
