package com.saiDeveloper.E_commerce2_Backend.service;

import com.saiDeveloper.E_commerce2_Backend.exception.UserException;
import com.saiDeveloper.E_commerce2_Backend.model.User;
import com.saiDeveloper.E_commerce2_Backend.repo.UserRepo;
import com.saiDeveloper.E_commerce2_Backend.response.AuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo repo;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    @Autowired
    private AuthenticationManager manager;
    @Autowired
    private JwtService jwtService;
@Autowired
private AuthResponse response;

    public AuthResponse saveUser(User user){

        if(repo.findByEmail(user.getEmail()).isPresent()){
            response.setMessage("User already exists");

        }
        else {

            user.setPassword(encoder.encode(user.getPassword()));
            //For verification only
            System.out.println(user.getPassword());
            repo.save(user);
            response.setMessage("User registered successfully");

        }
        return response;
    }

    public AuthResponse login(User user){

        if(repo.findByEmail(user.getEmail()).orElse(null)!=null){

            Authentication authentication = manager
                    .authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(),user.getPassword()));

            if (authentication.isAuthenticated()) {
                response.setJwt(jwtService.generateToken(user.getEmail()));
                response.setMessage("Login successful");
            }
            else {
                response.setMessage("Invalid Credentials");
            }
        }
        response.setMessage("User not found");
        return response;
    }

    public String findById(Long id){
        User user=repo.findById(id).orElse(null);
        if(user!=null){return user.toString();}else{return "User not found"; }
    }

    public String findByEmail(String email){
        User user = repo.findByEmail(email).orElse(null);
        if(user!=null){return user.toString();}else{return "User not found"; }
    }

    public String findByJWT(String jwt){
        User user=repo.findByEmail(jwtService.extractEmail(jwt)).orElse(null);
        if(user!=null){return user.toString();}else{return "User not found"; }
    }

//    public String updateUser(User user){
//        User oldUser = repo.findByEmail(user.getEmail()).orElse(null);
//        if(oldUser!=null){
//            user.setId(oldUser.getId());
//            repo.save(user);
//            return user.toString();
//        }else{
//           return "User not found";
//        }
//    }

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
