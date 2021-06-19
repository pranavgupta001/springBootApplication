package com.TruckBooking.TruckBooking;

import com.TruckBooking.TruckBooking.Constants.CommonConstants;
import com.TruckBooking.TruckBooking.Model.LoadRequest;

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

@TestMethodOrder(OrderAnnotation.class)
public class ApiTestRestAssured {
	
	static String loadid1;
	static String loadid2;
	static String loadid3;
	
	@BeforeAll
	public static void setup() throws Exception {
		RestAssured.baseURI = CommonConstants.BASEURI;
		
		LoadRequest loadrequest1 = new LoadRequest("Nagpur", "Maharashtra", "Nagpur", "id:1", "Raipur", "Chhattisgarh",
				"Raipur", "Gold", "OPEN_HALF_BODY", "6", "100kg", "added comment", "01/01/20", "Done");
        String inputJson1 = mapToJson(loadrequest1);
        Response response1 = (Response) RestAssured.given().header("", "").body(inputJson1).header("accept", "application/json")
				.header("Content-Type", "application/json").post().then().extract().response();
        
        LoadRequest loadrequest2 = new LoadRequest("Nagpur", "Maharashtra", "Nagpur", "id:1", "Raipur", "Chhattisgarh",
				"Raipur", "Gold", "STANDARD_CONTAINER", "6", "100kg", "added comment", "01/02/20", "Done");
		String inputJson2 = mapToJson(loadrequest2);
		Response response2 = (Response) RestAssured.given().header("", "").body(inputJson2).header("accept", "application/json")
				.header("Content-Type", "application/json").post().then().extract().response();
		
		LoadRequest loadrequest3 = new LoadRequest("Nagpur", "Maharashtra", "Jaipur", "id:2", "Raipur", "Chhattisgarh",
				"Raipur", "Gold", "OPEN_HALF_BODY", "6", "100kg", "added comment", "01/03/20", "Done");
		String inputJson3 = mapToJson(loadrequest3);
		Response response3 = (Response) RestAssured.given().header("", "").body(inputJson3).header("accept", "application/json").header("Content-Type", "application/json").post().then().extract().response();
		
		loadid1 = response1.jsonPath().getString("loadId");
		loadid2 = response2.jsonPath().getString("loadId");
		loadid3 = response3.jsonPath().getString("loadId");
		
		System.err.println("++++++++++++++++++++++++++++++++++++4");
		System.err.println("o " + loadid1);
		System.err.println("o " + loadid2);
		System.err.println("o " + loadid3);
		System.err.println("++++++++++++++++++++++++++++++++++++");
		
		assertEquals(200, response1.statusCode());
		assertEquals(CommonConstants.pending, response1.jsonPath().getString("status"));
		System.err.println("++++++++++++++++++++++++++++++++++++4");
		System.err.println("o " + response1.statusCode());
		System.err.println("o " + response1.jsonPath().getString("id"));
		System.err.println("o " + response1.jsonPath().getString("status"));
		System.err.println("++++++++++++++++++++++++++++++++++++");
		
		
		
	}
	
	private static String mapToJson(Object object) throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_NUMBERS_FOR_ENUMS, false);
		objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
		objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT, true);
		objectMapper.configure(DeserializationFeature.READ_ENUMS_USING_TO_STRING, true);

		return objectMapper.writeValueAsString(object);
	}
	
	//////////////////////////////////**addLoad()**////////////////////////
	
	
	@Test
	@Order(3)
	public void addloadsuccess() throws Exception {
		LoadRequest loadrequest = new LoadRequest("Nagpur", "Maharashtra", "Nagpur", "id:1", "Raipur", "Chhattisgarh",
				"Raipur", "Gold", "OPEN_HALF_BODY", "6", "100kg", "added comment", "01/01/20", "Done");

		String inputJson = mapToJson(loadrequest);

		Response response = RestAssured.given().header("", "").body(inputJson).header("accept", "application/json")
				.header("Content-Type", "application/json").post().then().extract().response();

		assertEquals(200, response.statusCode());
		assertEquals(CommonConstants.pending, response.jsonPath().getString("status"));
		
		System.err.println("++++++++++++++++++++++++++++++++++++4");
		System.err.println("o " + response.statusCode());
		System.err.println("o " + response.jsonPath().getString("loadId"));
		System.err.println("o " + response.jsonPath().getString("loadingPoint"));
		System.err.println("o " + response.jsonPath().getString("status"));
		System.err.println("++++++++++++++++++++++++++++++++++++");
		
		Response response1 = RestAssured.given().header("", "").delete("/" + response.jsonPath().getString("loadId")).then().extract().response();
		System.err.println("++++++++++++++++++++++++++++++++++++17");
		System.err.println("o " + response1.statusCode());
		System.err.println("o " + response1.jsonPath().getString("status"));
		System.err.println("++++++++++++++++++++++++++++++++++++");
		
	}
	//loading point null
	@Test
	@Order(4)
	public void addloadingpointnull() throws Exception {

		LoadRequest loadrequest = new LoadRequest(null, "Maharashtra", "Nagpur", "id:1", "Raipur", "Chhattisgarh",
				"Raipur", "Gold", "OPEN_HALF_BODY", "6", "100kg", "added comment", "01/01/20", "Done");

		String inputJson = mapToJson(loadrequest);

		Response response = RestAssured.given().header("", "").body(inputJson).header("accept", "application/json")
				.header("Content-Type", "application/json").post().then().extract().response();

		assertEquals(200, response.statusCode());
		assertEquals(CommonConstants.loadingError, response.jsonPath().getString("status"));
		
		System.err.println("++++++++++++++++++++++++++++++++++++4");
		System.err.println("o " + response.statusCode());
		System.err.println("o " + response.jsonPath().getString("status"));
		System.err.println("++++++++++++++++++++++++++++++++++++");

	}
	
	
	//loading point state null
	@Test
	@Order(5)
	public void addloadingpointstatenull() throws Exception {

		LoadRequest loadrequest = new LoadRequest("Maharashtra", null, "Nagpur", "id:1", "Raipur", "Chhattisgarh",
				"Raipur", "Gold", "OPEN_HALF_BODY", "6", "100kg", "added comment", "01/01/20", "Done");

		String inputJson = mapToJson(loadrequest);

		Response response = RestAssured.given().header("", "").body(inputJson).header("accept", "application/json")
				.header("Content-Type", "application/json").post().then().extract().response();

		assertEquals(200, response.statusCode());
		assertEquals(CommonConstants.loadingStateError, response.jsonPath().getString("status"));
		
		System.err.println("++++++++++++++++++++++++++++++++++++4");
		System.err.println("o " + response.statusCode());
		System.err.println("o " + response.jsonPath().getString("status"));
		System.err.println("++++++++++++++++++++++++++++++++++++");

	}
	
	//loading point city null
	@Test
	@Order(6)
	public void addloadingpointcitynull() throws Exception {

		LoadRequest loadrequest = new LoadRequest("Nagpur", "Maharashtra", null, "id:1", "Raipur", "Chhattisgarh",
				"Raipur", "Gold", "OPEN_HALF_BODY", "6", "100kg", "added comment", "01/01/20", "Done");

		String inputJson = mapToJson(loadrequest);

		Response response = RestAssured.given().header("", "").body(inputJson).header("accept", "application/json")
				.header("Content-Type", "application/json").post().then().extract().response();

		assertEquals(200, response.statusCode());
		assertEquals(CommonConstants.loadingCityError, response.jsonPath().getString("status"));
		
		System.err.println("++++++++++++++++++++++++++++++++++++4");
		System.err.println("o " + response.statusCode());
		System.err.println("o " + response.jsonPath().getString("status"));
		System.err.println("++++++++++++++++++++++++++++++++++++");

	}
	//id null
	@Test
	@Order(7)
	public void addidnull() throws Exception {

		LoadRequest loadrequest = new LoadRequest("Nagpur", "Maharashtra", "Nagpur", null, "Raipur", "Chhattisgarh",
				"Raipur", "Gold", "OPEN_HALF_BODY", "6", "100kg", "added comment", "01/01/20", "Done");

		String inputJson = mapToJson(loadrequest);

		Response response = RestAssured.given().header("", "").body(inputJson).header("accept", "application/json")
				.header("Content-Type", "application/json").post().then().extract().response();

		assertEquals(200, response.statusCode());
		assertEquals(CommonConstants.idError, response.jsonPath().getString("status"));
		
		System.err.println("++++++++++++++++++++++++++++++++++++4");
		System.err.println("o " + response.statusCode());
		System.err.println("o " + response.jsonPath().getString("status"));
		System.err.println("++++++++++++++++++++++++++++++++++++");

	}
	//unloadingpoint null
	@Test
	@Order(8)
	public void addloadunloadingpointnull() throws Exception {

		LoadRequest loadrequest = new LoadRequest("Nagpur", "Maharashtra", "Nagpur", "id:1", null, "Chhattisgarh",
				"Raipur", "Gold", "OPEN_HALF_BODY", "6", "100kg", "added comment", "01/01/20", "Done");

		String inputJson = mapToJson(loadrequest);

		Response response = RestAssured.given().header("", "").body(inputJson).header("accept", "application/json")
				.header("Content-Type", "application/json").post().then().extract().response();

		assertEquals(200, response.statusCode());
		assertEquals(CommonConstants.unloadingError, response.jsonPath().getString("status"));
		
		System.err.println("++++++++++++++++++++++++++++++++++++4");
		System.err.println("o " + response.statusCode());
		System.err.println("o " + response.jsonPath().getString("status"));
		System.err.println("++++++++++++++++++++++++++++++++++++");

	}
	//unloadingpointstate null
	@Test
	@Order(9)
	public void addloadunloadingpointstatenull() throws Exception {

		LoadRequest loadrequest = new LoadRequest("Nagpur", "Maharashtra", "Nagpur", "id:1", "Raipur", null,
				"Raipur", "Gold", "OPEN_HALF_BODY", "6", "100kg", "added comment", "01/01/20", "Done");

		String inputJson = mapToJson(loadrequest);

		Response response = RestAssured.given().header("", "").body(inputJson).header("accept", "application/json")
				.header("Content-Type", "application/json").post().then().extract().response();

		assertEquals(200, response.statusCode());
		assertEquals(CommonConstants.unloadingStateError, response.jsonPath().getString("status"));
		
		System.err.println("++++++++++++++++++++++++++++++++++++4");
		System.err.println("o " + response.statusCode());
		System.err.println("o " + response.jsonPath().getString("status"));
		System.err.println("++++++++++++++++++++++++++++++++++++");

	}
	//unloadingpointcity null
	@Test
	@Order(10)
	public void addloadunloadingpointcitynull() throws Exception {

		LoadRequest loadrequest = new LoadRequest("Nagpur", "Maharashtra", "Nagpur", "id:1", "Raipur", "Chhattisgarh", null,
				"Gold", "OPEN_HALF_BODY", "6", "100kg", "added comment", "01/01/20", "Done");

		String inputJson = mapToJson(loadrequest);

		Response response = RestAssured.given().header("", "").body(inputJson).header("accept", "application/json")
				.header("Content-Type", "application/json").post().then().extract().response();

		assertEquals(200, response.statusCode());
		assertEquals(CommonConstants.unloadingCityError, response.jsonPath().getString("status"));
		
		System.err.println("++++++++++++++++++++++++++++++++++++4");
		System.err.println("o " + response.statusCode());
		System.err.println("o " + response.jsonPath().getString("status"));
		System.err.println("++++++++++++++++++++++++++++++++++++");

	}
	//producttype
	@Test
	@Order(11)
	public void addloadproducttypenull() throws Exception {

		LoadRequest loadrequest = new LoadRequest("Nagpur", "Maharashtra", "Nagpur", "id:1", "Raipur", "Chhattisgarh", "Raipur",
				null, "OPEN_HALF_BODY", "6", "100kg", "added comment", "01/01/20", "Done");

		String inputJson = mapToJson(loadrequest);

		Response response = RestAssured.given().header("", "").body(inputJson).header("accept", "application/json")
				.header("Content-Type", "application/json").post().then().extract().response();

		assertEquals(200, response.statusCode());
		assertEquals(CommonConstants.productTypeError, response.jsonPath().getString("status"));
		
		System.err.println("++++++++++++++++++++++++++++++++++++4");
		System.err.println("o " + response.statusCode());
		System.err.println("o " + response.jsonPath().getString("status"));
		System.err.println("++++++++++++++++++++++++++++++++++++");

	}
	//trucktype
	@Test
	@Order(12)
	public void addloadtrucktypenull() throws Exception {

		LoadRequest loadrequest = new LoadRequest("Nagpur", "Maharashtra", "Nagpur", "id:1", "Raipur", "Chhattisgarh", "Raipur",
				"Gold", null, "6", "100kg", "added comment", "01/01/20", "Done");

		String inputJson = mapToJson(loadrequest);

		Response response = RestAssured.given().header("", "").body(inputJson).header("accept", "application/json")
				.header("Content-Type", "application/json").post().then().extract().response();

		assertEquals(200, response.statusCode());
		assertEquals(CommonConstants.truckTypeError, response.jsonPath().getString("status"));
		
		System.err.println("++++++++++++++++++++++++++++++++++++4");
		System.err.println("o " + response.statusCode());
		System.err.println("o " + response.jsonPath().getString("status"));
		System.err.println("++++++++++++++++++++++++++++++++++++");

	}
	//nooftruck
	@Test
	@Order(13)
	public void addloadnooftrucknull() throws Exception {

		LoadRequest loadrequest = new LoadRequest("Nagpur", "Maharashtra", "Nagpur", "id:1", "Raipur", "Chhattisgarh", "Raipur",
				"Gold", "OPEN_HALF_BODY", null, "100kg", "added comment", "01/01/20", "Done");

		String inputJson = mapToJson(loadrequest);

		Response response = RestAssured.given().header("", "").body(inputJson).header("accept", "application/json")
				.header("Content-Type", "application/json").post().then().extract().response();

		assertEquals(200, response.statusCode());
		assertEquals(CommonConstants.noOfTrucksError, response.jsonPath().getString("status"));
		
		System.err.println("++++++++++++++++++++++++++++++++++++4");
		System.err.println("o " + response.statusCode());
		System.err.println("o " + response.jsonPath().getString("status"));
		System.err.println("++++++++++++++++++++++++++++++++++++");

	}
	//weight
	@Test
	@Order(14)
	public void addloadweightnull() throws Exception {

		LoadRequest loadrequest = new LoadRequest("Nagpur", "Maharashtra", "Nagpur", "id:1", "Raipur", "Chhattisgarh", "Raipur",
				"Gold", "OPEN_HALF_BODY", "6", null, "added comment", "01/01/20", "Done");

		String inputJson = mapToJson(loadrequest);

		Response response = RestAssured.given().header("", "").body(inputJson).header("accept", "application/json")
				.header("Content-Type", "application/json").post().then().extract().response();

		assertEquals(200, response.statusCode());
		assertEquals(CommonConstants.weightError, response.jsonPath().getString("status"));
		
		System.err.println("++++++++++++++++++++++++++++++++++++4");
		System.err.println("o " + response.statusCode());
		System.err.println("o " + response.jsonPath().getString("status"));
		System.err.println("++++++++++++++++++++++++++++++++++++");

	}
	//comment
	@Test
	@Order(15)
	public void addloadcommentnull() throws Exception {

		LoadRequest loadrequest = new LoadRequest("Nagpur", "Maharashtra", "Nagpur", "id:1", "Raipur", "Chhattisgarh", "Raipur",
				"Gold", "OPEN_HALF_BODY", "6", "100kg", null, "01/01/20", "Done");

		String inputJson = mapToJson(loadrequest);

		Response response = RestAssured.given().header("", "").body(inputJson).header("accept", "application/json")
				.header("Content-Type", "application/json").post().then().extract().response();

		assertEquals(200, response.statusCode());
		assertEquals(CommonConstants.pending, response.jsonPath().getString("status"));
		
		System.err.println("++++++++++++++++++++++++++++++++++++4");
		System.err.println("o " + response.statusCode());
		System.err.println("o " + response.jsonPath().getString("status"));
		System.err.println("++++++++++++++++++++++++++++++++++++");
		
		Response response1 = RestAssured.given().header("", "").delete("/" + response.jsonPath().getString("loadId")).then().extract().response();
		System.err.println("++++++++++++++++++++++++++++++++++++17");
		System.err.println("o " + response1.statusCode());
		System.err.println("o " + response1.jsonPath().getString("status"));
		System.err.println("++++++++++++++++++++++++++++++++++++");

	}
	
	//date
	@Test
	@Order(16)
	public void addloaddatenull() throws Exception {

		LoadRequest loadrequest = new LoadRequest("Nagpur", "Maharashtra", "Nagpur", "id:1", "Raipur", "Chhattisgarh", "Raipur",
				"Gold", "OPEN_HALF_BODY", "6", "100kg", "added comment", null, "Done");

		String inputJson = mapToJson(loadrequest);

		Response response = RestAssured.given().header("", "").body(inputJson).header("accept", "application/json")
				.header("Content-Type", "application/json").post().then().extract().response();

		assertEquals(200, response.statusCode());
		assertEquals(CommonConstants.dateError, response.jsonPath().getString("status"));
		
		System.err.println("++++++++++++++++++++++++++++++++++++4");
		System.err.println("o " + response.statusCode());
		System.err.println("o " + response.jsonPath().getString("status"));
		System.err.println("++++++++++++++++++++++++++++++++++++");

	}
	//status
	@Test
	@Order(17)
	public void addloadstatusnull() throws Exception {

		LoadRequest loadrequest = new LoadRequest("Nagpur", "Maharashtra", "Nagpur", "id:1", "Raipur", "Chhattisgarh", "Raipur",
				"Gold", "OPEN_HALF_BODY", "6", "100kg", "added comment", "01/01/20", null);

		String inputJson = mapToJson(loadrequest);

		Response response = RestAssured.given().header("", "").body(inputJson).header("accept", "application/json")
				.header("Content-Type", "application/json").post().then().extract().response();

		assertEquals(200, response.statusCode());
		assertEquals(CommonConstants.pending, response.jsonPath().getString("status"));
		
		System.err.println("++++++++++++++++++++++++++++++++++++17");
		System.err.println("o " + response.statusCode());
		System.err.println("o " + response.jsonPath().getString("status"));
		System.err.println("++++++++++++++++++++++++++++++++++++");
		
		Response response1 = RestAssured.given().header("", "").delete("/" + response.jsonPath().getString("loadId")).then().extract().response();
		System.err.println("++++++++++++++++++++++++++++++++++++17");
		System.err.println("o " + response1.statusCode());
		System.err.println("o " + response1.jsonPath().getString("status"));
		System.err.println("++++++++++++++++++++++++++++++++++++");
		

	}
	
    //////////////////////////////////**getLoad()**////////////////////////
	
	
	//get all load
	@Test
	@Order(18)
	public void getload() throws Exception {
		
		Response response = RestAssured.given().header("accept", "application/json").header("Content-Type", "application/json").get().then().extract()
				.response();
		
		System.err.println("++++++++++++++++++++++++++++++++++++18");
		System.err.println("o " + response.statusCode());
		System.err.println("o " + response.jsonPath().getList("status").size());
		System.err.println("++++++++++++++++++++++++++++++++++++");
		assertEquals(3, response.jsonPath().getList("status").size());
	}
	
	//getloadloadingPointCity
	@Test
	@Order(19)
	public void getloadloadingpointcity() throws Exception {
		
		Response response = RestAssured.given().param("loadingPointCity", "Nagpur").header("accept", "application/json").header("Content-Type", "application/json").get().then().extract()
				.response();
		
		System.err.println("++++++++++++++++++++++++++++++++++++19");
		System.err.println("o " + response.statusCode());
		System.err.println("o " + response.jsonPath().getList("status").size());
		System.err.println("++++++++++++++++++++++++++++++++++++");
		assertEquals(2, response.jsonPath().getList("status").size());
	}
	
	// getload unloadingPointCity
	@Test
	@Order(20)
	public void getloadunloadingPointCity() throws Exception {
		
		Response response = RestAssured.given().param("unloadingPointCity", "Raipur").header("accept", "application/json").header("Content-Type", "application/json").get().then().extract()
				.response();
		
		System.err.println("++++++++++++++++++++++++++++++++++++20");
		System.err.println("o " + response.statusCode());
		System.err.println("o " + response.jsonPath().getList("status").size());
		System.err.println("++++++++++++++++++++++++++++++++++++");
		assertEquals(3, response.jsonPath().getList("status").size());
	}
	//getloadshipperId
	@Test
	@Order(21)
	public void getloadloadshipperId() throws Exception {
		
		Response response = RestAssured.given().param("Id", "id:1").header("accept", "application/json").header("Content-Type", "application/json").get().then().extract()
				.response();
		
		System.err.println("++++++++++++++++++++++++++++++++++++21");
		System.err.println("o " + response.statusCode());
		System.err.println("o " + response.jsonPath().getList("status").size());
		System.err.println("++++++++++++++++++++++++++++++++++++");
		assertEquals(2, response.jsonPath().getList("status").size());
	}
	//getloadtruckType
	@Test
	@Order(22)
	public void getloadtruckType() throws Exception {
		
		Response response = RestAssured.given().param("truckType", "OPEN_HALF_BODY").header("accept", "application/json").header("Content-Type", "application/json").get().then().extract()
				.response();
		
		System.err.println("++++++++++++++++++++++++++++++++++++22");
		System.err.println("o " + response.statusCode());
		System.err.println("o " + response.jsonPath().getList("status").size());
		System.err.println("++++++++++++++++++++++++++++++++++++");
		assertEquals(2, response.jsonPath().getList("status").size());
	}
	//getload date
	@Test
	@Order(23)
	public void getloaddate() throws Exception {
		
		Response response = RestAssured.given().param("date", "01/03/20").header("accept", "application/json").header("Content-Type", "application/json").get().then().extract()
				.response();
		
		System.err.println("++++++++++++++++++++++++++++++++++++23");
		System.err.println("o " + response.statusCode());
		System.err.println("o " + response.jsonPath().getList("status").size());
		System.err.println("++++++++++++++++++++++++++++++++++++");
		assertEquals(1, response.jsonPath().getList("status").size());
	}
	
	//getload loadid
	@Test
	@Order(24)
	public void getloadloadid() throws Exception {
		
		Response response = RestAssured.given().param().header("accept", "application/json").header("Content-Type", "application/json").get("/"+loadid1).then().extract()
				.response();
		
		String loadid = response.jsonPath().getString("loadId");
		System.err.println("++++++++++++++++++++++++++++++++++++24");
		System.err.println("o " + response.statusCode());
		System.err.println("o " + response.jsonPath().getString("status"));
		System.err.println("++++++++++++++++++++++++++++++++++++");
		assertEquals("Nagpur", response.jsonPath().getString("loadingPointCity"));
		assertEquals("Maharashtra", response.jsonPath().getString("loadingPointState"));
		assertEquals("id:1", response.jsonPath().getString("id"));
		assertEquals("Raipur", response.jsonPath().getString("unloadingPointCity"));
		assertEquals("Chhattisgarh", response.jsonPath().getString("unloadingPointState"));
		assertEquals("Gold", response.jsonPath().getString("productType"));
		assertEquals("OPEN_HALF_BODY", response.jsonPath().getString("truckType"));
		assertEquals("6", response.jsonPath().getString("noOfTrucks"));
		assertEquals("100kg", response.jsonPath().getString("weight"));
		assertEquals("01/01/20", response.jsonPath().getString("date"));
	}
	
//////////////////////////////////**updateLoad()**////////////////////////
	
	@Test
	@Order(25)
	public void updateData() throws Exception {

		//adding load
		LoadRequest loadrequest = new LoadRequest("Nagpur", "Maharashtra", "Nagpur", "id:1", "Raipur", "Chhattisgarh",
				"Raipur", "Gold", "OPEN_HALF_BODY", "6", "100kg", "added comment", "01/01/20", "Done");
        String inputJson = mapToJson(loadrequest);
        Response response = (Response) RestAssured.given().header("", "").body(inputJson).header("accept", "application/json")
				.header("Content-Type", "application/json").post().then().extract().response();
        
		String loadid = response.jsonPath().getString("loadId");
		
		//update request
		LoadRequest loadrequestupdate = new LoadRequest("Surat", "Gujarat", "Surat", "id:4", "Patna", "Bihar",
				"Patna", "Silver", "OPEN_HALF_BODY", "60", "1000kg", "added comment", "01/05/20", "Done");

		String inputJsonupdate = mapToJson(loadrequestupdate);

		Response responseupdate = RestAssured.given().header("", "").body(inputJsonupdate).header("accept", "application/json")
				.header("Content-Type", "application/json").put("/" + loadid).then().extract().response();

		assertEquals(200, responseupdate.statusCode());
		assertEquals(CommonConstants.updateSuccess, responseupdate.jsonPath().getString("status"));
		assertEquals(CommonConstants.updateSuccess, responseupdate.jsonPath().getString("status"));
		
		System.err.println("++++++++++++++++++++++++++++++++++++25");
		System.err.println("o " + responseupdate.jsonPath().getString("status"));
		System.err.println("++++++++++++++++++++++++++++++++++++");
		
		Response response1 = RestAssured.given().param("date", "01/03/20").header("accept", "application/json").header("Content-Type", "application/json").get("/"+loadid).then().extract()
				.response();
		
		System.err.println("++++++++++++++++++++++++++++++++++++24");
		System.err.println("o " + response1.statusCode());
		System.err.println("o " + response1.jsonPath().getString("status"));
		System.err.println("++++++++++++++++++++++++++++++++++++");
		assertEquals("Surat", response1.jsonPath().getString("loadingPointCity"));
		assertEquals("Gujarat", response1.jsonPath().getString("loadingPointState"));
		assertEquals("id:4", response1.jsonPath().getString("id"));
		assertEquals("Patna", response1.jsonPath().getString("unloadingPointCity"));
		assertEquals("Bihar", response1.jsonPath().getString("unloadingPointState"));
		assertEquals("Silver", response1.jsonPath().getString("productType"));
		assertEquals("OPEN_HALF_BODY", response1.jsonPath().getString("truckType"));
		assertEquals("60", response1.jsonPath().getString("noOfTrucks"));
		assertEquals("1000kg", response1.jsonPath().getString("weight"));
		assertEquals("01/05/20", response1.jsonPath().getString("date"));
		
		Response responsedel = RestAssured.given().header("", "").delete("/" + loadid).then().extract().response();
		assertEquals(200, responsedel.statusCode());
		assertEquals(CommonConstants.deleteSuccess, responsedel.jsonPath().getString("status"));
	}
	
	// all null
	@Test
	@Order(25)
	public void updateData1() throws Exception {

		//adding load
		LoadRequest loadrequest = new LoadRequest("Nagpur", "Maharashtra", "Nagpur", "id:1", "Raipur", "Chhattisgarh",
				"Raipur", "Gold", "OPEN_HALF_BODY", "6", "100kg", "added comment", "01/01/20", "Done");
        String inputJson = mapToJson(loadrequest);
        Response response = (Response) RestAssured.given().header("", "").body(inputJson).header("accept", "application/json")
				.header("Content-Type", "application/json").post().then().extract().response();
        
		String loadid = response.jsonPath().getString("loadId");
		
		//update request
		LoadRequest loadrequestupdate = new LoadRequest(null,null,null,null,null,null,null,null,null,null,null,null,null,null);

		String inputJsonupdate = mapToJson(loadrequestupdate);

		Response responseupdate = RestAssured.given().header("", "").body(inputJsonupdate).header("accept", "application/json")
				.header("Content-Type", "application/json").put("/" + loadid).then().extract().response();

		assertEquals(200, responseupdate.statusCode());
		assertEquals(CommonConstants.updateSuccess, responseupdate.jsonPath().getString("status"));
		assertEquals(CommonConstants.updateSuccess, responseupdate.jsonPath().getString("status"));
		
		System.err.println("++++++++++++++++++++++++++++++++++++25");
		System.err.println("o " + responseupdate.jsonPath().getString("status"));
		System.err.println("++++++++++++++++++++++++++++++++++++");
		
		Response response1 = RestAssured.given().param("date", "01/03/20").header("accept", "application/json").header("Content-Type", "application/json").get("/"+loadid).then().extract()
				.response();
		
		System.err.println("++++++++++++++++++++++++++++++++++++24");
		System.err.println("o " + response1.statusCode());
		System.err.println("o " + response1.jsonPath().getString("status"));
		System.err.println("++++++++++++++++++++++++++++++++++++");
		assertEquals("Nagpur", response1.jsonPath().getString("loadingPointCity"));
		assertEquals("Maharashtra", response1.jsonPath().getString("loadingPointState"));
		assertEquals("id:1", response1.jsonPath().getString("id"));
		assertEquals("Raipur", response1.jsonPath().getString("unloadingPointCity"));
		assertEquals("Chhattisgarh", response1.jsonPath().getString("unloadingPointState"));
		assertEquals("Gold", response1.jsonPath().getString("productType"));
		assertEquals("OPEN_HALF_BODY", response1.jsonPath().getString("truckType"));
		assertEquals("6", response1.jsonPath().getString("noOfTrucks"));
		assertEquals("100kg", response1.jsonPath().getString("weight"));
		assertEquals("01/01/20", response1.jsonPath().getString("date"));
		
		Response responsedel = RestAssured.given().header("", "").delete("/" + loadid).then().extract().response();
		assertEquals(200, responsedel.statusCode());
		assertEquals(CommonConstants.deleteSuccess, responsedel.jsonPath().getString("status"));
	}
	
	
	
	// loading point city empty
	@Test
	@Order(25)
	public void updateData2() throws Exception {

		//adding load
		LoadRequest loadrequest = new LoadRequest("Nagpur", "Maharashtra", "Nagpur", "id:1", "Raipur", "Chhattisgarh",
				"Raipur", "Gold", "OPEN_HALF_BODY", "6", "100kg", "added comment", "01/01/20", "Done");
        String inputJson = mapToJson(loadrequest);
        Response response = (Response) RestAssured.given().header("", "").body(inputJson).header("accept", "application/json")
				.header("Content-Type", "application/json").post().then().extract().response();
        
		String loadid = response.jsonPath().getString("loadId");
		
		//update request
		LoadRequest loadrequestupdate = new LoadRequest( "",null,null,null,null,null,null,null,null,null,null,null,null,null);

		String inputJsonupdate = mapToJson(loadrequestupdate);

		Response responseupdate = RestAssured.given().header("", "").body(inputJsonupdate).header("accept", "application/json")
				.header("Content-Type", "application/json").put("/" + loadid).then().extract().response();

		assertEquals(200, responseupdate.statusCode());
		assertEquals(CommonConstants.emptyloadingpoint, responseupdate.jsonPath().getString("status"));
		
		System.err.println("++++++++++++++++++++++++++++++++++++25");
		System.err.println("o " + responseupdate.jsonPath().getString("status"));
		System.err.println("++++++++++++++++++++++++++++++++++++");
		
		Response responsedel = RestAssured.given().header("", "").delete("/" + loadid).then().extract().response();
		assertEquals(200, responsedel.statusCode());
		assertEquals(CommonConstants.deleteSuccess, responsedel.jsonPath().getString("status"));
		
	}
	// loading state empty
	@Test
	@Order(25)
	public void updateData3() throws Exception {

		//adding load
		LoadRequest loadrequest = new LoadRequest("Nagpur", "Maharashtra", "Nagpur", "id:1", "Raipur", "Chhattisgarh",
				"Raipur", "Gold", "OPEN_HALF_BODY", "6", "100kg", "added comment", "01/01/20", "Done");
        String inputJson = mapToJson(loadrequest);
        Response response = (Response) RestAssured.given().header("", "").body(inputJson).header("accept", "application/json")
				.header("Content-Type", "application/json").post().then().extract().response();
        
		String loadid = response.jsonPath().getString("loadId");
		
		//update request
		LoadRequest loadrequestupdate = new LoadRequest( null,"",null,null,null,null,null,null,null,null,null,null,null,null);

		String inputJsonupdate = mapToJson(loadrequestupdate);

		Response responseupdate = RestAssured.given().header("", "").body(inputJsonupdate).header("accept", "application/json")
				.header("Content-Type", "application/json").put("/" + loadid).then().extract().response();

		assertEquals(200, responseupdate.statusCode());
		assertEquals(CommonConstants.emptyloadingstate, responseupdate.jsonPath().getString("status"));
		
		System.err.println("++++++++++++++++++++++++++++++++++++25");
		System.err.println("o " + responseupdate.jsonPath().getString("status"));
		System.err.println("++++++++++++++++++++++++++++++++++++");
		
		Response responsedel = RestAssured.given().header("", "").delete("/" + loadid).then().extract().response();
		assertEquals(200, responsedel.statusCode());
		assertEquals(CommonConstants.deleteSuccess, responsedel.jsonPath().getString("status"));
		
	}
	//loading city empty
	@Test
	@Order(25)
	public void updateData4() throws Exception {

		//adding load
		LoadRequest loadrequest = new LoadRequest("Nagpur", "Maharashtra", "Nagpur", "id:1", "Raipur", "Chhattisgarh",
				"Raipur", "Gold", "OPEN_HALF_BODY", "6", "100kg", "added comment", "01/01/20", "Done");
        String inputJson = mapToJson(loadrequest);
        Response response = (Response) RestAssured.given().header("", "").body(inputJson).header("accept", "application/json")
				.header("Content-Type", "application/json").post().then().extract().response();
        
		String loadid = response.jsonPath().getString("loadId");
		
		//update request
		LoadRequest loadrequestupdate = new LoadRequest( null,null,"",null,null,null,null,null,null,null,null,null,null,null);

		String inputJsonupdate = mapToJson(loadrequestupdate);

		Response responseupdate = RestAssured.given().header("", "").body(inputJsonupdate).header("accept", "application/json")
				.header("Content-Type", "application/json").put("/" + loadid).then().extract().response();

		assertEquals(200, responseupdate.statusCode());
		assertEquals(CommonConstants.emptyloadingcity, responseupdate.jsonPath().getString("status"));
		
		System.err.println("++++++++++++++++++++++++++++++++++++25");
		System.err.println("o " + responseupdate.jsonPath().getString("status"));
		System.err.println("++++++++++++++++++++++++++++++++++++");
		
		Response responsedel = RestAssured.given().header("", "").delete("/" + loadid).then().extract().response();
		assertEquals(200, responsedel.statusCode());
		assertEquals(CommonConstants.deleteSuccess, responsedel.jsonPath().getString("status"));
		
	}
	// unloading point empty
	@Test
	@Order(25)
	public void updateData5() throws Exception {

		//adding load
		LoadRequest loadrequest = new LoadRequest("Nagpur", "Maharashtra", "Nagpur", "id:1", "Raipur", "Chhattisgarh",
				"Raipur", "Gold", "OPEN_HALF_BODY", "6", "100kg", "added comment", "01/01/20", "Done");
        String inputJson = mapToJson(loadrequest);
        Response response = (Response) RestAssured.given().header("", "").body(inputJson).header("accept", "application/json")
				.header("Content-Type", "application/json").post().then().extract().response();
        
		String loadid = response.jsonPath().getString("loadId");
		
		//update request
		LoadRequest loadrequestupdate = new LoadRequest( null,null,null,null,"",null,null,null,null,null,null,null,null,null);

		String inputJsonupdate = mapToJson(loadrequestupdate);

		Response responseupdate = RestAssured.given().header("", "").body(inputJsonupdate).header("accept", "application/json")
				.header("Content-Type", "application/json").put("/" + loadid).then().extract().response();

		assertEquals(200, responseupdate.statusCode());
		assertEquals(CommonConstants.emptyunloadingpoint, responseupdate.jsonPath().getString("status"));
		
		System.err.println("++++++++++++++++++++++++++++++++++++25");
		System.err.println("o " + responseupdate.jsonPath().getString("status"));
		System.err.println("++++++++++++++++++++++++++++++++++++");
		
		Response responsedel = RestAssured.given().header("", "").delete("/" + loadid).then().extract().response();
		assertEquals(200, responsedel.statusCode());
		assertEquals(CommonConstants.deleteSuccess, responsedel.jsonPath().getString("status"));
		
	}
	
	// unloading state empty
	@Test
	@Order(25)
	public void updateData6() throws Exception {

		//adding load
		LoadRequest loadrequest = new LoadRequest("Nagpur", "Maharashtra", "Nagpur", "id:1", "Raipur", "Chhattisgarh",
				"Raipur", "Gold", "OPEN_HALF_BODY", "6", "100kg", "added comment", "01/01/20", "Done");
        String inputJson = mapToJson(loadrequest);
        Response response = (Response) RestAssured.given().header("", "").body(inputJson).header("accept", "application/json")
				.header("Content-Type", "application/json").post().then().extract().response();
        
		String loadid = response.jsonPath().getString("loadId");
		
		//update request
		LoadRequest loadrequestupdate = new LoadRequest( null,null,null,null,null,"",null,null,null,null,null,null,null,null);

		String inputJsonupdate = mapToJson(loadrequestupdate);

		Response responseupdate = RestAssured.given().header("", "").body(inputJsonupdate).header("accept", "application/json")
				.header("Content-Type", "application/json").put("/" + loadid).then().extract().response();

		assertEquals(200, responseupdate.statusCode());
		assertEquals(CommonConstants.emptyunloadingstate, responseupdate.jsonPath().getString("status"));
		
		System.err.println("++++++++++++++++++++++++++++++++++++25");
		System.err.println("o " + responseupdate.jsonPath().getString("status"));
		System.err.println("++++++++++++++++++++++++++++++++++++");
		
		Response responsedel = RestAssured.given().header("", "").delete("/" + loadid).then().extract().response();
		assertEquals(200, responsedel.statusCode());
		assertEquals(CommonConstants.deleteSuccess, responsedel.jsonPath().getString("status"));
		
	}
	//unloading city empty
	@Test
	@Order(25)
	public void updateData7() throws Exception {

		//adding load
		LoadRequest loadrequest = new LoadRequest("Nagpur", "Maharashtra", "Nagpur", "id:1", "Raipur", "Chhattisgarh",
				"Raipur", "Gold", "OPEN_HALF_BODY", "6", "100kg", "added comment", "01/01/20", "Done");
        String inputJson = mapToJson(loadrequest);
        Response response = (Response) RestAssured.given().header("", "").body(inputJson).header("accept", "application/json")
				.header("Content-Type", "application/json").post().then().extract().response();
        
		String loadid = response.jsonPath().getString("loadId");
		
		//update request
		LoadRequest loadrequestupdate = new LoadRequest( null,null,null,null,null,null,"",null,null,null,null,null,null,null);

		String inputJsonupdate = mapToJson(loadrequestupdate);

		Response responseupdate = RestAssured.given().header("", "").body(inputJsonupdate).header("accept", "application/json")
				.header("Content-Type", "application/json").put("/" + loadid).then().extract().response();

		assertEquals(200, responseupdate.statusCode());
		assertEquals(CommonConstants.emptyunloadingcity, responseupdate.jsonPath().getString("status"));
		
		System.err.println("++++++++++++++++++++++++++++++++++++25");
		System.err.println("o " + responseupdate.jsonPath().getString("status"));
		System.err.println("++++++++++++++++++++++++++++++++++++");
		
		Response responsedel = RestAssured.given().header("", "").delete("/" + loadid).then().extract().response();
		assertEquals(200, responsedel.statusCode());
		assertEquals(CommonConstants.deleteSuccess, responsedel.jsonPath().getString("status"));
	}
	//empty id
		@Test
		@Order(25)
		public void updateData8() throws Exception {

			//adding load
			LoadRequest loadrequest = new LoadRequest("Nagpur", "Maharashtra", "Nagpur", "id:1", "Raipur", "Chhattisgarh",
					"Raipur", "Gold", "OPEN_HALF_BODY", "6", "100kg", "added comment", "01/01/20", "Done");
	        String inputJson = mapToJson(loadrequest);
	        Response response = (Response) RestAssured.given().header("", "").body(inputJson).header("accept", "application/json")
					.header("Content-Type", "application/json").post().then().extract().response();
	        
			String loadid = response.jsonPath().getString("loadId");
			
			//update request
			LoadRequest loadrequestupdate = new LoadRequest( null,null,null,"",null,null,null,null,null,null,null,null,null,null);

			String inputJsonupdate = mapToJson(loadrequestupdate);

			Response responseupdate = RestAssured.given().header("", "").body(inputJsonupdate).header("accept", "application/json")
					.header("Content-Type", "application/json").put("/" + loadid).then().extract().response();

			assertEquals(200, responseupdate.statusCode());
			assertEquals(CommonConstants.emptyshiperid, responseupdate.jsonPath().getString("status"));
			
			System.err.println("++++++++++++++++++++++++++++++++++++25");
			System.err.println("o " + responseupdate.jsonPath().getString("status"));
			System.err.println("++++++++++++++++++++++++++++++++++++");
			
			Response responsedel = RestAssured.given().header("", "").delete("/" + loadid).then().extract().response();
			assertEquals(200, responsedel.statusCode());
			assertEquals(CommonConstants.deleteSuccess, responsedel.jsonPath().getString("status"));
		}
	
	
	@Test
	@AfterAll
	public static void deleteData() throws Exception {

		Response response1 = RestAssured.given().header("", "").delete("/" + loadid1).then().extract().response();
		assertEquals(200, response1.statusCode());
		assertEquals(CommonConstants.deleteSuccess, response1.jsonPath().getString("status"));
		
		Response response2 = RestAssured.given().header("", "").delete("/" + loadid2).then().extract().response();
		assertEquals(200, response2.statusCode());
		assertEquals(CommonConstants.deleteSuccess, response2.jsonPath().getString("status"));
		
		Response response3 = RestAssured.given().header("", "").delete("/" + loadid3).then().extract().response();
		assertEquals(200, response3.statusCode());
		assertEquals(CommonConstants.deleteSuccess, response3.jsonPath().getString("status"));
	}
}
