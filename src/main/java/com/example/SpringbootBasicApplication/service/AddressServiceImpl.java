package com.example.SpringbootBasicApplication.service;

import com.example.SpringbootBasicApplication.entity.AddressEntity;
import com.example.SpringbootBasicApplication.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    AddressRepository addressRepository;

    public Optional<AddressEntity> findAddressById(Integer addressId){

        return addressRepository.findById(addressId);
    }

    public AddressEntity saveAddress(AddressEntity addressEntity) {
        AddressEntity save = addressRepository.save(addressEntity);
        return save;
    }
}
