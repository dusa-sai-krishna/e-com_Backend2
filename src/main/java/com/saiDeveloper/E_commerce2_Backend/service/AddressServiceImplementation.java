package com.saiDeveloper.E_commerce2_Backend.service;

import com.saiDeveloper.E_commerce2_Backend.model.Address;
import com.saiDeveloper.E_commerce2_Backend.repo.AddressRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImplementation implements AddressService {

    @Autowired
    private AddressRepo repo;
    @Override
    public Address saveAddress(Address address) {
        return repo.save(address);
    }
}
