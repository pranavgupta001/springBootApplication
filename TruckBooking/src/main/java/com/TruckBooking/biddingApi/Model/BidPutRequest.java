package com.TruckBooking.biddingApi.Model;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.TruckBooking.biddingApi.Entities.BiddingData.Unit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public @Data class BidPutRequest {

	@NotBlank(message = "Transporter Id can not be null")
	private String transporterId;
	@NotNull(message = "TransporterBid can not be null")
	private Integer transporterBid;
	private Integer shipperBid;
	private Unit unitValue;
	private List<String> truckId;

	private Boolean transporterApproval;
	private Boolean shipperApproval;

	private String biddingDate;
	private String companyName;
}
