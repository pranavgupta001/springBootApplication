package com.TruckBooking.TruckBooking.Service;

import java.util.List;
import java.util.UUID;

import com.TruckBooking.TruckBooking.Entities.Load;
import com.TruckBooking.TruckBooking.Model.LoadRequest;
import com.TruckBooking.TruckBooking.Model.LoadResponse;

public interface LoadService {
	public LoadResponse load(LoadRequest loadrequest);
	public List<Load> getLoads(String ownerId, String loadingPoint, String shipperId, String truckType);
	public LoadResponse updatePostAload(String loadId, LoadRequest loadrequest); 
	public void deleteTruckRequirement(String id);
}
