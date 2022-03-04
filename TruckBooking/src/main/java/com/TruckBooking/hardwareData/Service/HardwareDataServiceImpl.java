package com.TruckBooking.hardwareData.Service;

import com.TruckBooking.TruckBooking.Entities.Load;
import com.TruckBooking.TruckBooking.Exception.BusinessException;
import com.TruckBooking.TruckBooking.Exception.EntityNotFoundException;
import com.TruckBooking.hardwareData.Dao.HardwareDataDoa;
import com.TruckBooking.hardwareData.Entities.Hardware;
import com.TruckBooking.hardwareData.Model.HardwareDataRequest;
import com.TruckBooking.hardwareData.Response.CreateHardwareDataResponse;
import com.TruckBooking.hardwareData.Response.UpdateHardwareDataResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class HardwareDataServiceImpl implements HardwareDataService{

    @Autowired
    HardwareDataDoa hardwareDataDoa;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public CreateHardwareDataResponse addHardwareData(HardwareDataRequest hardwareDataRequest) {

        List<Hardware> hardwareList = hardwareDataDoa.findByImei(hardwareDataRequest.getImei().trim());
        if (!hardwareList.isEmpty()) {
            throw new BusinessException(": This imei already exists.");
        }

        else {
            if(hardwareDataRequest.getPhoneNo().trim().length() != 13){
                throw new BusinessException(": The Phone No. must be of 13 digits.");
            }

            else {
                String temp = "";
                Hardware hardware = new Hardware();
                CreateHardwareDataResponse response = new CreateHardwareDataResponse();

                temp = "hardwareDataID:" + UUID.randomUUID();
                hardware.setHardwareDataId(temp);
                response.setHardwareDataId(temp);

                temp = hardwareDataRequest.getImei().trim();
                hardware.setImei(temp);
                response.setImei(temp);

                temp = hardwareDataRequest.getSimNumber().trim();
                hardware.setSimNumber(temp);
                response.setSimNumber(temp);

                temp = hardwareDataRequest.getPhoneNo().trim();
                hardware.setPhoneNo(temp);
                response.setPhoneNo(temp);

                hardwareDataDoa.save(hardware);
                log.info("hardwareData is saved to the database");
                log.info("addHardwareData service response is returned");

                return response;
            }
        }
    }


    @Transactional(readOnly = true, rollbackFor = Exception.class)
    @Override
    public List<Hardware> getHardwareData(String imei) {
        log.info("getHardwareData service by imei is started");

        List<Hardware> hardware = hardwareDataDoa.findByImei(imei);
        if (hardware.isEmpty())
            throw new EntityNotFoundException(Hardware.class, "imei", imei);

        log.info("getHardwareData service response is returned");
        return hardware;
    }

    @Override
    public List<Hardware> getAllHardwareData() {
        return hardwareDataDoa.findAll();
    }

    @Override
    public UpdateHardwareDataResponse updateHardwareDataResponse(String imei, HardwareDataRequest updateHardwareDataRequest) {
        log.info("updateHardwareData service is started");

        List<Hardware> hardwareList = hardwareDataDoa.findByImei(imei);
        if (hardwareList.isEmpty())
            throw new EntityNotFoundException(Hardware.class, "imei", imei);

        if(updateHardwareDataRequest.getPhoneNo().trim().length() != 13)
            throw new BusinessException(": The Phone No. must be of 13 digits.");

        String temp = "";
        Hardware hardware = hardwareList.get(0);
        UpdateHardwareDataResponse response = new UpdateHardwareDataResponse();

        temp = hardware.getHardwareDataId();
        response.setHardwareDataId(temp);

        temp = hardware.getImei();
        response.setImei(temp);

        temp = updateHardwareDataRequest.getSimNumber();
        if(StringUtils.isNotBlank(temp)) {
            hardware.setSimNumber(temp);
            response.setSimNumber(temp);
        }

        temp = updateHardwareDataRequest.getPhoneNo();
        if(StringUtils.isNotBlank(temp)) {
            hardware.setPhoneNo(temp);
            response.setPhoneNo(temp);
        }

        hardwareDataDoa.save(hardware);
        log.info("Hardware Data is updated in the database");
        log.info("updateHardwareData service response is returned");
        return response;
    }
}
