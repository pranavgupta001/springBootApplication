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


