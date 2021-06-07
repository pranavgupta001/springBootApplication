package com.TruckBooking.TruckBooking.Entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="load")
@Data
public class Load {
	@Id
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
	private String status;
	private String date;
	

	
}


