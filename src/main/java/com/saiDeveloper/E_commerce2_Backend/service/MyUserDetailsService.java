package com.saiDeveloper.E_commerce2_Backend.service;

import com.saiDeveloper.E_commerce2_Backend.controller.UserPrincipal;
import com.saiDeveloper.E_commerce2_Backend.model.User;
import com.saiDeveloper.E_commerce2_Backend.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo repo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = repo.findByEmail(email).orElse(null);
        if(user==null){
            System.out.println(email);
            throw new UsernameNotFoundException("404 user not found");
        }
        return new UserPrincipal(user);
    }
}

