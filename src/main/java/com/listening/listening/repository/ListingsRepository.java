package com.listening.listening.repository;

import com.listening.listening.model.Listings;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ListingsRepository extends JpaRepository<Listings, UUID> {
    List<Listings> findAll();
}
