package com.TruckBooking.InvoiceServices.Services;

import com.TruckBooking.InvoiceServices.Entity.InvoiceDetails;
import com.TruckBooking.InvoiceServices.Model.InvoiceModel;
import com.TruckBooking.InvoiceServices.Response.CreateInvoiceResponse;
import com.TruckBooking.InvoiceServices.Response.updateInvoiceResponse;

import java.util.List;

public interface InvoiceLoadService {
    public CreateInvoiceResponse addInvoice(InvoiceModel invoiceModel);
    public List<InvoiceDetails>getInvoices(String transporterId);
    public updateInvoiceResponse updateInvoice(String invoiceId,InvoiceModel invoiceModel);
    public CreateInvoiceResponse getInvoicesByInvoiceId(String invoiceId);
  public   CreateInvoiceResponse getInvoicebyshipperId(String shipperId);

  }
