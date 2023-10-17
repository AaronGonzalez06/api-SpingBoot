package com.api.policeStation.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.policeStation.models.CriminalModel;
import com.api.policeStation.models.PoliceModel;
import com.api.policeStation.services.CriminalService;

@RestController
@RequestMapping("/api")
public class CriminalController {
	
	@Autowired
	CriminalService criminalService;
	
	//peticiones POST
	@PostMapping("/criminal")
	public ResponseEntity<?> saveCriminal(@RequestBody CriminalModel criminal) {
		return this.criminalService.saveCriminal(criminal);
	}
	
	//peticiones GET
	@GetMapping("/criminals")
	public ResponseEntity<?> getPolices(){
		return this.criminalService.getCriminals();
	}
		
	@GetMapping(path="/criminal/{id}")
	public ResponseEntity<?> getPolices(@PathVariable Integer id){
		return this.criminalService.getCriminal(id);
	}
	
	@GetMapping(path="/filterArrests/{min}/{max}")
	public ResponseEntity<?> filterArrests(@PathVariable Integer min,@PathVariable Integer max){
		return this.criminalService.filterArrests(min, max);
	}
	
	@DeleteMapping(path="/criminal/{id}")
	public ResponseEntity<?> deleteCriminal(@PathVariable Integer id){
		return this.criminalService.deleteCriminal(id);
	}
	
	// peticiones PUT
	
	@PutMapping(path="/addArrestsCriminal/{id}")
	public ResponseEntity<?> addArrestsCriminal(@PathVariable Integer id){
		return this.criminalService.addArrestsCriminal(id);
	}
	
	@PutMapping(path="/criminal/{id}")
	public ResponseEntity<?> updateCriminal(@RequestBody CriminalModel criminal,@PathVariable Integer id){
		return this.criminalService.updateCriminal(criminal, id);
	}

}
