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
public class Controller {

	@Autowired
	public LoadServiceImpl LoadService;
	@PostMapping("/searchTruck")
	public List<Load> searchTrucks(Load load) {
		return this.LoadService.searchTrucks(load);
	}
	@GetMapping("/findLoad")
	public List<Load> findLoad(){
		return this.LoadService.findLoad();
	}
	@PutMapping("/updateSearchTruck")
	public Load updateSearchTruck(Load load) {
		return this.LoadService.updateSearchTruck(load);
		
	}
	@DeleteMapping("/deleteTruckRequirement")
	public void deleteTruckRequirement(Load load) {
		this.LoadService.deleteTruckRequirement(load);
	}
	
	
}
