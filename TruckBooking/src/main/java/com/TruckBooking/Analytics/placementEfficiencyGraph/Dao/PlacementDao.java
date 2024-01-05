package com.TruckBooking.Analytics.placementEfficiencyGraph.Dao;

import com.TruckBooking.Analytics.placementEfficiencyGraph.Entities.Placements;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlacementDao extends JpaRepository<Placements, String> {

    Optional<List<Placements>> findByShipperId(String shipperId);

}
