package com.TruckBooking.ContractRateUpload.Controller;

import java.util.List;
import java.util.Map;

import com.TruckBooking.ContractRateUpload.Dao.ContractRateRepo;
import com.TruckBooking.ContractRateUpload.Entity.Rates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.TruckBooking.ContractRateUpload.Service.ContractRateService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin
@RestController
public class Controller {

    @Autowired
    ContractRateService contractRateService;
    ContractRateRepo contractRateRepo;

	//upload the excel file.
    @PostMapping("/ContractRateUpload")
    public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file, @RequestParam("shipperId") String shipperId) {
        if (contractRateService.isExcelFile(file)) {
            boolean saved = contractRateService.save(file, shipperId);
            //true
            if (saved){
                return ResponseEntity.ok(Map.of("message", "File is uploaded and data is saved to database"));
            }
            else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Check the details again");
            }


        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please upload excel file ");
    }

    @GetMapping("/getSheet")
    public ResponseEntity<?> returnSheet(@RequestParam("shipperId") String shipperId){
        List<Rates> getRates = contractRateRepo.findByShipperId(shipperId);
        return new ResponseEntity<>(getRates, HttpStatus.OK);
    }

}