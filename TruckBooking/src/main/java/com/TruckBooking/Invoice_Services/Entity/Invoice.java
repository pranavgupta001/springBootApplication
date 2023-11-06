package com.TruckBooking.Invoice_Services.Entity;

import com.TruckBooking.TruckBooking.Entities.Load;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@Entity

public class Invoice {

    @Id
    public String invoiceId;
    @NotBlank(message = "transporterId can not be null")
    public String transporterId;
    public String transporterName;


    public String shipperId; //optional

    public String invoiceDate; //optional

    public String invoiceNo; //optional

    public String invoiceAmount; //optional

    public String partyName; //optional

    public String dueDate; //optional



    public String invoiceStatus;




}
