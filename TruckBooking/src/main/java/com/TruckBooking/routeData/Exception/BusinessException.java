package com.TruckBooking.routeData.Exception;

import org.springframework.stereotype.Component;


public class BusinessException extends RuntimeException {

	public BusinessException(String s) {
		super(BusinessException.generateMessage(s));
	}

	private static String generateMessage(String s) {
		return "Error" + s;
	}
}
