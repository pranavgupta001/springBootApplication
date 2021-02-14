package com.TruckBooking.TruckBooking.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.TruckBooking.TruckBooking.entities.Load;



public interface LoadDao extends JpaRepository<Load , Long> {

}
