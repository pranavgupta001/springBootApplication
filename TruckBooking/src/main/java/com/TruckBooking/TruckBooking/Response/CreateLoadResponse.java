package com.TruckBooking.TruckBooking.Response;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import lombok.Data;

@Data
public class CreateLoadResponse {
	
	private String loadId;
	private String status;
	private String loadingPoint;
	private String loadingPointCity;
	private String loadingPointState;
	private String postLoadId;
	private String unloadingPoint;
	private String unloadingPointCity;
	private String unloadingPointState;
	private String productType;
	private String truckType;
	private String noOfTrucks;
	private String weight; 
	private String comment; //this should be an optional
	private String loadDate;

	
	private Long rate;
	
	@Enumerated(EnumType.STRING)
	private UnitValue unitValue;

	public enum UnitValue{
		PER_TON, PER_TRUCK
	}

}
