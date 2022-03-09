package com.TruckBooking.hardwareData.Model;

import lombok.Data;
import javax.validation.constraints.NotBlank;


@Data
public class HardwareDataRequest {

    @NotBlank(message = "imei can't be left blank.")
    private String imei;

    @NotBlank(message = "simNumber can't be left blank.")
    private String simNumber;

    @NotBlank(message = "phoneNo can't be left blank.")
    private String phoneNo;

}
