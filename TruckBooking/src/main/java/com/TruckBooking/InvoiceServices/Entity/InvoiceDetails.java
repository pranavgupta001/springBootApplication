package com.TruckBooking.InvoiceServices.Entity;

import com.TruckBooking.InvoiceServices.Response.CreateInvoiceResponse;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Data
@Entity
public class InvoiceDetails {

    @Id
    public String invoiceId;
    @NotBlank(message = "transporterId can not be null")
    public String transporterId;

    public String shipperId; //optional

    public String invoiceDate; //optional

    public String invoiceNo; //optional

    public String invoiceAmount; //optional

    public String partyName; //optional

    public String dueDate; //optional

    public String bookingId; //optional




}
