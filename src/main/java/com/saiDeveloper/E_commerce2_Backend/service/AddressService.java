package com.saiDeveloper.E_commerce2_Backend.service;

import com.saiDeveloper.E_commerce2_Backend.exception.AddressException;
import com.saiDeveloper.E_commerce2_Backend.model.Address;

import java.util.List;

public interface AddressService {

   Address saveAddress(Address address);

    List<Address> getAllAddressOfUser(Long id);

    void deleteAddress(Long id, Long addressId) throws AddressException;

    boolean isExists(String street, String pincode);
}
