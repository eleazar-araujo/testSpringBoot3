package com.example.boot4fixture.customer;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {

    Optional<Customer> findByEmail(String email);

    @EntityGraph(attributePaths = "tickets")
    @Query("select c from Customer c where c.email = :email")
    Optional<Customer> findDetailedByEmail(@Param("email") String email);
}