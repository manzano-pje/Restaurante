package com.projeto.restaurante.repository;

import com.projeto.restaurante.identities.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<Request, Integer> {

    @Query("SELECT r FROM Request r JOIN FETCH r.requestAttendant JOIN FETCH r.requestSeating s WHERE s.id = :seatingId AND s.status = true AND r.aberto = true")
    List<Request> findRequestsBySeatingIdAndStatusTrue(@Param("seatingId") int seatingId);
}

