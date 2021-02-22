package com.TruckBooking.TruckBooking.dao;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.TruckBooking.TruckBooking.entities.Load;


@Repository
public interface LoadDao extends JpaRepository<Load , UUID> {
	public List<Load> findByStatus(String status);
	
	public List<Load> findAllByOwnerId(UUID ownerId);
}
