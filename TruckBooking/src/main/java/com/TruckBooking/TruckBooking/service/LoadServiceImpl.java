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
	public List<Load> PostAload(Load load) {
		// TODO Auto-generated method stub
		load.setStatus("pending");
		
		loadDao.save(load);
		
		return loadDao.findAll();
	}

	@Override
	public List<Load> findLoad() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Load updatePostAload(Load load) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteTruckRequirement(Load load) {
		// TODO Auto-generated method stub
		
	}
	
}
