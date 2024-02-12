package com.TruckBooking.Booking.Model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import com.TruckBooking.Booking.Entities.BookingData.Unit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public @Data class BookingPostRequest {

	@NotBlank(message = "Transporter Id can not be null")
	private String transporterId;
	@NotBlank(message = "Load Id can not be null")
	private String loadId;
	@NotBlank(message = "PostLoadId Id can not be null")
	private String postLoadId;

	//adding new columns
	@NotBlank(message = "loadingPointCity can not be null")
	private String loadingPointCity;
	@NotBlank(message = "unloadingPointCity can not be null")
	private String unloadingPointCity;
	@NotBlank(message = "truckNo can not be null")
	private String truckNo;
	@NotBlank(message = "driverName can not be null")
	private String driverName;
	@NotBlank(message = "phoneNum can not be null")
	private String driverPhoneNum;
	@NotBlank(message = "deviceId can not be null")
	private String deviceId;

	private Long rate;
	private Unit unitValue;

	private String lr;            // optional
	private String remarks;       // optional
	private Integer damage;        // optional
	private String companyName;   // optional

	private List<String> truckId;

	private Boolean cancel;
	private Boolean completed;
	private String bookingDate;
	private String completedDate;

	
}
