package com.TruckBooking.TruckBooking.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.TruckBooking.TruckBooking.entities.Load;


@Repository
public interface LoadDao extends JpaRepository<Load , Long> {
	public List<Load> findByStatus(String status);
}
