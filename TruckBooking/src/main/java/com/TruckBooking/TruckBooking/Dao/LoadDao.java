package com.TruckBooking.TruckBooking.Dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.TruckBooking.TruckBooking.Entities.Load;


@Repository
public interface LoadDao extends JpaRepository<Load , String> {

	@Query("select l from Load l")
	List<Load> findByAll(Pageable pageable);
		
	List<Load> findByPostLoadId(String postLoadId, Pageable pageable);
	
	Optional<Load> findByLoadId(String loadId);
	
	List<Load> findByLoadingPointCityAndUnloadingPointCity(String loadingPointCity, String unloadingPointCity, Pageable pageable);
	
	List<Load> findByTruckType(String truckType, Pageable pageable);

	List<Load> findByLoadDate(String loadDate, Pageable pageable);
	
	List<Load> findByLoadingPointCity(String loadingPointCity, Pageable pageable);
	
	List<Load> findByLoadingPointState(String loadingPointState, Pageable pageable);
	
	List<Load> findByUnloadingPointCity(String unloadingPointCity, Pageable pageable);
	
	List<Load> findByUnloadingPointState(String loadingPointState, Pageable pageable);
}
