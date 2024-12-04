package com.projeto.restaurante.repository;

import com.projeto.restaurante.identities.Request;
import com.projeto.restaurante.identities.Seating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SeatingRepository extends JpaRepository<Seating, Integer> {
    Optional<Seating> findByName(String name);

}
