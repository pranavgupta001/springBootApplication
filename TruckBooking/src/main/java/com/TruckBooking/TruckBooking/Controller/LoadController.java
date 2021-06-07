package com.TruckBooking.TruckBooking.Controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.TruckBooking.TruckBooking.Entities.Load;
import com.TruckBooking.TruckBooking.Model.LoadRequest;
import com.TruckBooking.TruckBooking.Response.CreateLoadResponse;
import com.TruckBooking.TruckBooking.Response.DeleteLoadResponse;
import com.TruckBooking.TruckBooking.Response.UpdateLoadResponse;
import com.TruckBooking.TruckBooking.Service.LoadServiceImpl;

@RestController
public class LoadController {

	@Autowired
	public LoadServiceImpl loadService;
	
	@GetMapping("/home")
	public String getmessage() {
		return "Welcome to loadApi...!!!";
	}
	
	@PostMapping("/load")
	public CreateLoadResponse load(@RequestBody LoadRequest loadrequest) {
		return loadService.addLoad(loadrequest);
	}
	
	@GetMapping("/load")
	public List<Load> findLoads(@RequestParam(name="loadingPointCity",required=false) String loadingPointCity,
			@RequestParam(name="unloadingPointCity",required=false) String unloadingPointCity,
			@RequestParam(name="Id",required=false) String Id,
			@RequestParam(name="truckType",required=false) String truckType,
			@RequestParam(name="date",required=false) String date){
		
		return loadService.getLoads(loadingPointCity, unloadingPointCity, Id, truckType, date);
	}
	
	@GetMapping("/load/{loadId}")
	public Load findLoad(@PathVariable String loadId){ 
		return loadService.getLoad(loadId);
	}
	
	@PutMapping("/load/{loadId}")
	public UpdateLoadResponse updateLoad(@PathVariable String loadId,@RequestBody LoadRequest loadrequest) {
		return loadService.updateLoad(loadId, loadrequest);
		
	}
	
	@DeleteMapping("/load/{loadId}")
	public DeleteLoadResponse deleteLoad(@PathVariable String loadId) {
		return loadService.deleteLoad(loadId);
	}
	
}
