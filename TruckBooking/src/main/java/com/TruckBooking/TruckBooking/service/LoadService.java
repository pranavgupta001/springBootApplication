package com.TruckBooking.TruckBooking.service;

import java.util.List;

import com.TruckBooking.TruckBooking.entities.Load;

public interface LoadService {
	public List<Load> searchTrucks(Load load);
	public List<Load> findLoad();
	public Load updateSearchTruck(Load load); 
	public void deleteTruckRequirement(Load load);
}
