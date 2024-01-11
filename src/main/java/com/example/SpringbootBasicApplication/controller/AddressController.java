package com.example.SpringbootBasicApplication.controller;

import com.example.SpringbootBasicApplication.entity.AddressEntity;
import com.example.SpringbootBasicApplication.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/address-service")
public class AddressController {
    @Autowired
    AddressService addressService;

    @GetMapping("address/{addressId}")
    public Optional<AddressEntity> findAddressById(@PathVariable("addressId") Integer addressId){
        return addressService.findAddressById(addressId);
    }

    @PostMapping("/saveAddress")
    public AddressEntity saveAddress(@RequestBody AddressEntity addressEntity){
        return addressService.saveAddress(addressEntity);
    }
}
