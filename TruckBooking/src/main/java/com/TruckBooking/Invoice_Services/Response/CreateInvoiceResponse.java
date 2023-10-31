package com.TruckBooking.Invoice_Services.Response;

import lombok.Data;

@Data
public class CreateInvoiceResponse {
    public String invoiceId;
    public String transporterId;
    public String shipperId;
    public String invoiceDate;
    public String invoiceNo;
    public String invoiceAmount;
    public String partyName;
    public String dueDate;
    public String bookingId;

}
