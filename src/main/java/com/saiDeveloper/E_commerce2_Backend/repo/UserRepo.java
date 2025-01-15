package com.saiDeveloper.E_commerce2_Backend.repo;

import com.saiDeveloper.E_commerce2_Backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    void deleteByEmail(String email);

}
