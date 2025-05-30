package com.example.auth.repository;

import com.example.auth.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepo extends JpaRepository<Customer,Integer> {

//      @Query("SELECT c FROM Customer c WHERE c.userName= :username ")
      Optional<Customer> findByUserName(@Param("username") String username);

}
