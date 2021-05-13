package com.TruckBooking.TruckBooking.Controller;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.TruckBooking.TruckBooking.Entities.Load;
import com.TruckBooking.TruckBooking.Model.LoadRequest;
import com.TruckBooking.TruckBooking.Model.LoadResponse;
import com.TruckBooking.TruckBooking.Service.LoadServiceImpl;

@RestController
public class LoadController {

	@Autowired
	public LoadServiceImpl loadService;
	
	@PostMapping("/load")
	public LoadResponse load(@RequestBody LoadRequest loadrequest) {
		return loadService.addLoad(loadrequest);
	}
	
	@GetMapping("/load")
	public List<Load> findLoads(@RequestParam(name="loadingPoint",required=false) String loadingPoint,
			@RequestParam(name="shipperId",required=false) String shipperId,
			@RequestParam(name="truckType",required=false) String truckType,
			@RequestParam(name="date",required=false) String date){
		
		return loadService.getLoads(loadingPoint, shipperId, truckType, date);
	}
	
	@GetMapping("/load/{loadId}")
	public Load findLoad(@PathVariable String loadId){ 
		return loadService.getLoad(loadId);
	}
	
	@PutMapping("/load/{loadId}")
	public LoadResponse updateLoad(@PathVariable String loadId,@RequestBody LoadRequest loadrequest) {
		return loadService.updateLoad(loadId, loadrequest);
		
	}
	
	@DeleteMapping("/load/{loadId}")
	public LoadResponse deleteLoad(@PathVariable String loadId) {
		return loadService.deleteLoad(loadId);
	}
	
}
