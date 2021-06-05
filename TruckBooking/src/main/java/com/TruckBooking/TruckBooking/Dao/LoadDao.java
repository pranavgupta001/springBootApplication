package com.TruckBooking.TruckBooking.Dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.TruckBooking.TruckBooking.Entities.Load;


@Repository
public interface LoadDao extends JpaRepository<Load , String> {
	@Query("select l from Load l where l.id = :Id")
	List<Load> findByid(String Id);
	
	Optional<Load> findByLoadId(String loadId);
	
	@Query("select l from Load l where l.loadingPointCity = :loadingPointCity AND l.unloadingPointCity = :unloadingPointCity")
	List<Load> findByLoadAndUnloadPoint(String loadingPointCity, String unloadingPointCity);
	
	List<Load> findByTruckType(String truckType);

	List<Load> findByDate(String date);
	
	List<Load> findByLoadingPointCity(String loadingPointCity);
	
	List<Load> findByLoadingPointState(String loadingPointState);
	
	List<Load> findByUnloadingPointCity(String unloadingPointCity);
	
	List<Load> findByUnloadingPointState(String loadingPointState);
}
