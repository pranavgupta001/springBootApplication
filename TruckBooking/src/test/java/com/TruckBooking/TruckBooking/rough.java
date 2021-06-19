package com.TruckBooking.TruckBooking;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import com.TruckBooking.TruckBooking.Dao.LoadDao;
import com.TruckBooking.TruckBooking.Entities.Load;

@TestMethodOrder(OrderAnnotation.class)
public class rough {
	
	
	public static void show(int n)
	{
		System.err.println("++++++++++++++++++++++++++++++++++++");
		System.err.println("Working: " + n);
		System.err.println("++++++++++++++++++++++++++++++++++++");
	}
	
	@Test
	public void demo()
	{
		show(55);
	}
}
