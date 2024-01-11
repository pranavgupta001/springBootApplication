package com.TruckBooking.Analytics.placementEfficiencyGraph.Dao;

import com.TruckBooking.Analytics.placementEfficiencyGraph.Entities.PlacementInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlacementInfoDao extends JpaRepository<PlacementInfo, String> {

    //Optional<PlacementInfo> findByPlacementInfoListTransporterIdAndYearAndMonthAndWeek(String transporterId, int year, int month, int week);
}
