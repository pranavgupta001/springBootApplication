package com.TruckBooking.Analytics.placementEfficiencyGraph.Dao;

import com.TruckBooking.Analytics.placementEfficiencyGraph.Entities.PlacementInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PlacementDao extends JpaRepository<PlacementInfo, String> {

    List<PlacementInfo> findByShipperId(String shipperId);

}
