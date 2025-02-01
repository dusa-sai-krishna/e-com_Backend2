package com.saiDeveloper.E_commerce2_Backend.service;

import com.saiDeveloper.E_commerce2_Backend.exception.AddressException;
import com.saiDeveloper.E_commerce2_Backend.model.Address;
import com.saiDeveloper.E_commerce2_Backend.model.User;
import com.saiDeveloper.E_commerce2_Backend.repo.AddressRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImplementation implements AddressService {

    @Autowired
    private AddressRepo repo;
    @Override
    public Address saveAddress(Address address) {
        return repo.save(address);
    }

    @Override
    public List<Address> getAllAddressOfUser(Long userId){
        return repo.findAllByUserId(userId);
    }

    @Override
    public void deleteAddress(Long userId, Long addressId) throws AddressException {

        Address address = findAddressById(addressId);
        if(address.getUser().getId().equals(userId)){
            User user = address.getUser();
            user.getAddress().remove(address);
            repo.deleteById(addressId);
        }
        else{
            throw new AddressException("You can't delete other users address");
        }

    }

    @Override
    public boolean isExists(String street, String pincode){

        Address address = repo.findByStreetAndPinCode(street.toLowerCase().trim(),
                            pincode.toLowerCase().trim())
                .orElse(null);
        return address != null;
    }

    public Address findAddressById(Long addressId) throws AddressException {
       Address address=repo.findById(addressId).orElse(null);
       if(address==null) throw new AddressException("Address not found with id:" + addressId);
       return address;
    }

}
