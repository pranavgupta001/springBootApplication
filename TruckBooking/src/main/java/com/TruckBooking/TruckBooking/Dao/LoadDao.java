package com.TruckBooking.TruckBooking.Dao;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.TruckBooking.TruckBooking.Entities.Load;


@Repository
public interface LoadDao extends JpaRepository<Load , String> {
	//public List<Load> findByStatus(String status);
	@Query("select l from Load l where l.shipperId = :shipperId")
	List<Load> findByShipper(String shipperId);

	@Query("select l from Load l where l.loadingPoint = :loadingPoint")
	List<Load> findByLoadingPoint(String loadingPoint);
	
	@Query("select l from Load l where l.truckType = :truckType")
	List<Load> findByTruckType(String truckType);
	//public List<Load> findAllByOwnerId(UUID ownerId);
}
