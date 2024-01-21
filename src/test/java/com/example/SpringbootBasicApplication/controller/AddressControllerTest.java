package com.example.SpringbootBasicApplication.controller;

import com.example.SpringbootBasicApplication.entity.AddressEntity;
import com.example.SpringbootBasicApplication.service.AddressService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(AddressController.class)
public class AddressControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AddressService addressService;

    @Test
    public void testFindAddressById() throws Exception {

        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setAddressId(123);
        addressEntity.setCity("KNL");

        Mockito.when(addressService.findAddressById(any())).thenReturn(Optional.of(addressEntity));

        mockMvc.perform(get("/address-service/address/{addressId}",1).accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void testFindAddressByIdWithNotFound() throws Exception {

        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setAddressId(123);
        addressEntity.setCity("KNL");

        Mockito.when(addressService.findAddressById(any())).thenReturn(Optional.of(addressEntity));

        mockMvc.perform(get("/address-service/address").accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isNotFound());
    }

    @Test
    public void testFindAddressByIdWithMethodNotAllowed() throws Exception {

        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setAddressId(123);
        addressEntity.setCity("KNL");

        Mockito.when(addressService.findAddressById(any())).thenReturn(Optional.of(addressEntity));

        mockMvc.perform(post("/address-service/address/{addressId}",1).accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isMethodNotAllowed());
    }
    @Test
    public void testSaveAddress() throws Exception {

        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setAddressId(123);
        addressEntity.setCity("KNL");
        addressEntity.setZipcode("512777");

        Mockito.when(addressService.saveAddress(any())).thenReturn(addressEntity);

        mockMvc.perform(post("/address-service/saveAddress").content(asJsonString(addressEntity))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void testSaveAddressWithBadRequest() throws Exception {

        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setAddressId(123);
        addressEntity.setCity("KNL");

        Mockito.when(addressService.saveAddress(any())).thenReturn(addressEntity);

        mockMvc.perform(post("/address-service/saveAddress").content(asJsonString(addressEntity))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isBadRequest());
    }


    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
