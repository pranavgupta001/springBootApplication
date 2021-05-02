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
		return loadService.load(loadrequest);
	}
	
	@GetMapping("/load")
	public List<Load> findLoads(@RequestParam(name="ownerId",required=false) String ownerId, 
			@RequestParam(name="loadingPoint",required=false) String loadingPoint,
			@RequestParam(name="shipperId",required=false) String shipperId,
			@RequestParam(name="truckType",required=false) String truckType){
		return loadService.getLoads(ownerId, loadingPoint, shipperId, truckType);	
	}

	
	@PutMapping("/load/{loadId}")
	public LoadResponse updatePostAload(@PathVariable String loadId,@RequestBody LoadRequest loadrequest) {
		return loadService.updatePostAload(loadId, loadrequest);
		
	}
	@DeleteMapping("/load/{id}")
	public void deleteTruckRequirement(@PathVariable String id ) {
		loadService.deleteTruckRequirement(id);
	}
	
}
