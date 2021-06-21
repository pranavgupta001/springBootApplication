package com.TruckBooking.TruckBooking.Model;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import lombok.Data;

@Data
public class LoadRequest {

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
	private String status;
	private Long rate;
	
	@Enumerated(EnumType.STRING)
	private UnitValue unitValue;

	public enum UnitValue{
		PER_TON, PER_TRUCK
	}
}
