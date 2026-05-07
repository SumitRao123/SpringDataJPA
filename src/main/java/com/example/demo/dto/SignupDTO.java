package com.example.demo.dto;

import com.example.demo.entity.Type.RoleType;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class SignupDTO {
   private String username;
   private String password;

   private RoleType roleType ;
}
