package com.saiDeveloper.E_commerce2_Backend.repo;

import com.saiDeveloper.E_commerce2_Backend.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AddressRepo extends JpaRepository<Address,Long> {

    @Query("Select a From Address a Where a.user.id = :userId")
    List<Address> findAllByUserId(Long userId);

    @Query("Select a From Address a Where a.streetAddress = :streetAddress And a.zipCode = :zipCode")
    Optional<Address> findByStreetAndPinCode(@Param("streetAddress") String streetAddress,
                                             @Param("zipCode") String zipCode);

}
