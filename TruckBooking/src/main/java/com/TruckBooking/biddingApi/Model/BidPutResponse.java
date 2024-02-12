package com.TruckBooking.biddingApi.Model;

import java.util.List;

import com.TruckBooking.biddingApi.Entities.BiddingData.Unit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public @Data class BidPutResponse {

	private String status;
	private String bidId;
	private String transporterId;
	private String loadId;
	private Integer transporterBid;
	private Integer shipperBid;
	private Unit unitValue;
	private List<String> truckId;
	private Boolean transporterApproval;
	private Boolean shipperApproval;
	private String biddingDate;
	private String companyName;

}
