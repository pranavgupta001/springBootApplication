package com.TruckBooking.TruckBooking.Service;

import java.util.List;


import com.TruckBooking.TruckBooking.Entities.Load;

public interface LoadService {
	public Load addLoad(Load load);
	public List<Load> getLoads(
			Integer pageNo, String loadingPointCity,
			String unloadingPointCity, String shipperId, 
			String truckType, String date, boolean suggestedLoads
			);
	public Load updateLoad(String loadId, Load loadrequest); 
	public Load getLoad(String loadId);
	public void deleteLoad(String loadId);
}
