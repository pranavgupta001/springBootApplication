package com.TruckBooking.TruckBooking.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.TruckBooking.TruckBooking.entities.Load;
import com.TruckBooking.TruckBooking.service.LoadServiceImpl;

@RestController
public class LoadController {

	@Autowired
	public LoadServiceImpl LoadService;
	@PostMapping("/PostAload")
	public List<Load> PostAload(Load load) {
		return this.LoadService.PostAload(load);
	}
	@GetMapping("/findLoad")
	public List<Load> findLoad(){
		return this.LoadService.findLoad();
	}
	@PutMapping("/updateRegisterShipment")
	public Load updatePostAload(Load load) {
		return this.LoadService.updatePostAload(load);
		
	}
	@DeleteMapping("/deleteTruckRequirement")
	public void deleteTruckRequirement(Load load) {
		this.LoadService.deleteTruckRequirement(load);
	}
	
}
