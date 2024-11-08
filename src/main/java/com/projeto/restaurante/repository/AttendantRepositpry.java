package com.projeto.restaurante.repository;

import com.projeto.restaurante.identities.Attendant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AttendantRepositpry extends JpaRepository<Attendant, Integer> {

    Optional<Attendant> findByName(String name);

    @Query(value = "SELECT * FROM tb_attendant WHERE name LIKE CONCAT('%', :name, '%')", nativeQuery = true)
    List<Attendant> findAllByName(@Param("name") String name);
}
