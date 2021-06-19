package com.TruckBooking.TruckBooking;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.TruckBooking.TruckBooking.Constants.CommonConstants;
import com.TruckBooking.TruckBooking.Controller.LoadController;
import com.TruckBooking.TruckBooking.Dao.LoadDao;
import com.TruckBooking.TruckBooking.Entities.Load;
import com.TruckBooking.TruckBooking.Model.LoadRequest;
import com.TruckBooking.TruckBooking.Response.CreateLoadResponse;
import com.TruckBooking.TruckBooking.Response.DeleteLoadResponse;
import com.TruckBooking.TruckBooking.Response.UpdateLoadResponse;
import com.TruckBooking.TruckBooking.Service.LoadServiceImpl;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;


@WebMvcTest(value = LoadController.class)
public class TestLoadController {
	String URI = "/load";
	
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private LoadDao loaddao;

	@MockBean
	private LoadServiceImpl loadservice;

	private static String mapToJson(Object object) throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_NUMBERS_FOR_ENUMS, false);
		objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
		objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT, true);
		objectMapper.configure(DeserializationFeature.READ_ENUMS_USING_TO_STRING, true);

		return objectMapper.writeValueAsString(object);
	}
	
	@Test
	public void addLoad() throws Exception {
		LoadRequest loadrequest = createLoadRequest();
		Load load = createLoad();
		CreateLoadResponse createloadresponse = createLoadResponse();
		
		when(loadservice.addLoad(Mockito.any(LoadRequest.class))).thenReturn(createloadresponse);
		
		String inputJson = mapToJson(loadrequest);
		String expectedJson = mapToJson(createloadresponse);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(CommonConstants.URI).accept(MediaType.APPLICATION_JSON).content(inputJson).contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();

		String outputInJson = response.getContentAsString();
		
		System.err.println("++++++++++++++++++++++++++++++++++++1");
		System.err.println("i " + inputJson);
		System.err.println("e " + expectedJson);
		System.err.println("o " + outputInJson);
		System.err.println("++++++++++++++++++++++++++++++++++++");

		assertThat(outputInJson).isEqualTo(expectedJson);
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}
	
	
	// sending loadid using url
	@Test
	public void getLoadbyloadid() throws Exception {
		LoadRequest loadrequest = createLoadRequest();
		List<Load> loads = createLoads();
		
		CreateLoadResponse createloadresponse = createLoadResponse();

		when(loadservice.getLoad("loadid:1")).thenReturn(loads.get(0));

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(CommonConstants.LOADID_URI).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expectedJson = mapToJson(loads.get(0));
		String outputInJson = result.getResponse().getContentAsString();
		
		System.err.println("++++++++++++++++++++++++++++++++++++2");
		System.err.println("e " + expectedJson);
		System.err.println("o " + outputInJson);
		System.err.println("i " + result);
		System.err.println("++++++++++++++++++++++++++++++++++++");

		assertEquals(expectedJson, outputInJson);
		
		MockHttpServletResponse response1 = result.getResponse();
		assertEquals(HttpStatus.OK.value(), response1.getStatus());
	}	
	
	// using params
	@Test
	public void getLoad() throws Exception {
		LoadRequest loadrequest = createLoadRequest();
		List<Load> loads = createLoads();
		List<Load> loadsret = Arrays.asList(loads.get(0));
		
		CreateLoadResponse createloadresponse = createLoadResponse();

		when(loadservice.getLoads(null, null, CommonConstants.ID, null, null)).thenReturn(loadsret);

		String URI = "/load?Id=id:1";
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expectedJson = mapToJson(loadsret);
		String outputInJson = result.getResponse().getContentAsString();
		
		System.err.println("++++++++++++++++++++++++++++++++++++3");
		System.err.println("e " + expectedJson);
		System.err.println("o " + outputInJson);
		System.err.println("i " + result);
		System.err.println("++++++++++++++++++++++++++++++++++++");

		assertEquals(expectedJson, outputInJson);
		
		MockHttpServletResponse response1 = result.getResponse();
		assertEquals(HttpStatus.OK.value(), response1.getStatus());
	}
	
	@Test
	public void updateLoad() throws Exception {
		LoadRequest loadrequest = createLoadRequest();
		List<Load> loads = createLoads();
		
		UpdateLoadResponse response =  updateLoadResponse();
		String inputJson = mapToJson(loadrequest);

		when(loadservice.updateLoad(CommonConstants.LOADID, loadrequest)).thenReturn(response);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.put(CommonConstants.LOADID_URI).accept(MediaType.APPLICATION_JSON).content(inputJson).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expectedJson = mapToJson(response);
		String outputInJson = result.getResponse().getContentAsString();
		
		System.err.println("++++++++++++++++++++++++++++++++++++4");
		System.err.println("e " + expectedJson);
		System.err.println("o " + outputInJson);
		System.err.println("i " + result.getResponse());
		System.err.println("++++++++++++++++++++++++++++++++++++");

		assertEquals(expectedJson, outputInJson);
		
		MockHttpServletResponse response1 = result.getResponse();
		assertEquals(HttpStatus.OK.value(), response1.getStatus());
	}
	
	@Test
	public void deleteLoad() throws Exception {
		DeleteLoadResponse response = new DeleteLoadResponse();
		response.setStatus(CommonConstants.deleteSuccess);
		
		List<Load> loads = createLoads();
		when(loadservice.deleteLoad(CommonConstants.LOADID)).thenReturn(response);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete(CommonConstants.LOADID_URI).accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		String expectedJson = mapToJson(response);
		String outputInJson = result.getResponse().getContentAsString();
		
		assertEquals(expectedJson, outputInJson);
		
		MockHttpServletResponse response1 = result.getResponse();
		assertEquals(HttpStatus.OK.value(), response1.getStatus());
	}
	
	public LoadRequest createLoadRequest()
	{
		LoadRequest loadrequest = new LoadRequest();
		loadrequest.setLoadingPoint("Nagpur");
		loadrequest.setLoadingPointState("Maharashtra");
		loadrequest.setLoadingPointCity("Nagpur");
		loadrequest.setId("id:1");
		loadrequest.setUnloadingPoint("Raipur");
		loadrequest.setUnloadingPointState("Chhattisgarh");
		loadrequest.setUnloadingPointCity("Raipur");
		loadrequest.setProductType("Gold");
		loadrequest.setTruckType("OPEN_HALF_BODY");
		loadrequest.setNoOfTrucks("6");
		loadrequest.setWeight("100kg");
		loadrequest.setComment("added comment");
		loadrequest.setDate("01/01/20");
		loadrequest.setStatus("Done");
		return loadrequest;
	}
	
	public CreateLoadResponse createLoadResponse()
	{
		CreateLoadResponse response = new CreateLoadResponse();
		response.setLoadId("loadid:1");
		response.setLoadingPoint("Nagpur");
		response.setLoadingPointCity("Nagpur");
		response.setLoadingPointState("Maharashtra");
		response.setId("id:1");
		response.setUnloadingPoint("Raipur");
		response.setUnloadingPointCity("Raipur");
		response.setUnloadingPointState("Chhattisgarh");
		response.setProductType("Metal Scrap");
		response.setTruckType("OPEN_HALF_BODY");
		response.setNoOfTrucks("4");
		response.setWeight("10000kg");
		response.setComment("no comments");
		response.setStatus("pending");
		response.setDate("01/01/21");
		return response;
	}
	
	public UpdateLoadResponse updateLoadResponse()
	{
		UpdateLoadResponse response = new UpdateLoadResponse();
		response.setStatus(CommonConstants.updateSuccess);
		return response;
	}
	
	public Load createLoad()
	{
		Load load = new Load();
		load.setLoadId("loadid:1");
		load.setLoadingPoint("Nagpur");
		load.setLoadingPointCity("Nagpur");
		load.setLoadingPointState("Maharashtra");
		load.setId("id:1");
		load.setUnloadingPoint("Raipur");
		load.setUnloadingPointCity("Raipur");
		load.setUnloadingPointState("Chhattisgarh");
		load.setProductType("Metal Scrap");
		load.setTruckType("OPEN_HALF_BODY");
		load.setNoOfTrucks("4");
		load.setWeight("10000kg");
		load.setComment("no comments");
		load.setStatus("pending");
		load.setDate("01/01/21");
		return load;
	}
	
	public List<Load> createLoads()
	{
		Load load1 = new Load();
		load1.setLoadId("loadid:1");
		load1.setLoadingPoint("Nagpur");
		load1.setLoadingPointCity("Nagpur");
		load1.setLoadingPointState("Maharashtra");
		load1.setId("id:1");
		load1.setUnloadingPoint("Raipur");
		load1.setUnloadingPointCity("Raipur");
		load1.setUnloadingPointState("Chhattisgarh");
		load1.setProductType("Metal Scrap");
		load1.setTruckType("OPEN_HALF_BODY");
		load1.setNoOfTrucks("4");
		load1.setWeight("10000kg");
		load1.setComment("no comments");
		load1.setStatus("pending");
		load1.setDate("01/01/21");

		Load load2 = new Load();
		load2.setLoadId("loadid:2");
		load2.setLoadingPoint("Nagpur");
		load2.setLoadingPointCity("Nagpur");
		load2.setLoadingPointState("Maharashtra");
		load2.setId("id:2");
		load2.setUnloadingPoint("Raipur");
		load2.setUnloadingPointCity("Raipur");
		load2.setUnloadingPointState("Chhattisgarh");
		load2.setProductType("Metal Scrap");
		load2.setTruckType("STANDARD_CONTAINER");
		load2.setNoOfTrucks("4");
		load2.setWeight("10000kg");
		load2.setComment("no comments");
		load2.setStatus("pending");
		load2.setDate("02/01/21");

		Load load3 = new Load();
		load3.setLoadId("loadid:3");
		load3.setLoadingPoint("Nagpur");
		load3.setLoadingPointCity("Nagpur");
		load3.setLoadingPointState("Maharashtra");
		load3.setId("id:1");
		load3.setUnloadingPoint("Raipur");
		load3.setUnloadingPointCity("Raipur");
		load3.setUnloadingPointState("Chhattisgarh");
		load3.setProductType("Metal Scrap");
		load3.setTruckType("OPEN_HALF_BODY");
		load3.setNoOfTrucks("4");
		load3.setWeight("10000kg");
		load3.setComment("no comments");
		load3.setStatus("pending");
		load3.setDate("03/01/21");
		
        List<Load> loads = Arrays.asList(load1, load2, load3);
		return loads;
	}

}
