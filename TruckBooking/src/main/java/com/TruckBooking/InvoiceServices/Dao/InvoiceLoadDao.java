package com.TruckBooking.InvoiceServices.Dao;

import com.TruckBooking.InvoiceServices.Entity.InvoiceDetails;
import com.TruckBooking.InvoiceServices.Model.InvoiceModel;
import com.TruckBooking.InvoiceServices.Response.CreateInvoiceResponse;
import com.TruckBooking.TruckBooking.Entities.Load;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InvoiceLoadDao extends JpaRepository<InvoiceDetails,String> {

    Optional<InvoiceDetails>findByshipperId(String shipperId);
  //  Optional<InvoiceDetails>findByInvoiceId(String invoiceId);
    Optional<InvoiceDetails>findByInvoiceId(String invoiceId);

    List<InvoiceDetails> findBytransporterId(String transporterId);
}

