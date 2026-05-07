package com.example.demo.controller;

import com.example.demo.dto.LoginDTO;
import com.example.demo.dto.SignupDTO;
import com.example.demo.entity.UserAuthDetails;
import com.example.demo.service.UserAuthDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    public UserAuthDetailsService userAuthDetailsService;
    @Autowired
    private AuthenticationManager authenticationManager;


    @Autowired
    public PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody SignupDTO signupDTO){
//       userAuthDetails.setPassword(passwordEncoder.encode(userAuthDetails.getPassword()));
       userAuthDetailsService.saveUserDetail(signupDTO);
       return ResponseEntity.ok("User saved");
    }

    @GetMapping("/greet")
    public ResponseEntity<?> greets(){
        return ResponseEntity.ok("Sasd");
    }

    @PostMapping("/logIn")
    public ResponseEntity<?> loginUser(@RequestBody LoginDTO loginDTO){
         String token = userAuthDetailsService.authenticateUser(loginDTO,authenticationManager);

         return new ResponseEntity<>(token, HttpStatus.ACCEPTED);
    }



}
