package com.TruckBooking.TruckBooking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TruckBooking.TruckBooking.entities.Load;
import com.TruckBooking.TruckBooking.dao.LoadDao;

@Service
public class LoadServiceImpl implements LoadService {
	@Autowired
	LoadDao loadDao;
	
	@Override
	public List<Load> searchTrucks(Load load) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Load> findLoad() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Load updateSearchTruck(Load load) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteTruckRequirement(Load load) {
		// TODO Auto-generated method stub
		
	}
	
}
