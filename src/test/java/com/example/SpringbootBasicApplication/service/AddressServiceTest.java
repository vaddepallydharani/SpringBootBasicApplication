package com.example.SpringbootBasicApplication.service;

import com.example.SpringbootBasicApplication.entity.AddressEntity;
import com.example.SpringbootBasicApplication.repository.AddressRepository;
import com.example.SpringbootBasicApplication.service.AddressServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
public class AddressServiceTest {

    @Mock
    private AddressRepository addressRepository;

    @InjectMocks
    private AddressServiceImpl addressService;

    @Test
    public void testFindAddressById(){
        // Set up mock data
        Integer addressId = 123;
        AddressEntity expectedAddress = new AddressEntity();
        expectedAddress.setAddressId(addressId);
        expectedAddress.setCity("KNL");

        // Configure mock behavior
        Mockito.when(addressRepository.findById(addressId)).thenReturn(Optional.of(expectedAddress));
        // Call the service method
        Optional<AddressEntity> foundAddress = addressService.findAddressById(addressId);
        // Assert the results
        assertEquals(expectedAddress, foundAddress.get());
    }

    @Test
    public void testSaveAddress(){
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setAddressId(123);
        addressEntity.setCity("KNL");
        addressEntity.setZipcode("512777");

        Mockito.when(addressRepository.save(addressEntity)).thenReturn(addressEntity);
        AddressEntity actualAddress= addressService.saveAddress(addressEntity);

        assertEquals(addressEntity,actualAddress);

    }



}
