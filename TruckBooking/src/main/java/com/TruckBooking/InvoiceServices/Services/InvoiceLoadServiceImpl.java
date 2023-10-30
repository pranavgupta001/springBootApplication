package com.TruckBooking.InvoiceServices.Services;

import com.TruckBooking.InvoiceServices.Dao.InvoiceLoadDao;
import com.TruckBooking.InvoiceServices.Entity.InvoiceDetails;
import com.TruckBooking.InvoiceServices.Model.InvoiceModel;
import com.TruckBooking.InvoiceServices.Response.CreateInvoiceResponse;
import com.TruckBooking.InvoiceServices.Response.updateInvoiceResponse;
import com.TruckBooking.TruckBooking.Entities.Load;
import com.TruckBooking.TruckBooking.Exception.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
@Slf4j
public class InvoiceLoadServiceImpl implements InvoiceLoadService {

    InvoiceLoadDao invoiceLoadDao;

    @Autowired
    public InvoiceLoadServiceImpl(InvoiceLoadDao invoiceLoadDao) {
        this.invoiceLoadDao = invoiceLoadDao;
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public CreateInvoiceResponse addInvoice(InvoiceModel invoiceModel) {
        log.info("addInvoice service is started");
        String temp=" ";
        InvoiceDetails ObjInvoice = new InvoiceDetails();
        CreateInvoiceResponse objCreateInvoiceResponse = new CreateInvoiceResponse();
        temp="InvoiceId"+ UUID.randomUUID();
        ObjInvoice.setInvoiceId(temp);
        objCreateInvoiceResponse.setInvoiceId(temp);
        temp = " ";
        temp = invoiceModel.getTransporterId();
            ObjInvoice.setTransporterId(temp);
            objCreateInvoiceResponse.setTransporterId(temp);

        temp = invoiceModel.getDueDate();
        if (StringUtils.isNotBlank(temp)) {
            ObjInvoice.setDueDate(temp);
            objCreateInvoiceResponse.setDueDate(temp);
        }



        temp = invoiceModel.getShipperId();
        if (StringUtils.isNotBlank(temp)) {
            ObjInvoice.setShipperId(temp);
            objCreateInvoiceResponse.setShipperId(temp);
        }

        temp = invoiceModel.getInvoiceNo();
        if (StringUtils.isNotBlank(temp)) {
            ObjInvoice.setInvoiceNo(temp);
            objCreateInvoiceResponse.setInvoiceNo(temp);
        }

        temp = invoiceModel.getInvoiceDate();
        if (StringUtils.isNotBlank(temp)) {
            ObjInvoice.setInvoiceDate(temp);
            objCreateInvoiceResponse.setInvoiceDate(temp);
        }

        temp = invoiceModel.getInvoiceAmount();
        if (StringUtils.isNotBlank(temp)) {
            ObjInvoice.setInvoiceAmount(temp);
            objCreateInvoiceResponse.setInvoiceAmount(temp);
        }

        temp = invoiceModel.getPartyName();
        if (StringUtils.isNotBlank(temp)) {
            ObjInvoice.setPartyName(temp);
            objCreateInvoiceResponse.setPartyName(temp);
        }


        temp = invoiceModel.getDueDate();
        if (StringUtils.isNotBlank(temp)) {
            ObjInvoice.setDueDate(temp);
            objCreateInvoiceResponse.setDueDate(temp);
        }


        temp = invoiceModel.getBookingId();
        if (StringUtils.isNotBlank(temp)) {
            ObjInvoice.setBookingId(temp);
            objCreateInvoiceResponse.setBookingId(temp);
        }


        invoiceLoadDao.save(ObjInvoice);
        return objCreateInvoiceResponse;


    }
    @Transactional(readOnly = true,rollbackFor = Exception.class)
    @Override
    public CreateInvoiceResponse getInvoicesByInvoiceId(String invoiceId){
        log.info("getInvoicesByInvoiceId Started");
        Optional<InvoiceDetails> ansByinvoiceId=invoiceLoadDao.findByInvoiceId(invoiceId);
        CreateInvoiceResponse objOfCreateInReBygetInvByinvId=new CreateInvoiceResponse();
        InvoiceDetails ansForgetInvoicesByInvoiceId=ansByinvoiceId.get();
        objOfCreateInReBygetInvByinvId.setInvoiceId(ansForgetInvoicesByInvoiceId.getInvoiceId());
        objOfCreateInReBygetInvByinvId.setInvoiceNo(ansForgetInvoicesByInvoiceId.getInvoiceNo());
        objOfCreateInReBygetInvByinvId.setInvoiceDate(ansForgetInvoicesByInvoiceId.getInvoiceDate());
        objOfCreateInReBygetInvByinvId.setInvoiceAmount(ansForgetInvoicesByInvoiceId.getInvoiceAmount());
        objOfCreateInReBygetInvByinvId.setTransporterId(ansForgetInvoicesByInvoiceId.getTransporterId());
        objOfCreateInReBygetInvByinvId.setShipperId(ansForgetInvoicesByInvoiceId.getShipperId());
        objOfCreateInReBygetInvByinvId.setPartyName(ansForgetInvoicesByInvoiceId.getPartyName());
        objOfCreateInReBygetInvByinvId.setBookingId(ansForgetInvoicesByInvoiceId.getBookingId());
        objOfCreateInReBygetInvByinvId.setDueDate(ansForgetInvoicesByInvoiceId.getDueDate());
        return objOfCreateInReBygetInvByinvId;


    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    @Override
    public List<InvoiceDetails> getInvoices(String transporterId) {

        log.info("getInvoices services started");
         List<InvoiceDetails>listans =invoiceLoadDao.findBytransporterId(transporterId);
        if (listans.isEmpty()) {
            throw new EntityNotFoundException(InvoiceDetails.class, "id", transporterId.toString());
        }
         return listans;
    }

    public updateInvoiceResponse updateInvoice(String invoiceId,InvoiceModel invoiceModel) {
        log.info("CreateInvoiceResponse started");
        String temp = " ";
        // temp=invoiceModel.getInvoiceNo().trim();
        Optional<InvoiceDetails> ansShipperId = invoiceLoadDao.findByInvoiceId(invoiceId);

        InvoiceDetails invDe = ansShipperId.get();

        temp = invoiceModel.getInvoiceNo().trim();
        if (StringUtils.isNotBlank(temp)) {
            invDe.setInvoiceNo(temp);
        }


        temp = invoiceModel.getInvoiceDate().trim();
        if (StringUtils.isNotBlank(temp)) {
            invDe.setInvoiceDate(temp);
        }
        temp = invoiceModel.getInvoiceAmount();
        if (StringUtils.isNotBlank(temp)) {
            invDe.setInvoiceAmount(temp);
        }
        temp = invoiceModel.getPartyName().trim();
        if (StringUtils.isNotBlank(temp)) {
            invDe.setPartyName(temp);

        }

        temp = invoiceModel.getDueDate().trim();
        if (StringUtils.isNotBlank(temp)) {
            invDe.setDueDate(temp);
        }




        updateInvoiceResponse response1 = new updateInvoiceResponse();
        response1.setInvoiceNo(invDe.getInvoiceNo());
        response1.setDueDate(invDe.getDueDate());
        response1.setInvoiceId(invoiceId);
        response1.setInvoiceAmount(invDe.getInvoiceAmount());
        response1.setInvoiceDate(invDe.getInvoiceDate());
        response1.setPartyName(invDe.getPartyName());





        invoiceLoadDao.save(invDe);
        return response1;

    }


    public CreateInvoiceResponse getInvoicebyshipperId(String shipperId) {
        log.info("CreateInvoiceResponse started");
        Optional<InvoiceDetails> ans1 = invoiceLoadDao.findByshipperId(shipperId);
        CreateInvoiceResponse objOfCreateInvoiceResponse=new CreateInvoiceResponse();
        InvoiceDetails  ans2 = ans1.get();
        objOfCreateInvoiceResponse.setInvoiceId(ans2.getInvoiceId());

        objOfCreateInvoiceResponse.setInvoiceNo(ans2.getInvoiceNo());
        objOfCreateInvoiceResponse.setInvoiceAmount(ans2.getInvoiceAmount());
        objOfCreateInvoiceResponse.setBookingId(ans2.getBookingId());
        objOfCreateInvoiceResponse.setPartyName(ans2.getPartyName());
        objOfCreateInvoiceResponse.setDueDate(ans2.getDueDate());
        objOfCreateInvoiceResponse.setInvoiceDate(ans2.getInvoiceDate());
        objOfCreateInvoiceResponse.setTransporterId(ans2.getTransporterId());
        objOfCreateInvoiceResponse.setShipperId(ans2.getShipperId());
        return objOfCreateInvoiceResponse;




    }
}









