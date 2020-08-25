package com.listening.listening.service;

import com.listening.listening.dto.*;
import com.listening.listening.model.Listings;
import com.listening.listening.model.SpecialPrices;
import com.listening.listening.model.Users;
import com.listening.listening.repository.ListingsRepository;
import com.listening.listening.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

@Service
public class ListingsService {
    @Autowired
    private ListingsRepository listingsRepository;
    @Autowired
    private UsersRepository usersRepository;

    public static Date localDateToDate(LocalDate local) {
        Instant instant = Instant.from(local.atStartOfDay(ZoneId.of("UTC-03:00")));
        return Date.from(instant);
    }

    public static Date stringToDate(String date) {
        String year = date.substring(0, 4);
        String month = date.substring(5, 7);
        String day = date.substring(8, 10);
        LocalDate localDate = LocalDate.of(Integer.valueOf(year), Integer.valueOf(month), Integer.valueOf(day));
        return localDateToDate(localDate);
    }

    public static <T> List<T> convertSetToList(Set<T> set) {
        List<T> list = new ArrayList<>(set);
        return list;
    }

    private List<SpecialPricesDto> getDtoToEntitySpecialPrices (List<SpecialPrices> specialPricesList) {
        List<SpecialPricesDto> specialPricesDtoList = new ArrayList<>();
        for (SpecialPrices specialPrices : specialPricesList) {
            SpecialPricesDto specialPricesDto = new SpecialPricesDto();
            specialPricesDto.setDate(specialPrices.getDate());
            specialPricesDto.setPrice(specialPrices.getPrice());
            specialPricesDtoList.add(specialPricesDto);
        }
        return specialPricesDtoList;
    }
    private ListingsDto getDtoToEntity (Listings listings){
        ListingsDto listingsDto = new ListingsDto();
        listingsDto.setId(listings.getId());
        if (listings.getOwner_id() != null) listingsDto.setOwner_id(listings.getOwner_id().getId());
        listingsDto.setAdults(listings.getAdults());
        listingsDto.setBase_price(listings.getBase_price());
        listingsDto.setChildren(listings.getChildren());
        listingsDto.setCleaning_fee(listings.getCleaning_fee());
        listingsDto.setDescription(listings.getDescription());
        listingsDto.setImage_url(listings.getImage_url());
        listingsDto.setIs_pets_allowed(listings.getIs_pets_allowed());
        listingsDto.setMonthly_discount(listings.getMonthly_discount());
        listingsDto.setName(listings.getName());
        listingsDto.setSlug(listings.getSlug());
        if (listings.getSpecial_prices() != null) {
            listingsDto.setSpecial_prices(this.getDtoToEntitySpecialPrices(this.convertSetToList(listings.getSpecial_prices())));
        }
        listingsDto.setWeekly_discount(listings.getWeekly_discount());
        return listingsDto;
    }

    private CheckDto getCheckDto(UUID uuid, Date checkIn, Date checkOut, int dias) {
        Listings listings = listingsRepository.getOne(uuid);
        Double discount = 0.0;
        CheckDto checkDto = new CheckDto();
        checkDto.setNights_count(dias);
        checkDto.setCleaning_fee(listings.getCleaning_fee());
        if (listings.getMonthly_discount() != null) discount += listings.getMonthly_discount();
        if (listings.getWeekly_discount() != null) discount += listings.getWeekly_discount();
        checkDto.setDiscount(discount);
        Double cost = 0.0;
        for (SpecialPrices specialPrices : listings.getSpecial_prices()) {
            if (specialPrices.getDate().after(checkIn) && specialPrices.getDate().before(checkOut)) {
                cost += specialPrices.getPrice();
                dias--;
            }
        }
        if (dias > 0) cost += listings.getBase_price() * dias;
        checkDto.setNights_cost(cost);
        checkDto.setTotal((checkDto.getNights_cost() + checkDto.getCleaning_fee()) * checkDto.getDiscount());
        return checkDto;
    }

    public List<ListingsDto> findAll() {
        List<Listings> listingsList = listingsRepository.findAll();
        List<ListingsDto> listingsDtoList = new ArrayList<>();
        for (Listings listings : listingsList) {
            listingsDtoList.add(this.getDtoToEntity(listings));
        }
        return listingsDtoList;
    }

    public ListingsDto findById(UUID uuid) {
        return this.getDtoToEntity(listingsRepository.getOne(uuid));
    }

    public ListingsDeleteDto delete(UUID uuid) {
        Listings listings = listingsRepository.getOne(uuid);
        ListingsDeleteDto dto = new ListingsDeleteDto();
        dto.setId(listings.getId());
        listingsRepository.delete(listings);
        return dto;
    }

    public ListingsDto insert(Listings listings) {
        Users users = new Users();
        users.setName(listings.getName());
        users = usersRepository.save(users);
        listings.setOwner_id(users);
        if (listings.getName().contains(" ")) {
            listings.setSlug("revolutionize-" +listings.getName().toLowerCase());
        } else {
            listings.setSlug(listings.getName().replace(' ', '-').toLowerCase());
        }
        ListingsDto listingsDto = this.getDtoToEntity(listingsRepository.save(listings));
        return listingsDto;
    }

    public Listings update(UUID uuid, Listings listings){
        Listings listingsPersistence = listingsRepository.getOne(uuid);
        listingsPersistence.setName(listings.getName());
        listingsPersistence.setDescription(listings.getDescription());
        listingsPersistence.setAdults(listings.getAdults());
        listingsPersistence.setChildren(listings.getChildren());
        listingsPersistence.setIs_pets_allowed(listings.getIs_pets_allowed());
        listingsPersistence.setBase_price(listings.getBase_price());
        listingsPersistence.setCleaning_fee(listings.getCleaning_fee());
        listingsPersistence.setImage_url(listings.getImage_url());
        listingsPersistence.setWeekly_discount(listings.getWeekly_discount());
        listingsPersistence.setMonthly_discount(listings.getMonthly_discount());
        return listingsRepository.save(listingsPersistence);
    }

    public CheckDto checkOut(UUID uuid, CheckDateDto checkDateDto) {
        Date checkIn = this.stringToDate(checkDateDto.getCheckin());
        Date checkOut = this.stringToDate(checkDateDto.getCheckout());
        Date fechaActual = new Date();
        if (checkIn.after(checkOut)) return null;
        int dias = (int) ((checkOut.getTime() - checkIn.getTime())/86400000);
        if (dias > 28) return null;
        if (fechaActual.after(checkIn) || fechaActual.after(checkOut)) return null;
        CheckDto checkDto = this.getCheckDto(uuid, checkIn, checkOut, dias);
        return checkDto;
    }
}
