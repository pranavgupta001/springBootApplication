package com.TruckBooking.Invoice_Services.Controller;

import com.TruckBooking.Invoice_Services.Entity.Invoice;
import com.TruckBooking.Invoice_Services.Model.InvoiceRequest;
import com.TruckBooking.Invoice_Services.Services.InvoiceService;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/invoices")
@Slf4j
public class InvoiceController {
    private InvoiceService invoiceService;
    @Autowired
    public InvoiceController(InvoiceService invoiceService){
        this.invoiceService =invoiceService;
    }
    @PostMapping
    public ResponseEntity<Object>postInvoiceService( @Valid @RequestBody InvoiceRequest invoice) {
        log.info("Invoice created sucessfully");

        return new ResponseEntity<>(invoiceService.addInvoice(invoice), HttpStatus.CREATED);


    }
    @GetMapping("/{shipperId}")
    public ResponseEntity<Object>getInvoiceService(@PathVariable String shipperId){
        log.info("getInvoicesService started sucessfully");
        return new ResponseEntity<>(invoiceService.getInvoicebyshipperId(shipperId), HttpStatus.OK);
    }
    @GetMapping("/invoice/{invoiceId}")
    public ResponseEntity<Object>getInvoiceById(@PathVariable String invoiceId){
        log.info("getInvoiceServiceBYinvoiceID started sucessfully");
        return new ResponseEntity<>(invoiceService.getInvoiceById(invoiceId),HttpStatus.OK);
    }

    @GetMapping

    public ResponseEntity<List<Invoice>> findinvoiceService(@RequestParam String transporterId) {
        log.info("Get with Params Controller Started");

        return new ResponseEntity<>(invoiceService.getInvoices(transporterId), HttpStatus.OK);
    }
    @PutMapping("/{invoiceId}")
    public ResponseEntity<Object>updateInvoices(@PathVariable String invoiceId , @RequestBody InvoiceRequest invoiceModel){
        log.info("updateInvoices started");
        return new ResponseEntity<>(invoiceService.updateInvoice(invoiceId,invoiceModel),HttpStatus.OK);
    }




}
