package com.TruckBooking.TruckBooking.Exception;

public class BuisnessException extends RuntimeException{

	public BuisnessException(String s) {
		super(BuisnessException.generateMessage(s));
	}
	
	private static String generateMessage(String s) {
		return "Error"+s;
	}
}
