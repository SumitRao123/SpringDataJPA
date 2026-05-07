package com.example.demo.repository;

import com.example.demo.entity.UserAuthDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserAuthDetailsRepository extends JpaRepository<UserAuthDetails,Long> {


    Optional<UserAuthDetails> findByUsername(String username);
}
