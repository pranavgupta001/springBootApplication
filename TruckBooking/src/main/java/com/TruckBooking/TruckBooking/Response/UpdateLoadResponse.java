package com.TruckBooking.TruckBooking.Response;

import java.sql.Timestamp;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.TruckBooking.TruckBooking.Entities.Load.Status;

import lombok.Data;

@Data
public class UpdateLoadResponse {

	private String loadId;
	private String loadingPoint;
	private String loadingPointCity;
	private String loadingPointState;
	private String unloadingPoint;
	private String unloadingPointCity;
	private String unloadingPointState;
	private String postLoadId;
	private String productType;
	private String truckType;
	private String noOfTrucks;
	private String noOfTyres;
	private String weight;
	private String loadDate;
	private String postLoadDate;
	public Status status;
	private String comment; // this should be an optional
	private Long rate;

	@Enumerated(EnumType.STRING)
	private UnitValue unitValue;

	public enum UnitValue {
		PER_TON, PER_TRUCK
	}

	public Timestamp timestamp;
}
