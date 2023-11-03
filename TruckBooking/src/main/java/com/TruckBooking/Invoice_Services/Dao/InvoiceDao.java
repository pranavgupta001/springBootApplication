package com.TruckBooking.Invoice_Services.Dao;

import com.TruckBooking.Invoice_Services.Entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InvoiceDao extends JpaRepository<Invoice,String> {

    Optional<Invoice>findByshipperId(String shipperId);
  //  Optional<InvoiceDetails>findByInvoiceId(String invoiceId);
    Optional<Invoice>findByInvoiceId(String invoiceId);

    List<Invoice> findBytransporterId(String transporterId);
}

