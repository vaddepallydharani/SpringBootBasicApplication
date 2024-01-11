package com.example.SpringbootBasicApplication.service;

import com.example.SpringbootBasicApplication.entity.AddressEntity;

import java.util.Optional;

public interface AddressService {
    public Optional<AddressEntity> findAddressById(Integer addressId);

    AddressEntity saveAddress(AddressEntity address);
}
