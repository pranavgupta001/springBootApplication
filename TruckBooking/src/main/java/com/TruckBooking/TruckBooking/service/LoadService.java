package com.TruckBooking.TruckBooking.service;

import java.util.List;
import java.util.UUID;

import com.TruckBooking.TruckBooking.entities.Load;
import com.TruckBooking.TruckBooking.model.LoadRequest;
import com.TruckBooking.TruckBooking.model.LoadResponse;

public interface LoadService {
	public LoadResponse load(LoadRequest loadrequest);
	public List<Load> findLoad(UUID ownerId);
	public LoadResponse updatePostAload(LoadRequest loadrequest); 
	public void deleteTruckRequirement(UUID id);
}
