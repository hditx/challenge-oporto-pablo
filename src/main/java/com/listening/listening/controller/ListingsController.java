package com.listening.listening.controller;

import com.listening.listening.dto.*;
import com.listening.listening.model.Listings;
import com.listening.listening.model.SpecialPrices;
import com.listening.listening.service.ListingsService;
import com.listening.listening.service.SpecialPricesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/listings")
@CrossOrigin("*")
public class ListingsController {

    @Autowired
    private ListingsService listingsService;
    @Autowired
    private SpecialPricesService specialPricesService;

    @PostMapping()
    public ResponseEntity<ListingsDto> insert(@RequestBody Listings listings){
        return ResponseEntity.ok(listingsService.insert(listings));
    }

    @GetMapping()
    public List<ListingsDto> getAll () {
        return listingsService.findAll();
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<ListingsDto> getById (@PathVariable(value = "uuid") UUID uuid){
        return ResponseEntity.ok(listingsService.findById(uuid));
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<Listings> update (@PathVariable(value = "uuid") UUID uuid, @RequestBody Listings listings) {
        if(listings == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(listingsService.update(uuid, listings));
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<ListingsDeleteDto> delete (@PathVariable(value = "uuid") UUID uuid) {
        return ResponseEntity.ok(listingsService.delete(uuid));
    }

    @PostMapping("/{uuid}/special-prices")
    public ResponseEntity<SpecialPrices> insertSpecialPrices (@PathVariable(value = "uuid") UUID uuid, @RequestBody SpecialPrices specialPrices) {
        return ResponseEntity.ok(specialPricesService.insert(uuid, specialPrices));
    }

    @DeleteMapping("/{uuid}/special-prices/{uuidspecial}")
    public ResponseEntity<SpecialPricesDeleteDto> deleteSpecialPrices(@PathVariable(value = "uuid") UUID uuid, @PathVariable(value = "uuidspecial") UUID uuidspecial ) {
        return ResponseEntity.ok(specialPricesService.delete(uuid, uuidspecial));
    }

    @GetMapping("/{uuid}/checkout")
    public ResponseEntity<CheckDto> getCheckout(@PathVariable(value = "uuid") UUID uuid, @RequestBody CheckDateDto checkDateDto) {
        CheckDto checkDto = listingsService.checkOut(uuid, checkDateDto);
        if (checkDto == null) return ResponseEntity.badRequest().contentType(MediaType.APPLICATION_JSON).header("Fail", "Fecha no valida").build();
        return ResponseEntity.ok(checkDto);
    }
}
