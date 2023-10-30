package com.TruckBooking.InvoiceServices.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
