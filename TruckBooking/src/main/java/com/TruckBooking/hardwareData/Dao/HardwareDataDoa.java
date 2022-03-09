package com.TruckBooking.hardwareData.Dao;

import com.TruckBooking.hardwareData.Entities.Hardware;
import com.TruckBooking.routeData.Entities.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface HardwareDataDoa  extends JpaRepository<Hardware, String> {
    List<Hardware> findByImei(String imei);
}
