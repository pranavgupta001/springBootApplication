package com.TruckBooking.TruckBooking.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.TruckBooking.TruckBooking.entities.Load;
import com.TruckBooking.TruckBooking.model.LoadRequest;
import com.TruckBooking.TruckBooking.model.LoadResponse;
import com.TruckBooking.TruckBooking.dao.LoadDao;

@Service
public class LoadServiceImpl implements LoadService {
	@Autowired
	LoadDao loadDao;
	
	public LoadResponse load(LoadRequest loadRequest) {
		// TODO Auto-generated method stub
		LoadResponse loadResponse = new LoadResponse();
		Load load = new Load();
		load.setId(UUID.randomUUID());
		if(loadRequest.getComment()!=null) {
			load.setComment(loadRequest.getComment());
		}
		load.setUnloadingPoint(loadRequest.getUnloadingPoint());
		load.setLoadingPoint(loadRequest.getLoadingPoint());
		load.setNoOfTrucks(loadRequest.getNoOfTrucks());
		load.setTruckType(loadRequest.getTruckType());
		load.setProductType(loadRequest.getProductType());
		load.setWeight(loadRequest.getWeight());
		load.setStatus("pending");
		loadResponse.setStatus("pending");
		loadDao.save(load);	
		return loadResponse;
	}

	@Override
	public List<Load> findLoad(UUID ownerId) {
		// TODO Auto-generated method stub
		if(ownerId!=null)
		 return loadDao.findAllByOwnerId(ownerId);
		return loadDao.findByStatus("pending");
	}

	public LoadResponse updatePostAload(LoadRequest loadrequest) {
		// TODO Auto-generated method stub
		LoadResponse loadResponse = new LoadResponse();
		return loadResponse;
	}

	@Override
	public void deleteTruckRequirement(UUID id) {
		// TODO Auto-generated method stub
		loadDao.deleteById(id);
		
	}
	
}
