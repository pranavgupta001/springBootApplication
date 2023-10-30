package com.TruckBooking.InvoiceServices.Controller;

import com.TruckBooking.InvoiceServices.Entity.InvoiceDetails;
import com.TruckBooking.InvoiceServices.Model.InvoiceModel;
import com.TruckBooking.InvoiceServices.Services.InvoiceLoadService;


import com.TruckBooking.TruckBooking.Entities.Load;
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
public class InvoicesController {
    private InvoiceLoadService invoiceLoadService;
    @Autowired
    public InvoicesController(InvoiceLoadService invoiceLoadService){
        this.invoiceLoadService=invoiceLoadService;
    }
    @PostMapping
    public ResponseEntity<Object>postInvoiceService( @Valid @RequestBody InvoiceModel invoice) {
        log.info("Invoice created sucessfully");
        return new ResponseEntity<>(invoiceLoadService.addInvoice(invoice), HttpStatus.CREATED);


    }
    @GetMapping("/{shipperId}")
    public ResponseEntity<Object>getInvoiceService(@PathVariable String shipperId){
        log.info("getInvoicesService started sucessfully");
        return new ResponseEntity<>(invoiceLoadService.getInvoicebyshipperId(shipperId), HttpStatus.OK);
    }
    @GetMapping("/invoice/{invoiceId}")
    public ResponseEntity<Object>getInvoiceServiceBYinvoiceID(@PathVariable String invoiceId){
        log.info("getInvoiceServiceBYinvoiceID started sucessfully");
        return new ResponseEntity<>(invoiceLoadService.getInvoicesByInvoiceId(invoiceId),HttpStatus.OK);
    }

    @GetMapping

    public ResponseEntity<List<InvoiceDetails>> findinvoiceService(@RequestParam String transporterId) {
        log.info("Get with Params Controller Started");

        return new ResponseEntity<>(invoiceLoadService.getInvoices(transporterId), HttpStatus.OK);
    }
    @PutMapping("/{invoiceId}")
    public ResponseEntity<Object>updateInvoices(@PathVariable String invoiceId , @RequestBody InvoiceModel invoiceModel){
        log.info("updateInvoices started");
        return new ResponseEntity<>(invoiceLoadService.updateInvoice(invoiceId,invoiceModel),HttpStatus.OK);
    }




}
