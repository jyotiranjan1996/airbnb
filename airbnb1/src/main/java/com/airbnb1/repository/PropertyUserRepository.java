package com.airbnb1.repository;

import com.airbnb1.entity.PropertyUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PropertyUserRepository extends JpaRepository<PropertyUser, Long> {
    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    Optional<PropertyUser> findByUsername(String userName);

}