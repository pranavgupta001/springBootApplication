package com.TruckBooking.Invoice_Services.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
@AllArgsConstructor
@NoArgsConstructor
@Data

public class InvoiceRequest {

    @NotBlank(message = "transporterId can not be null")
    public String transporterId;


    public String shipperId;

    public String invoiceDate;

    public String invoiceNo;

    public String invoiceAmount;

    public String partyName;

    public String dueDate;

    public String bookingId;

}
