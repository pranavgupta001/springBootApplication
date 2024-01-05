package com.TruckBooking.Analytics.placementEfficiencyGraph.Controller;

import com.TruckBooking.Analytics.placementEfficiencyGraph.Entities.Placements;
import com.TruckBooking.Analytics.placementEfficiencyGraph.Service.PlacementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PlacementController {

    @Autowired
    private PlacementService placementService;

    @GetMapping("/placementGraph/{id}") // the id will either be ShipperId or TransporterId
    public ResponseEntity<Object> graphData(@PathVariable String id){
        Object response = placementService.getPlacementData(id);
        if (response.getClass() == "".getClass()){  // looking if object returned is string or entity
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
