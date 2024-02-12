package com.TruckBooking.biddingApi.Model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.NotBlank;

import com.TruckBooking.biddingApi.Entities.BiddingData.Unit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public @Data class BidPostRequest {
	
	@NotBlank(message = "Transporter Id can not be null")
	private String transporterId;
	@NotBlank(message = "Load Id can not be null")
	private String loadId;
	@NotNull(message = "TransporterBid can not be null")
	private Integer transporterBid;
	private Integer shipperBid;

	private Unit unitValue;
	private List<String> truckId;
	private String biddingDate;
	private String companyName;
	
		Boolean transporterApproval;
		private Boolean shipperApproval;
		@Column(name = "transporterId")
		@ElementCollection(targetClass = String.class)
		private List<String> rank;
}
