package com.TruckBooking.TruckBooking.Service;

import java.util.List;
import java.util.UUID;

import com.TruckBooking.TruckBooking.Entities.Load;
import com.TruckBooking.TruckBooking.Model.LoadRequest;
import com.TruckBooking.TruckBooking.Model.LoadResponse;

public interface LoadService {
	public LoadResponse addLoad(LoadRequest loadrequest);
	public List<Load> getLoads(String loadingPoint, String shipperId, String truckType, String date);
	public LoadResponse updateLoad(String loadId, LoadRequest loadrequest); 
	public Load getLoad(String loadId);
	public LoadResponse deleteLoad(String loadId);
}
