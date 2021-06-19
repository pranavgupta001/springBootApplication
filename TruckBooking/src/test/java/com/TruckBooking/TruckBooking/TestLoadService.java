package com.TruckBooking.TruckBooking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.Arrays;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import com.TruckBooking.TruckBooking.Constants.CommonConstants;
import com.TruckBooking.TruckBooking.Dao.LoadDao;
import com.TruckBooking.TruckBooking.Entities.Load;
import com.TruckBooking.TruckBooking.Model.LoadRequest;
import com.TruckBooking.TruckBooking.Response.CreateLoadResponse;
import com.TruckBooking.TruckBooking.Response.DeleteLoadResponse;
import com.TruckBooking.TruckBooking.Response.UpdateLoadResponse;
import com.TruckBooking.TruckBooking.Service.LoadService;
import com.TruckBooking.TruckBooking.Service.LoadServiceImpl;


@SpringBootTest
public class TestLoadService {
	
	@Autowired
	LoadServiceImpl loadservice;
	
	@MockBean
	LoadDao loaddao;
	
	@Test
	public void addLoad()
	{
		LoadRequest loadrequest = createLoadRequest();
		Load load = createLoad();
		
		CreateLoadResponse createloadresponse = new CreateLoadResponse();
		createloadresponse.setStatus("pending");
		createloadresponse.setLoadId("loadid:1");
		createloadresponse.setLoadingPointCity("Nagpur");
		createloadresponse.setLoadingPoint("Nagpur");
		createloadresponse.setLoadingPointState("Maharashtra");
		createloadresponse.setId("id:1");
		createloadresponse.setUnloadingPoint("Raipur");
		createloadresponse.setUnloadingPointCity("Raipur");
		createloadresponse.setUnloadingPointState("Chhattisgarh");
		createloadresponse.setProductType("metal");
		createloadresponse.setNoOfTrucks("10");
		createloadresponse.setWeight("10000kg");
		createloadresponse.setComment("None");
		createloadresponse.setDate("02/02/22");
		
		when(loaddao.save(load)).thenReturn(load);
		
		CreateLoadResponse createloadresponseres = loadservice.addLoad(loadrequest);
		System.err.println("working");
		System.err.println("req: " + createloadresponse);
		System.err.println("res: " + loadservice.addLoad(loadrequest));
		
		assertEquals(createloadresponse.getStatus(), loadservice.addLoad(loadrequest).getStatus());
	}
	
	@Test
	public void getLoadsBytruckType()
	{
		List<Load> loads = Arrays.asList(createLoads().get(0), createLoads().get(2));
		when(loaddao.findByTruckType("OPEN_HALF_BODY")).thenReturn(loads);
		
		System.err.println("working " + loads);
		System.err.println("req: " + loadservice.getLoads( null, null, null, "OPEN_HALF_BODY", null));
		System.err.println("res: " + loadservice.getLoads( null, null, null, "OPEN_HALF_BODY", null).size());
		assertEquals(2, loadservice.getLoads( null, null, null, "OPEN_HALF_BODY", null).size());
	}
	
	@Test
	public void getLoadsBydate()
	{
		List<Load> loads = Arrays.asList(createLoads().get(0), createLoads().get(1));
		when(loaddao.findByDate("03/01/21")).thenReturn(loads);
		
		System.err.println("working " + loads);
		System.err.println("req: " + loadservice.getLoads( null, null, null, null, "03/01/21"));
		System.err.println("res: " + loadservice.getLoads( null, null, null, null, "03/01/21").size());
		assertEquals(2, loadservice.getLoads( null, null, null, null, "03/01/21").size());
	}
	
	@Test
	public void getLoadsByshipperId()
	{
		List<Load> loads = Arrays.asList(createLoads().get(2));
		when(loaddao.findByid("id:1")).thenReturn(loads);
		
		System.err.println("working " + loads);
		System.err.println("req: " + loadservice.getLoads( null, null, "id:1", null, null));
		System.err.println("res: " + loadservice.getLoads( null, null, "id:1", null, null).size());
		assertEquals(1, loadservice.getLoads( null, null, "id:1", null, null).size());
	}
	
	@Test
	public void getLoadsByloadingPointCity()
	{
		List<Load> loads = createLoads();
		when(loaddao.findByLoadingPointCity("Nagpur")).thenReturn(loads);
		
		System.err.println("working " + loads + " bbb " );
		System.err.println("req: " + loadservice.getLoads("Nagpur", null, null, null, null));
		System.err.println("res: " + loadservice.getLoads("Nagpur", null, null, null, null).size());
		assertEquals(3, loadservice.getLoads("Nagpur", null, null, null, null).size());
	}
	
	// unable to search using only unloading point, loading point is also necessary for this
	@Test
	public void getLoadsByloadandunloadingPointCity()
	{
		List<Load> loads = createLoads();
		when(loaddao.findByLoadAndUnloadPoint("Nagpur","Raipur")).thenReturn(loads);
		
		System.err.println("working " + loads + " bbb " );
		System.err.println("req: " + loadservice.getLoads("Nagpur", "Raipur", null, null, null));
		System.err.println("res: " + loadservice.getLoads("Nagpur", "Raipur", null, null, null).size());
		assertEquals(3, loadservice.getLoads("Nagpur", "Raipur", null, null, null).size());
	}
	
	public void getLoad()
	{
		List<Load> loads = createLoads();
		
		when(loaddao.findAll()).thenReturn(loads);
		assertEquals(3, loadservice.getLoads(null, null, null, null, null).size());
	}
	
	@Test
	public void updateLoad()
	{
		List<Load> loads = createLoads();

		when(loaddao.findByLoadId("loadid:1")).thenReturn(Optional.of(loads.get(0)));

		LoadRequest loadrequest = new LoadRequest();
		loadrequest.setLoadingPoint("Nagpur,MH");
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
		UpdateLoadResponse response = new UpdateLoadResponse();
		response.setStatus(CommonConstants.updateSuccess);
		
		System.err.println("++++++++++++++++++++++++++++++++++++" );
		System.err.println("working " + loads + " bbb " );
		System.err.println("req: " + response);
		System.err.println("res: " + loadservice.updateLoad("loadid:1", loadrequest));
		System.err.println("++++++++++++++++++++++++++++++++++++" );

		
		assertEquals(response, loadservice.updateLoad("loadid:1", loadrequest));
		
	}
	
	
	@Test
	public void deleteLoad()
	{
		List<Load> loads = createLoads();

		when(loaddao.findByLoadId("loadid:1")).thenReturn(Optional.of(loads.get(0)));
		
		DeleteLoadResponse response = new DeleteLoadResponse();
		response.setStatus(CommonConstants.deleteSuccess);
		System.err.println("++++++++++++++++++++++++++++++++++++" );
		System.err.println("working " + loads + " bbb " );
		System.err.println("req: " + loadservice.deleteLoad("loadid:1"));
		System.err.println("res: " + response);
		System.err.println("++++++++++++++++++++++++++++++++++++" );
		
		assertEquals(response, loadservice.deleteLoad("loadid:1"));
	}
	
	@Test
	public void deleteLoadFail()
	{
		List<Load> loads = createLoads();

		when(loaddao.findByLoadId("loadid:1")).thenReturn(Optional.of(loads.get(0)));
		
		DeleteLoadResponse response = new DeleteLoadResponse();
		response.setStatus(CommonConstants.AccountNotFoundError);
		System.err.println("++++++++++++++++++++++++++++++++++++" );
		System.err.println("fail " + loads + " bbb " );
		System.err.println("req: " + loadservice.deleteLoad("loadid:6"));
		System.err.println("res: " + response);
		System.err.println("++++++++++++++++++++++++++++++++++++" );
		
		assertEquals(response, loadservice.deleteLoad("loadid:6"));
	}
	
	public LoadRequest createLoadRequest()
	{
		LoadRequest loadrequest =new LoadRequest();
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
