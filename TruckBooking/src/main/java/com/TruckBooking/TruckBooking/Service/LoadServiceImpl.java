package com.TruckBooking.TruckBooking.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TruckBooking.TruckBooking.Constants.CommonConstants;
import com.TruckBooking.TruckBooking.Dao.LoadDao;
import com.TruckBooking.TruckBooking.Entities.Load;
import com.TruckBooking.TruckBooking.Model.LoadRequest;
import com.TruckBooking.TruckBooking.Model.LoadResponse;

@Service
public class LoadServiceImpl implements LoadService {
	@Autowired
	LoadDao loadDao;
	
	public LoadResponse load(LoadRequest loadRequest) {
		// TODO Auto-generated method stub
		LoadResponse loadResponse = new LoadResponse();
		Load load = new Load();
		
		if(loadRequest.getLoadingPoint()==null) {
			loadResponse.setStatus(CommonConstants.loadingError);
			return loadResponse;
		}
		if(loadRequest.getUnloadingPoint()==null) {
			loadResponse.setStatus(CommonConstants.unloadingError);
			return loadResponse;
		}
		if(loadRequest.getNoOfTrucks()==null) {
			loadResponse.setStatus(CommonConstants.noOfTrucksError);
			return loadResponse;
		}
		if(loadRequest.getTruckType()==null) {
			loadResponse.setStatus(CommonConstants.truckTypeError);
			return loadResponse;
		}
		if(loadRequest.getProductType()==null) {
			loadResponse.setStatus(CommonConstants.productTypeError);
			return loadResponse;
		}
		if(loadRequest.getWeight()==null) {
			loadResponse.setStatus(CommonConstants.weightError);
			return loadResponse;
		}
		if(loadRequest.getDate()==null) {
			loadResponse.setStatus(CommonConstants.dateError);
			return loadResponse;
		}
		
		load.setId("load:"+UUID.randomUUID());
		load.setUnloadingPoint(loadRequest.getUnloadingPoint());
		load.setLoadingPoint(loadRequest.getLoadingPoint());
		load.setNoOfTrucks(loadRequest.getNoOfTrucks());
		load.setShipperId(loadRequest.getShipperId());
		load.setTruckType(loadRequest.getTruckType());
		load.setProductType(loadRequest.getProductType());
		load.setWeight(loadRequest.getWeight());
		load.setStatus(CommonConstants.pending);
		load.setComment(loadRequest.getComment());
		load.setDate(loadRequest.getDate());
		loadDao.save(load);
		
		loadResponse.setStatus(CommonConstants.pending);
		return loadResponse;
	}
	
	public List<Load> getLoads(String ownerId, String loadingPoint, String shipperId, String truckType) {
		// TODO Auto-generated method stub
		List<Load> list = new ArrayList<Load>();
		try {
			if(ownerId!=null) {
				list.add(loadDao.findById(ownerId).get());
				return list; 
			}
			
			if(loadingPoint!=null) {
				return loadDao.findByLoadingPoint(loadingPoint); 
			}
			
			if(shipperId!=null) {
				return loadDao.findByShipper(shipperId);
			}
			
			if(truckType!=null) {
				return loadDao.findByTruckType(truckType);
			}
		}
		
		catch(NoSuchElementException e) {
			return list;
		}
		
		return loadDao.findAll();
	}

	@Override
	public LoadResponse updatePostAload(String loadId, LoadRequest loadRequest) {
		LoadResponse loadResponse = new LoadResponse();
		Load load = new Load();
		
		try {
			load = loadDao.findById(loadId).get();
		}
		catch(NoSuchElementException e) {
			loadResponse.setStatus(CommonConstants.AccountNotFoundError);
			return loadResponse;
		}
		
		if(loadRequest.getLoadingPoint()!=null) {
			load.setLoadingPoint(loadRequest.getLoadingPoint());
		}
		if(loadRequest.getUnloadingPoint()!=null) {
			load.setUnloadingPoint(loadRequest.getUnloadingPoint());
		}
		if(loadRequest.getProductType()!=null) {
			load.setProductType(loadRequest.getProductType());
		}
		if(loadRequest.getTruckType()!=null) {
			load.setTruckType(loadRequest.getTruckType());
		}
		if(loadRequest.getNoOfTrucks()!=null) {
			load.setNoOfTrucks(loadRequest.getNoOfTrucks());
		}
		if(loadRequest.getWeight()!=null) {
			load.setWeight(loadRequest.getWeight());
		}
		if(loadRequest.getComment()!=null) {
			load.setComment(loadRequest.getComment());
		}
		if(loadRequest.getStatus()!=null) {
			load.setStatus(loadRequest.getStatus());
		}
		
		loadDao.save(load);
		loadResponse.setStatus(CommonConstants.updateSuccess);
		return loadResponse;
	}

	@Override
	public void deleteTruckRequirement(String id) {
		try {
			loadDao.delete(loadDao.findById(id).get());
		}
		catch(NoSuchElementException e) {
			
		}
	}
	

}
