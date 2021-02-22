package com.TruckBooking.TruckBooking.model;

import java.util.UUID;

import lombok.Data;

public @Data class LoadRequest {

	private UUID ownerId;
	private String loadingPoint;
	private String unloadingPoint;
	private String productType;
	private String truckType;
	private String noOfTrucks;
	private String weight; 
	private String comment; 
}
