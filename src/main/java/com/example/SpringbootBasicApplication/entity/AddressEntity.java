package com.example.SpringbootBasicApplication.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="Address")
public class AddressEntity {

    @Id
    @Column(name="address_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer addressId;

    @Column(name="street")
    private String street;

    @Column(name="city")
    private String city;

    @Column(name="state")
    private String state;

    @Column(name="country")
    private String country;

    @Column(name="zip_code")
    private String zipcode;


}
