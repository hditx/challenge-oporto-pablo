package com.listening.listening.service;

import com.listening.listening.dto.SpecialPricesDeleteDto;
import com.listening.listening.model.Listings;
import com.listening.listening.model.SpecialPrices;
import com.listening.listening.repository.ListingsRepository;
import com.listening.listening.repository.SpecialPricesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SpecialPricesService {
    @Autowired
    private SpecialPricesRepository specialPricesRepository;
    @Autowired
    private ListingsRepository listingsRepository;

    public SpecialPrices insert(UUID uuid, SpecialPrices specialPrices) {
        Listings listings = listingsRepository.getOne(uuid);
        specialPrices.setListing_id(listings);
        return specialPricesRepository.save(specialPrices);
    }

    public SpecialPricesDeleteDto delete(UUID uuid, UUID uuidespecial) {
        SpecialPrices specialPrices = specialPricesRepository.getOne(uuidespecial);
        Listings listings = listingsRepository.getOne(uuid);
        specialPrices.setListing_id(listings);
        SpecialPricesDeleteDto specialPricesDeleteDto = new SpecialPricesDeleteDto();
        specialPricesDeleteDto.setId(specialPrices.getId());
        specialPricesRepository.delete(specialPrices);
        return specialPricesDeleteDto;
    }
}
