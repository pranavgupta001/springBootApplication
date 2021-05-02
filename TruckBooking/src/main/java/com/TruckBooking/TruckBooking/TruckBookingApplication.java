package com.TruckBooking.TruckBooking;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.format.annotation.DateTimeFormat;

@SpringBootApplication
public class TruckBookingApplication {

	public static void main(String[] args) {
		SpringApplication.run(TruckBookingApplication.class, args);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd MMM, yyyy");
        LocalDateTime now = LocalDateTime.now();
        System.out.println(dtf.format(now).toString());
	}


}
