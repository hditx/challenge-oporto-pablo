package com.listening.listening.repository;

import com.listening.listening.model.SpecialPrices;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpecialPricesRepository extends JpaRepository<SpecialPrices, UUID> {
}
