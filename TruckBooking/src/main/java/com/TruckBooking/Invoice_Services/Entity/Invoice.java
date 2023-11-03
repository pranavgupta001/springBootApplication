package com.TruckBooking.Invoice_Services.Entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Data
@Entity
public class Invoice {

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
