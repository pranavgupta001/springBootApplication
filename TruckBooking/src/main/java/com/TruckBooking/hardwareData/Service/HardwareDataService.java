package com.TruckBooking.hardwareData.Service;


import com.TruckBooking.TruckBooking.Model.LoadRequest;
import com.TruckBooking.TruckBooking.Response.UpdateLoadResponse;
import com.TruckBooking.hardwareData.Entities.Hardware;
import com.TruckBooking.hardwareData.Model.HardwareDataRequest;
import com.TruckBooking.hardwareData.Response.CreateHardwareDataResponse;
import com.TruckBooking.hardwareData.Response.UpdateHardwareDataResponse;

import java.util.List;

public interface HardwareDataService {

    public CreateHardwareDataResponse addHardwareData(HardwareDataRequest hardwareDataRequest);

    public List<Hardware> getHardwareData(String imei);

    public List<Hardware> getAllHardwareData();

    public UpdateHardwareDataResponse updateHardwareDataResponse(String imei, HardwareDataRequest hardwareDataRequest);
}
