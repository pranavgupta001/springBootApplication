package com.TruckBooking.TruckBooking.Service;


import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TruckBooking.TruckBooking.Constants.CommonConstants;
import com.TruckBooking.TruckBooking.Dao.LoadDao;
import com.TruckBooking.TruckBooking.Entities.Load;
import com.TruckBooking.TruckBooking.Model.LoadRequest;
import com.TruckBooking.TruckBooking.Response.CreateLoadResponse;
import com.TruckBooking.TruckBooking.Response.DeleteLoadResponse;
import com.TruckBooking.TruckBooking.Response.UpdateLoadResponse;

@Service
public class LoadServiceImpl implements LoadService {
	@Autowired
	LoadDao loadDao;
	
	String temp="";
	@Override
	public CreateLoadResponse addLoad(LoadRequest addLoad) {
		// TODO Auto-generated method stub
		CreateLoadResponse createloadResponse = new CreateLoadResponse();
		Load load = new Load();

		if(addLoad.getLoadingPoint()==null) {
			createloadResponse.setStatus(CommonConstants.loadingError);
			return createloadResponse;
		}
		else {
			temp=addLoad.getLoadingPoint().trim();
			if(temp.length() < 1) {
				createloadResponse.setStatus(CommonConstants.emptyloadingpoint);
				return createloadResponse;
			}
			load.setLoadingPoint(temp);
		}
		
		if(addLoad.getLoadingPointCity()==null) {
			createloadResponse.setStatus(CommonConstants.loadingCityError);
			return createloadResponse;
		}
		else {
			temp=addLoad.getLoadingPointCity().trim();
			if(temp.length() < 1) {
				createloadResponse.setStatus(CommonConstants.emptyloadingcity);
				return createloadResponse;
			}
			load.setLoadingPointCity(temp);
		}
		
		if(addLoad.getLoadingPointState()==null) {
			createloadResponse.setStatus(CommonConstants.loadingStateError);
			return createloadResponse;
		}
		else {
			temp=addLoad.getLoadingPointState().trim();
			if(temp.length() < 1) {
				createloadResponse.setStatus(CommonConstants.emptyloadingstate);
				return createloadResponse;
			}
			load.setLoadingPointState(temp);
		}
		
		if(addLoad.getUnloadingPoint()==null) {
			createloadResponse.setStatus(CommonConstants.unloadingError);
			return createloadResponse;
		}
		else {
			temp=addLoad.getUnloadingPoint().trim();
			if(temp.length() < 1) {
				createloadResponse.setStatus(CommonConstants.emptyunloadingpoint);
				return createloadResponse;
			}
			load.setUnloadingPoint(temp);
		}
		
		if(addLoad.getUnloadingPointCity()==null) {
			createloadResponse.setStatus(CommonConstants.unloadingCityError);
			return createloadResponse;
		}
		else {
			temp=addLoad.getUnloadingPointCity().trim();
			if(temp.length() < 1) {
				createloadResponse.setStatus(CommonConstants.emptyunloadingcity);
				return createloadResponse;
			}
			load.setUnloadingPointCity(temp);
		}
		
		if(addLoad.getUnloadingPointState()==null) {
			createloadResponse.setStatus(CommonConstants.unloadingStateError);
			return createloadResponse;
		}
		else {
			temp=addLoad.getUnloadingPointState().trim();
			if(temp.length() < 1) {
				createloadResponse.setStatus(CommonConstants.emptyunloadingstate);
				return createloadResponse;
			}
			load.setUnloadingPointState(temp);
		}
		
		if(addLoad.getNoOfTrucks()==null) {
			createloadResponse.setStatus(CommonConstants.noOfTrucksError);
			return createloadResponse;
		}
		else {
			temp=addLoad.getNoOfTrucks().trim();
			if(temp.length() < 1) {
				createloadResponse.setStatus(CommonConstants.emptytruckno);
				return createloadResponse;
			}
			load.setNoOfTrucks(temp);
		}
		
		if(addLoad.getTruckType()==null) {
			createloadResponse.setStatus(CommonConstants.truckTypeError);
			return createloadResponse;
		}
		else {
			temp=addLoad.getTruckType().trim();
			if(temp.length() < 1) {
				createloadResponse.setStatus(CommonConstants.emptytrucktype);
				return createloadResponse;
			}
			load.setTruckType(temp);
		}
		
		if(addLoad.getProductType()==null) {
			createloadResponse.setStatus(CommonConstants.productTypeError);
			return createloadResponse;
		}
		else {
			temp=addLoad.getProductType().trim();
			if(temp.length() < 1) {
				createloadResponse.setStatus(CommonConstants.emptyproducttype);
				return createloadResponse;
			}
			load.setProductType(temp);
		}
		
		if(addLoad.getWeight()==null) {
			createloadResponse.setStatus(CommonConstants.weightError);
			return createloadResponse;
		}
		else {
			temp=addLoad.getWeight().trim();
			if(temp.length() < 1) {
				createloadResponse.setStatus(CommonConstants.emptyweight);
				return createloadResponse;
			}
			load.setWeight(temp);
		}
		
		if(addLoad.getPostLoadId()==null) {
			createloadResponse.setStatus(CommonConstants.idError);
			return createloadResponse;
		}
		else {
			temp=addLoad.getPostLoadId().trim();
			if(temp.length() < 1) {
				createloadResponse.setStatus(CommonConstants.emptypostloadid);
				return createloadResponse;
			}
			load.setPostLoadId(temp);
		}
		
		if(addLoad.getLoadDate()==null) {
			createloadResponse.setStatus(CommonConstants.dateError);
			return createloadResponse;
		}
		else {
			temp=addLoad.getLoadDate().trim();
			if(temp.length() < 1) {
				createloadResponse.setStatus(CommonConstants.emptydate);
				return createloadResponse;
			}
			load.setLoadDate(temp);
		}
		
		if(addLoad.getRate() != null)
		{
			load.setRate(addLoad.getRate());
		}

		if(addLoad.getUnitValue() != null)
		{
			String unitValue=String.valueOf(addLoad.getUnitValue());
			
			if("PER_TON".equals(unitValue))
			{
				load.setUnitValue(Load.UnitValue.PER_TON);
			}
			else if("PER_TRUCK".equals(unitValue))
			{
				load.setUnitValue(Load.UnitValue.PER_TRUCK);
			}
		}
		
	    load.setComment(addLoad.getComment());
		load.setLoadId("load:"+UUID.randomUUID());
		load.setStatus(CommonConstants.pending);
		
		loadDao.save(load);
		
		createloadResponse.setStatus(CommonConstants.pending);
		createloadResponse.setLoadId(load.getLoadId());
		createloadResponse.setLoadingPoint(load.getLoadingPoint());
		createloadResponse.setLoadingPointCity(load.getLoadingPointCity());
		createloadResponse.setLoadingPointState(load.getLoadingPointState());
		createloadResponse.setUnloadingPoint(load.getUnloadingPoint());
		createloadResponse.setUnloadingPointCity(load.getUnloadingPointCity());
		createloadResponse.setUnloadingPointState(load.getUnloadingPointState());
		createloadResponse.setNoOfTrucks(load.getNoOfTrucks());
		createloadResponse.setProductType(load.getProductType());
		createloadResponse.setTruckType(load.getTruckType());
		createloadResponse.setWeight(load.getWeight());
		createloadResponse.setLoadDate(load.getLoadDate());
		createloadResponse.setComment(load.getComment());
		createloadResponse.setPostLoadId(load.getPostLoadId());
		createloadResponse.setRate(load.getRate());
		
		if(load.getUnitValue() != null)
		{
			String unitValue=String.valueOf(load.getUnitValue());
			if("PER_TON".equals(unitValue))
			{
				createloadResponse.setUnitValue(CreateLoadResponse.UnitValue.PER_TON);
			}
			else if("PER_TRUCK".equals(unitValue))
			{
				createloadResponse.setUnitValue(CreateLoadResponse.UnitValue.PER_TRUCK);
			}
		}
		
		return createloadResponse;
	}
	
	@Override
	public List<Load> getLoads(Integer pageNo, String loadingPointCity, String unloadingPointCity,
			String postLoadId, String truckType, String loadDate, boolean suggestedLoads) {
		
		if (pageNo == null)
			pageNo = 0;
		
		Pageable currentPage = PageRequest.of(pageNo, CommonConstants.pagesize);
		
		if(suggestedLoads)
		{
			List<Load> load = loadDao.findByAll(currentPage);
			Collections.reverse(load);
			return load;
		}
		
		if(loadingPointCity != null) {
			if(unloadingPointCity != null) {
				List<Load> load = loadDao.findByLoadingPointCityAndUnloadingPointCity(loadingPointCity, unloadingPointCity, currentPage);
				Collections.reverse(load);
				return load;
			}
			List<Load> load = loadDao.findByLoadingPointCity(loadingPointCity, currentPage);   
			Collections.reverse(load);
			return load;
		}
		
		if(unloadingPointCity != null)
		{
			List<Load> load = loadDao.findByUnloadingPointCity(unloadingPointCity, currentPage);
			Collections.reverse(load);
			return load;
		}
			
		if(postLoadId != null) {
			List<Load> load = loadDao.findByPostLoadId(postLoadId, currentPage);            
			Collections.reverse(load);
			return load;
		}
			 
		if(truckType != null) {
			List<Load> load = loadDao.findByTruckType(truckType, currentPage);               
			Collections.reverse(load);
			return load;
		}
		
		if(loadDate != null) {
			List<Load> load = loadDao.findByLoadDate(loadDate, currentPage);                  
			Collections.reverse(load);
			return load;
		}
		
		List<Load> load = loadDao.findByAll(currentPage);
		Collections.reverse(load);
		return load;
	}
	

	@Override
	public Load getLoad(String loadId) {
		Optional<Load> L = loadDao.findByLoadId(loadId);
		if(L.isEmpty()) {
			return null;
		}
		return L.get();
	}

	@Override
	public UpdateLoadResponse updateLoad(String loadId, LoadRequest updateLoad) {
		UpdateLoadResponse updateloadResponse = new UpdateLoadResponse();
		Load load = new Load();
		
		Optional<Load> L = loadDao.findByLoadId(loadId);
		if(L.isPresent()) {
			load = L.get();
			
			if(updateLoad.getLoadingPoint() != null) {
				temp=updateLoad.getLoadingPoint().trim();
				if(temp.length() < 1) {
					updateloadResponse.setStatus(CommonConstants.emptyloadingpoint);
					return updateloadResponse;
				}
				load.setLoadingPoint(temp);
			}
			if(updateLoad.getLoadingPointCity() != null) {
				temp=updateLoad.getLoadingPointCity().trim();
				if(temp.length() < 1) {
					updateloadResponse.setStatus(CommonConstants.emptyloadingcity);
					return updateloadResponse;
				}
				load.setLoadingPointCity(temp);
			}
			if(updateLoad.getLoadingPointState() != null) {
				temp=updateLoad.getLoadingPointState().trim();
				if(temp.length() < 1) {
					updateloadResponse.setStatus(CommonConstants.emptyloadingstate);
					return updateloadResponse;
				}
				load.setLoadingPointState(temp);
			}
			
			if(updateLoad.getUnloadingPoint()!= null) {
				temp=updateLoad.getUnloadingPoint().trim();
				if(temp.length() < 1) {
					updateloadResponse.setStatus(CommonConstants.emptyunloadingpoint);
					return updateloadResponse;
				}
				load.setUnloadingPoint(temp);
			}
			if(updateLoad.getUnloadingPointCity() != null) {
				temp=updateLoad.getUnloadingPointCity().trim();
				if(temp.length() < 1) {
					updateloadResponse.setStatus(CommonConstants.emptyunloadingcity);
					return updateloadResponse;
				}
				load.setUnloadingPointCity(temp);
			}
			if(updateLoad.getUnloadingPointState() != null) {
				temp=updateLoad.getUnloadingPointState().trim();
				if(temp.length() < 1) {
					updateloadResponse.setStatus(CommonConstants.emptyunloadingstate);
					return updateloadResponse;
				}
				load.setUnloadingPointState(temp);
			}
			
			if(updateLoad.getNoOfTrucks() != null) {
				temp=updateLoad.getNoOfTrucks().trim();
				if(temp.length() < 1) {
					updateloadResponse.setStatus(CommonConstants.emptytruckno);
					return updateloadResponse;
				}
				load.setNoOfTrucks(temp);
			}
			
			if(updateLoad.getTruckType() != null) {
				temp=updateLoad.getTruckType().trim();
				if(temp.length() < 1) {
					updateloadResponse.setStatus(CommonConstants.emptytrucktype);
					return updateloadResponse;
				}
				load.setTruckType(temp);
			}
			
			if(updateLoad.getProductType() != null) {
				temp=updateLoad.getProductType().trim();
				if(temp.length() < 1) {
					updateloadResponse.setStatus(CommonConstants.emptyproducttype);
					return updateloadResponse;
				}
				load.setProductType(temp);
			}
			
			if(updateLoad.getWeight() != null) {
				temp=updateLoad.getWeight().trim();
				if(temp.length() < 1) {
					updateloadResponse.setStatus(CommonConstants.emptyweight);
					return updateloadResponse;
				}
				load.setWeight(temp);
			}
			
			if(updateLoad.getLoadDate() != null) {
				temp=updateLoad.getLoadDate().trim();
				if(temp.length() < 1) {
					updateloadResponse.setStatus(CommonConstants.emptydate);
					return updateloadResponse;
				}
				load.setLoadDate(temp);
			}
			
			if(updateLoad.getPostLoadId() != null) {
				temp=updateLoad.getPostLoadId().trim();
				if(temp.length() < 1) {
					updateloadResponse.setStatus(CommonConstants.emptypostloadid);
					return updateloadResponse;
				}
				load.setPostLoadId(temp);
			}
			
			if(updateLoad.getStatus() != null) {
				temp=updateLoad.getStatus().trim();
				if(temp.length() < 1) {
					updateloadResponse.setStatus("Empty status");
					return updateloadResponse;
				}
				load.setStatus(temp);
			}
			
			if(updateLoad.getComment() != null)
			{
				load.setComment(updateLoad.getComment());
			}
			
			if(updateLoad.getRate() != null)
			{
				load.setRate(updateLoad.getRate());
			}
			
			if(updateLoad.getUnitValue() != null)
			{
				String unitValue=String.valueOf(updateLoad.getUnitValue());
				
				if("PER_TON".equals(unitValue))
				{
					load.setUnitValue(Load.UnitValue.PER_TON);
				}
				else if("PER_TRUCK".equals(unitValue))
				{
					load.setUnitValue(Load.UnitValue.PER_TRUCK);
				}
			}
			
			loadDao.save(load);
			
			updateloadResponse.setLoadId(load.getLoadId());
			updateloadResponse.setLoadingPoint(load.getLoadingPoint());
			updateloadResponse.setLoadingPointCity(load.getLoadingPointCity());
			updateloadResponse.setLoadingPointState(load.getLoadingPointState());
			updateloadResponse.setUnloadingPoint(load.getUnloadingPoint());
			updateloadResponse.setUnloadingPointCity(load.getUnloadingPointCity());
			updateloadResponse.setUnloadingPointState(load.getUnloadingPointState());
			updateloadResponse.setNoOfTrucks(load.getNoOfTrucks());
			updateloadResponse.setProductType(load.getProductType());
			updateloadResponse.setTruckType(load.getTruckType());
			updateloadResponse.setWeight(load.getWeight());
			updateloadResponse.setStatus(load.getStatus());
			updateloadResponse.setLoadDate(load.getLoadDate());
			updateloadResponse.setComment(load.getComment());
			updateloadResponse.setPostLoadId(load.getPostLoadId());
			updateloadResponse.setRate(load.getRate());
			
			if(load.getUnitValue() != null)
			{
				String unitValue=String.valueOf(load.getUnitValue());
				if("PER_TON".equals(unitValue))
				{
					updateloadResponse.setUnitValue(UpdateLoadResponse.UnitValue.PER_TON);
				}
				else if("PER_TRUCK".equals(unitValue))
				{
					updateloadResponse.setUnitValue(UpdateLoadResponse.UnitValue.PER_TRUCK);
				}
			}
			
			return updateloadResponse;
		}
		else {
			updateloadResponse.setStatus(CommonConstants.AccountNotFoundError);
			return updateloadResponse;
		}
	}

	@Override
	public DeleteLoadResponse deleteLoad(String loadId) {
		DeleteLoadResponse deleteloadResponse = new DeleteLoadResponse();
		Optional<Load> L = loadDao.findByLoadId(loadId);
		if(L.isEmpty()) {
			deleteloadResponse.setStatus(CommonConstants.AccountNotFoundError);
			return deleteloadResponse;
		}
		loadDao.delete(L.get());
		deleteloadResponse.setStatus(CommonConstants.deleteSuccess);
		return deleteloadResponse;
	}

}
