package com.example.demo.service;

import com.example.demo.dto.LoginDTO;
import com.example.demo.dto.SignupDTO;
import com.example.demo.entity.UserAuthDetails;
import com.example.demo.repository.UserAuthDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserAuthDetailsService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Autowired
    public UserAuthDetailsRepository userAuthDetailsRepository;

    @Autowired
    public JWTService jwtService;

    public boolean saveUserDetail(SignupDTO signupDTO){
        UserAuthDetails userAuthDetails = new UserAuthDetails();
        userAuthDetails.setUsername(signupDTO.getUsername());
        userAuthDetails.setPassword(passwordEncoder.encode(signupDTO.getPassword()));
        userAuthDetails.setRole(signupDTO.getRoleType());
        userAuthDetailsRepository.save(userAuthDetails);
        return true;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{

        return userAuthDetailsRepository.findByUsername(username).orElseThrow(()->new UsernameNotFoundException("User not found"));
    }

    public String authenticateUser(LoginDTO loginDTO,AuthenticationManager authenticationManager){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUsername(),loginDTO.getPassword()));
        UserAuthDetails userAuthDetails = (UserAuthDetails)  authentication.getPrincipal();
        String token = jwtService.createToken(userAuthDetails);

        return token;
    }


}
