package com.example.luggagekeeper.repository;

import com.example.luggagekeeper.models.Luggage;
import com.example.luggagekeeper.models.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LuggageRepository extends JpaRepository<Luggage,Long> {
    Page<Luggage> findAll(Pageable pageable);

}
