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
	public Load updateLoad(String loadId, Load updateLoad) {
		Optional<Load> Id=loadDao.findByLoadId(loadId);

		if(Id.isEmpty())
			throw new EntityNotFoundException(Load.class, "id", loadId.toString());

		Load load=Id.get();

		temp=updateLoad.getLoadingPoint();
		if(temp !=null && !temp.isBlank()) {
			load.setLoadingPoint(temp.trim());
		}

		temp=updateLoad.getLoadingPointCity();
		if(temp !=null && !temp.isBlank()) {
			load.setLoadingPointCity(temp.trim());
		}

		temp=updateLoad.getLoadingPointState();
		if(temp != null && !temp.isBlank()) {
			load.setLoadingPointState(temp.trim());
		}

		temp=updateLoad.getUnloadingPoint();
		if(temp != null && !temp.isBlank()) {
			load.setUnloadingPoint(temp.trim());
		}

		temp=updateLoad.getUnloadingPointCity();
		if(temp != null && !temp.isBlank()) {
			load.setUnloadingPointCity(temp.trim());
		}
		
		temp=updateLoad.getUnloadingPointState();
		if(temp != null && !temp.isBlank()) {
			load.setUnloadingPointState(temp.trim());
		}
		
		temp=updateLoad.getNoOfTrucks();
		if(temp != null && !temp.isBlank()) {
			load.setNoOfTrucks(temp.trim());
		}
		
		temp=updateLoad.getTruckType();
		if(temp != null && !temp.isBlank()) {
			
			load.setTruckType(temp.trim());
		}
		
		temp=updateLoad.getProductType();
		if(temp != null && !temp.isBlank()) {
			
			load.setProductType(temp.trim());
		}
		
		temp=updateLoad.getWeight();
		if(temp != null && !temp.isBlank()) {
			load.setWeight(temp.trim());
		}
		
		temp=updateLoad.getLoadDate();
		if(temp != null && !temp.isBlank()) {
			load.setLoadDate(temp.trim());
		}
		
		temp=updateLoad.getPostLoadId();
		if(temp != null && !temp.isBlank()) {
			load.setPostLoadId(temp);
		}
		
		temp=updateLoad.getStatus();
		if(temp != null && !temp.isBlank()) {
			load.setStatus(temp.trim());
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

		return load;
	}

	@Override
	public void deleteLoad(String loadId) {
		Optional<Load> L = loadDao.findByLoadId(loadId);
		if(L.isEmpty())
			throw new EntityNotFoundException(Load.class, "id", loadId.toString());
		loadDao.delete(L.get());
	}

}
