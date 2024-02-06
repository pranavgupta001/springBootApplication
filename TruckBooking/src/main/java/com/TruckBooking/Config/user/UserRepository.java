package com.TruckBooking.Config.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    Optional<UserEntity> findByUsername(String username);
}