package com.TruckBooking.TruckBooking.Model;

import lombok.Data;

public @Data class LoadRequest {

	private String loadingPoint;
	private String loadingPointState;
	private String loadingPointCity;
	private String id;
	private String unloadingPoint;
	private String unloadingPointState;
	private String unloadingPointCity;
	private String productType;
	private String truckType;
	private String noOfTrucks;
	private String weight; 
	private String comment; //this should be an optional
	private String date;
	private String status;
}
