package com.TruckBooking.TruckBooking.Entities;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Entity
@Table(name = "load")
@Data
public class Load {
	@Id
	private String loadId;
	@NotBlank(message = "Loading Point Cannot Be Empty")
	private String loadingPoint;
	@NotBlank(message = "Loading Point City Cannot Be Empty")
	private String loadingPointCity;
	@NotBlank(message = "Loading Point State Cannot Be Empty")
	private String loadingPointState;

	@NotBlank(message = "Unloading Point Cannot Be Empty")
	private String unloadingPoint;
	@NotBlank(message = "Unloading Point City Cannot Be Empty")
	private String unloadingPointCity;
	@NotBlank(message = "Unloading Point State Cannot Be Empty")
	private String unloadingPointState;

	private String postLoadId;       // optional
	private String productType;      // optional
	private String truckType;        // optional
	private String weight;           // optional
	private String postLoadDate;     // optional

	@CreationTimestamp
	public Timestamp timestamp;

	private String loadingPoint2;		//optional
	private String loadingPointCity2;	//optional
	private String loadingPointState2;	//optional
	private String unloadingPoint2;		//optional
	private String unloadingPointCity2;  //optional
	private String unloadingPointState2; //optional

	
	private String noOfTrucks;
	private String noOfTyres;
	private String LR; // optional
	private String comment; // this should be an optional

	
	private String loadingDate;
	private String publishMethod;
	private String loadingTime;

	
	private Long rate; // optional
	
	private String biddingEndDate;  //optional
	private String biddingEndTime; //optional

	@Enumerated(EnumType.STRING)
	private UnitValue unitValue; // optional

	@Enumerated(EnumType.STRING)
	public Status status;

	public enum UnitValue {
		PER_TON, PER_TRUCK
	}

	public enum Status {
		PENDING, EXPIRED, NOT_ASSIGNED, INDENT_ASSIGNED, TRANSPORTER_REJECTED, ON_GOING, COMPLETED
	}
	public enum Publish {
		CONTRACT, BIDDING
	}

}
