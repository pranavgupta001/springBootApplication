package com.TruckBooking.ULIP.Controller;

import java.io.IOException;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.TruckBooking.ULIP.Entity.FastagEntity;
import com.TruckBooking.ULIP.Entity.SarathiEntity;
import com.TruckBooking.ULIP.Entity.VahanEntity;
import com.TruckBooking.ULIP.Service.ULIPService;

import io.swagger.annotations.Api;



@CrossOrigin
@RestController
@Api(tags ="ULIP Service", description = "Everything about ULIP APIs")
public class ULIPController {
	
	@Autowired
    public ULIPService service;
	

    @PostMapping("/Vahan")
    public ResponseEntity<Object> vahanApi(@Valid @RequestBody VahanEntity entity) 
    throws IOException{
        return new ResponseEntity<>(service.getvahanApi(entity), HttpStatus.OK);
    }

    @PostMapping("/Fastag")
    public ResponseEntity<Object> fastagApi(@Valid @RequestBody FastagEntity entity) 
    throws IOException{
        return new ResponseEntity<>(service.getfastagApi(entity), HttpStatus.OK);
    }
    
    @PostMapping("/Sarathi")
    public ResponseEntity<Object> sarathiApi(@Valid @RequestBody SarathiEntity entity) 
    throws IOException{
        return new ResponseEntity<>(service.getsarathiApi(entity), HttpStatus.OK);
    }


	  
	

}

