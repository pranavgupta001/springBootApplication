package com.TruckBooking.Invoice_Services.Model;

import com.TruckBooking.Invoice_Services.Entity.Invoice;
import com.TruckBooking.TruckBooking.Entities.Load;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class InvoiceRequest {

    @NotBlank(message = "transporterId can not be null")
    public String transporterId;
    public String invoiceStatus;

    public String transporterName;
    public String shipperId;

    public String invoiceDate;

    public String invoiceNo;

    public String invoiceAmount;

    public String partyName;

    public String dueDate;



}
