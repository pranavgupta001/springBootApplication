package com.TruckBooking.Invoice_Services.Response;

import com.TruckBooking.Invoice_Services.Entity.Invoice;
import com.TruckBooking.TruckBooking.Entities.Load;
import lombok.Data;

import java.util.List;

@Data
public class CreateInvoiceResponse {
    public String invoiceId;
    public String transporterId;
    public String transporterName;

    public  String invoiceStatus;

    public String shipperId;
    public String invoiceDate;
    public String invoiceNo;
    public String invoiceAmount;
    public String partyName;
    public String dueDate;


}
