package com.TruckBooking.TruckBooking.entities;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="load")
@Data
public class Load {
	@Id
	@GeneratedValue
	private UUID id;
	private UUID ownerId;
	private String loadingPoint;
	
	private String unloadingPoint;
	private String productType;
	private String truckType;
	private String noOfTrucks;
	private String weight; 
	private String comment; //this should be an optional
	private String status;
	

	
}


