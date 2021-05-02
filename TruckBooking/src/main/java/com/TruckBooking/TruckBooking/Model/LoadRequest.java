package com.TruckBooking.TruckBooking.Model;

import java.util.UUID;

import lombok.Data;

public @Data class LoadRequest {

	private String id;
	private String ownerId;
	private String loadingPoint;
	private String shipperId;
	private String unloadingPoint;
	private String productType;
	private String truckType;
	private String noOfTrucks;
	private String weight; 
	private String comment; //this should be an optional
	private String status;
	private String date;
}
