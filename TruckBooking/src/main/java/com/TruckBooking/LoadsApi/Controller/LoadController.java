package com.TruckBooking.LoadsApi.Controller;

import java.sql.Timestamp;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.TruckBooking.LoadsApi.Entities.Load;
import com.TruckBooking.LoadsApi.Model.LoadRequest;
import com.TruckBooking.LoadsApi.Service.LoadServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin
@RestController
@Api(tags ="Loads Service", description = "Everything about Loads")
public class LoadController {

	@Autowired
	public LoadServiceImpl loadService;

	@ApiOperation(value = "Home Message")
	@GetMapping("/home")
	public String getmessage() {
		return "Welcome to loadApi git actions second check...!!!";
	}

	@ApiOperation(value = "New load details can be added")
	@PostMapping("/load")
	public ResponseEntity<Object> load(@Valid @RequestBody LoadRequest loadrequest) {
		log.info("Post Controller Started");
		return new ResponseEntity<>(loadService.addLoad(loadrequest), HttpStatus.CREATED);
	}

	@ApiOperation(value = "Access to all the load details")
	@GetMapping("/load")
	public ResponseEntity<List<Load>> findLoads(@RequestParam(value = "pageNo", required = false) Integer pageNo,
			@RequestParam(name = "loadingPointCity", required = false) String loadingPointCity,
			@RequestParam(name = "unloadingPointCity", required = false) String unloadingPointCity,
			@RequestParam(name = "postLoadId", required = false) String postLoadId,
			@RequestParam(name = "truckType", required = false) String truckType,
			@RequestParam(name = "suggestedLoads", required = false) boolean suggestedLoads,
			@RequestParam(name="transporterId", required=false) String transporterId,
			 @RequestParam (name="startTimestamp", required=false)Timestamp startTimestamp,
				@RequestParam(name="unloadingPointGeoId",required = false) List<String> unloadingPointGeoId,
				@RequestParam(name="loadingPointGeoId",required = false)List<String>loadingPointGeoId,
		        @RequestParam (name="endTimestamp", required=false) Timestamp endTimestamp) {

		log.info("Get with Params Controller Started");

		return new ResponseEntity<>(loadService.getLoads(pageNo, loadingPointCity, unloadingPointCity, postLoadId,
				truckType,suggestedLoads, transporterId, startTimestamp, endTimestamp), HttpStatus.OK);
	}
	
	

	@ApiOperation(value = "Access to details of a prticular load")
	@GetMapping("/load/{loadId}")
	public ResponseEntity<Object> findLoad(@PathVariable String loadId) {
		log.info("Get by loadId Controller Started");
		return new ResponseEntity<>(loadService.getLoad(loadId), HttpStatus.OK);
	}

	@ApiOperation(value = "Load details can be updated by loadId")
	@PutMapping("/load/{loadId}")
	public ResponseEntity<Object> updateLoad(@PathVariable String loadId, @RequestBody LoadRequest loadrequest) {

		log.info("Put Controller Started");
		return new ResponseEntity<>(loadService.updateLoad(loadId, loadrequest), HttpStatus.OK);
	}

	@ApiOperation(value = "Load can be deleted by loadId")
	@DeleteMapping("/load/{loadId}")
	public ResponseEntity<Object> deleteLoad(@PathVariable String loadId) {
		log.info("Delete Controller Started");
		loadService.deleteLoad(loadId);
		return new ResponseEntity<>("Successfully Deleted", HttpStatus.OK);
	}

}
