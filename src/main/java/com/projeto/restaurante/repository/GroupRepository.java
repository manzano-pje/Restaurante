package com.projeto.restaurante.repository;

import com.projeto.restaurante.identities.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GroupRepository extends JpaRepository<Group, Integer> {

    Optional<Group> findByName(String name);
//    List<Group> findAllBySection(String section);
}
