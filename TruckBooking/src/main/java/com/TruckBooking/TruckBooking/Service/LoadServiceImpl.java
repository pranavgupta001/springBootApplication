package com.TruckBooking.TruckBooking.Service;

import java.util.List;
import java.util.Optional;
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
	
	@Override
	public LoadResponse addLoad(LoadRequest loadRequest) {
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
	
	@Override
	public List<Load> getLoads(String loadingPoint, String shipperId, String truckType, String date) {
		// TODO Auto-generated method stub
		if(loadingPoint!=null) {
			return loadDao.findByLoadingPoint(loadingPoint); 
		}
			
		if(shipperId!=null) {
			return loadDao.findByShipper(shipperId);
		}
			
		if(truckType!=null) {
			return loadDao.findByTruckType(truckType);
		}
		
		if(date!=null) {
			return loadDao.findByDate(date);
		}
			
		return loadDao.findAll();
	}
	
	@Override
	public Load getLoad(String loadId) {
		// TODO Auto-generated method stub
		Optional<Load> L = loadDao.findById(loadId);
		if(L.isEmpty()) {
			return null;
		}
		return L.get();
	}

	@Override
	public LoadResponse updateLoad(String loadId, LoadRequest loadRequest) {
		LoadResponse loadResponse = new LoadResponse();
		Load load = new Load();
		
		Optional<Load> L = loadDao.findById(loadId);
		if(L.isPresent()) {
			load = L.get();
		}
		else {
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
		if(loadRequest.getDate()!=null) {
			load.setDate(loadRequest.getDate());
		}
		
		loadDao.save(load);
		loadResponse.setStatus(CommonConstants.updateSuccess);
		return loadResponse;
	}

	@Override
	public LoadResponse deleteLoad(String id) {
		LoadResponse loadResponse = new LoadResponse();
		Optional<Load> L = loadDao.findById(id);
		if(L.isEmpty()) {
			loadResponse.setStatus(CommonConstants.AccountNotFoundError);
			return loadResponse;
		}
		loadDao.delete(L.get());
		loadResponse.setStatus(CommonConstants.deleteSuccess);
		return loadResponse;
	}

}
