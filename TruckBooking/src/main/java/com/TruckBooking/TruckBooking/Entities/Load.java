package com.TruckBooking.TruckBooking.Entities;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
	private String postLoadId;
	private String unloadingPoint;
	private String unloadingPointCity;
	private String unloadingPointState;
	private String productType;
	private String truckType;
	private String noOfTrucks;
	private String weight; 
	private String comment; //this should be an optional
	private String status;
	private String loadDate;
	private Long rate;  //optional
	
	@Enumerated(EnumType.STRING)
	private UnitValue unitValue;    //optional

	public enum UnitValue{
		PER_TON, PER_TRUCK
	}
	

	
}


