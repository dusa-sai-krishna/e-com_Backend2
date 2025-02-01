package com.saiDeveloper.E_commerce2_Backend.service;

import com.saiDeveloper.E_commerce2_Backend.request.UserLoginRequest;
import com.saiDeveloper.E_commerce2_Backend.request.UserRegisterRequest;
import com.saiDeveloper.E_commerce2_Backend.exception.UserException;
import com.saiDeveloper.E_commerce2_Backend.model.User;
import com.saiDeveloper.E_commerce2_Backend.repo.UserRepo;
import com.saiDeveloper.E_commerce2_Backend.response.AuthResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepo repo;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    @Autowired
    private AuthenticationManager manager;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private CartService cartService;
    @Autowired
    private AuthResponse response;

    public AuthResponse saveUser(UserRegisterRequest req) throws UserException {

        if(repo.findByEmail(req.getEmail()).isPresent()){
            log.error("User already exists with email:{}", req.getEmail());
            throw new UserException("User already exists with email:"+req.getEmail());

        }
        else {

            User user = new User();
            user.setFirstName(req.getFirstName());
            user.setLastName(req.getLastName());
            user.setEmail(req.getEmail());
            user.setMobile(req.getMobile());

            user.setPassword(encoder.encode(req.getPassword()));
            repo.save(user);
            response.setMessage("User registered successfully");
            cartService.createCart(user);
            log.info("User registered successfully with email:{}", req.getEmail());// when user is registered, create an associated cart too.

        }
        return response;
    }

    public AuthResponse login(UserLoginRequest req) throws UserException {

        findByEmail(req.getEmail());

        try {
            //throws exception if user not found
            Authentication authentication = manager
                    .authenticate(new UsernamePasswordAuthenticationToken(req.getEmail(),req.getPassword()));

            if (authentication.isAuthenticated()) {
                response.setJwt(jwtService.generateToken(req.getEmail()));
                response.setMessage("Login successful");
            }
        } catch (AuthenticationException e) {
            throw new UserException("Invalid Password");
        }



        return response;
    }

    public User findById(Long id) throws UserException{
        User user=repo.findById(id).orElse(null);
        if(user!=null){
            log.info("User found with id:{}",id);
            return user;
        }
        else{
            log.error("User not found with id:{}",id);
            throw new UserException("User not found with id:"+id);
        }
    }

    public User findByEmail(String email) throws UserException{
        User user=repo.findByEmail(email).orElse(null);
        if(user!=null){
            log.info("User found with email:{}",email);
            return user;
        }
        else{
            log.error("User not found with email:{}",email);
            throw new UserException("User not found with email:"+email);
        }

    }

    public User findByJWT(String jwt) throws UserException{
        log.error("Calling jwt Service to find email for jwt: {}",jwt);
        String token = jwt.split(" ")[1];
        String email=jwtService.extractEmail(token);
        return findByEmail(email);
    }

    public User updateUser(User user) throws UserException{
        if(repo.findByEmail(user.getEmail()).isPresent()){
            log.info("User has been updated!!");
             return repo.save(user);
        }
        else{
            throw new UserException("User to be updated not found with id:"+user.getId());
        }
    }

    public String deleteUser(User user){
        if(repo.findByEmail(user.getEmail()).isPresent()){
            repo.delete(repo.findByEmail(user.getEmail()).get());
            return "User deleted successfully";
        }
        else{
            return "User not found";
        }
    }

}
