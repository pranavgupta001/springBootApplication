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
import com.TruckBooking.TruckBooking.Exception.EntityNotFoundException;
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
	public Load addLoad(Load load) {	

		String unitValue=String.valueOf(load.getUnitValue());

		if("PER_TON".equals(unitValue))
		{
			load.setUnitValue(Load.UnitValue.PER_TON);
		}
		else if("PER_TRUCK".equals(unitValue))
		{
			load.setUnitValue(Load.UnitValue.PER_TRUCK);
		}

		load.setLoadId("load:"+UUID.randomUUID());
		load.setStatus(CommonConstants.pending);

		loadDao.save(load);
		return  load;
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
		Optional<Load> load=loadDao.findByLoadId(loadId);
		if(load.isEmpty())
			throw new EntityNotFoundException(Load.class, "id", loadId.toString());

		return load.get();
	}

	@Override
	public UpdateLoadResponse updateLoad(String loadId, Load updateLoad) {
		UpdateLoadResponse updateloadResponse = new UpdateLoadResponse();
		Optional<Load> Id=loadDao.findByLoadId(loadId);

		if(Id.isEmpty())
			throw new EntityNotFoundException(Load.class, "id", loadId.toString());

		Load load=Id.get();

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
