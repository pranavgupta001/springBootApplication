package com.TruckBooking.TruckBooking.Response;

import lombok.Data;

@Data
public class CreateLoadResponse {
	private String status;
	private String loadId;
	private String loadingPoint;
	private String loadingPointCity;
	private String loadingPointState;
	private String id;
	private String unloadingPoint;
	private String unloadingPointCity;
	private String unloadingPointState;
	private String productType;
	private String truckType;
	private String noOfTrucks;
	private String weight; 
	private String comment; //this should be an optional
	private String date;
}
