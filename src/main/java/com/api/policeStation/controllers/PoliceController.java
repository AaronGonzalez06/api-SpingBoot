package com.api.policeStation.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.policeStation.models.PoliceModel;
import com.api.policeStation.services.PoliceService;

@RestController
@RequestMapping("/api")
public class PoliceController {

	@Autowired
	private PoliceService policeService;
	
	// peticiones get
	
	@GetMapping("/polices")
	public ResponseEntity<?> getPolices(){
		return this.policeService.getPolices();
	}
	
	
	@GetMapping(path="/police/{id}")
	public ResponseEntity<?> getPolices(@PathVariable Integer id){
		return this.policeService.getPolice(id);
	}
	
	@GetMapping(path="/listSupervisorPolice/{id}")
	public ResponseEntity<?> geSupervisorPolice(@PathVariable Integer id){
		return this.policeService.getSupervisorPolice(id);
	}	
	
	@GetMapping(path="/policeRange/{name}")
	public ResponseEntity<?> getRangePolices(@PathVariable String name){
		return this.policeService.listRangoPolice(name);
	}
	
	@GetMapping(path="/ArrestedCriminalForPolice/{id}")
	public ResponseEntity<?> getArrestedCriminalForPolice(@PathVariable Integer id){
		return this.policeService.getArrestedCriminalForPolice(id);
	}
	
	@GetMapping(path="/filterPrivateOffice/{name}")
	public ResponseEntity<?> filterPrivateOffice(@PathVariable String name){
		return this.policeService.filterPrivateOffice(name);
	}
	
	//peticiones POST
	
	@PostMapping("/addPolice")
	public ResponseEntity<?> savePolice(@RequestBody PoliceModel police) {
		return this.policeService.savePolice(police);
	}		
	
	// peticiones PUT
	
	@PutMapping(path="/police/{id}")
	public ResponseEntity<?> updatePolice(@RequestBody PoliceModel police,@PathVariable Integer id){
		return this.policeService.updatePolice(police, id);
	}
	
	@PutMapping(path="/police/{idPolice}/{idSupervisor}")
	public ResponseEntity<?> addSupervisorPolice(@PathVariable Integer idPolice,@PathVariable Integer idSupervisor){
		return this.policeService.addSupervisorPolice(idPolice, idSupervisor);
	}
	
	@PutMapping(path="/rankUp/{idpolice}/{range}")
	public ResponseEntity<?> rankUp(@PathVariable Integer idpolice,@PathVariable String range){
		return this.policeService.rankUp(idpolice,range);
	}
	
	// peticiones Delete
	
	@DeleteMapping(path="/police/{id}")
	public ResponseEntity<?> deletePolice(@PathVariable Integer id){
		return this.policeService.deletePolice(id);
	}
	
	
}
