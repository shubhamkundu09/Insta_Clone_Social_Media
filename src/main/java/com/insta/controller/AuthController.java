package com.insta.controller;

import com.insta.config.JwtProvider;
import com.insta.modal.User;
import com.insta.repo.UserRepo;
import com.insta.request.LoginRequest;
import com.insta.response.AuthResponse;
import com.insta.service.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CustomUserDetailService customUserDetailService;

    @PostMapping("/signup")
    public AuthResponse addUser(@RequestBody User user){

        User isExist = userRepo.findByEmail(user.getEmail());
        if (isExist!=null){
            throw new RuntimeException("Email already Exists.........");
        }
        User newuser = new User();

        newuser.setFirstName(user.getFirstName());
        newuser.setLastName(user.getLastName());
        newuser.setPassword(passwordEncoder.encode(user.getPassword()));
        newuser.setEmail(user.getEmail());
        newuser.setGender(user.getGender());

        User saveduser = userRepo.save(newuser);

        Authentication authentication = new UsernamePasswordAuthenticationToken(saveduser.getEmail(), saveduser.getPassword());
        String token = JwtProvider.generateToken(authentication);

        return new AuthResponse(token, "Register Success");


    }

    @PostMapping("/signin")
    public AuthResponse signin(@RequestBody LoginRequest loginRequest){
        Authentication authentication = authenticate(loginRequest.getEmail(),loginRequest.getPassword());
        String token = JwtProvider.generateToken(authentication);
        return new AuthResponse(token, "Success Login");

    }

    private Authentication authenticate(String email, String password) {
        UserDetails userDetails = customUserDetailService.loadUserByUsername(email);
        if (userDetails==null){
            throw new BadCredentialsException("User not Found..........");
        }
        if (!passwordEncoder.matches(password, userDetails.getPassword())){
            throw new BadCredentialsException("Password is wrong........");
        }
        return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
    }


}
