package com.example.SpringbootBasicApplication.repository;

import com.example.SpringbootBasicApplication.entity.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<AddressEntity,Integer > {
}
