package com.TruckBooking.TruckBooking.controller;
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

import com.TruckBooking.TruckBooking.entities.Load;
import com.TruckBooking.TruckBooking.model.LoadRequest;
import com.TruckBooking.TruckBooking.model.LoadResponse;
import com.TruckBooking.TruckBooking.service.LoadServiceImpl;

@RestController
public class LoadController {

	@Autowired
	public LoadServiceImpl loadService;
	
	@PostMapping("/load")
	public LoadResponse load(@RequestBody LoadRequest loadrequest) {
		//System.out.print(load.getId());
		return loadService.load(loadrequest);
	}
	@GetMapping("/load")
	public List<Load> findLoads(@RequestParam(name="ownerId",required=false) UUID ownerId){
			return loadService.findLoad(ownerId);
	}
	
	//@GetMapping("/load/{id}")
	//public Load findLoad(String id)
	//{
		//return loadService.findOneLoad(id);
	//}
	@PutMapping("/load")
	public LoadResponse updatePostAload(@RequestBody LoadRequest loadrequest) {
		
		return loadService.updatePostAload(loadrequest);
		
	}
	@DeleteMapping("/load/{id}")
	public void deleteTruckRequirement(@PathVariable UUID id ) {
		loadService.deleteTruckRequirement(id);
	}
	
}
