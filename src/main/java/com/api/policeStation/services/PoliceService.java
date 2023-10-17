package com.api.policeStation.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.api.policeStation.repositories.IUCriminalRepository;
import com.api.policeStation.repositories.IUPoliceRepository;
import com.api.policeStation.models.CriminalModel;
import com.api.policeStation.models.PoliceModel;
@Service
public class PoliceService {
	
	@Autowired
	IUCriminalRepository criminalRepository;
	
	@Autowired
	IUPoliceRepository policeRepository;
	
	public ResponseEntity<?> getPolices(){
		//return (ArrayList<PoliceModel>) policeRepository.findAll();
		//Map<String, List<PoliceModel>> response = Collections.singletonMap("polices", policeRepository.findAll());
		Map<String, Object> response = new HashMap<>();
        response.put("total", policeRepository.findAll().size());
        response.put("polices", policeRepository.findAll());
        return ResponseEntity.ok(response);
		
	}
	
	public ResponseEntity<?> getPolice(Integer id) {
	    Optional<PoliceModel> police = policeRepository.findById(id);
	    
	    if (police.isPresent()) {
	    	Map<String, PoliceModel> response = Collections.singletonMap("police", police.get());
	        return ResponseEntity.ok(response);
	    } else {
	    	String errorMessage = "Police not found with id: " + id;
	        Map<String, String> errorResponse = Collections.singletonMap("message", errorMessage);
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
	    }
	}
	
	public ResponseEntity<?> savePolice( PoliceModel police) {
		try {
			Optional<PoliceModel> supervisor = policeRepository.findById(police.getMunSupervisor());
			police.setSupervisor(supervisor.get());
			policeRepository.save(police);
			Map<String, PoliceModel> response = Collections.singletonMap("Police",police );
	        return ResponseEntity.ok(response);	
		} catch (Exception e) {
			String errorMessage = "Error when adding new police.";
	        Map<String, String> errorResponse = Collections.singletonMap("message", errorMessage);
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
		}
	}
	
	public ResponseEntity<?> updatePolice(PoliceModel request, Integer id){
		try {
			PoliceModel police = policeRepository.findById(id).get();
			police.setEmail(request.getEmail());
			police.setPhone(request.getPhone());
			police.setProvince(request.getProvince());
			police.setLocation(request.getLocation());
			policeRepository.save(police);
			Map<String, PoliceModel> response = Collections.singletonMap("Police",police );
	        return ResponseEntity.ok(response);	
		} catch (Exception e) {
			String errorMessage = "Police not found with id: " + id;
	        Map<String, String> errorResponse = Collections.singletonMap("message", errorMessage);
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
		}					
	}
	
	public ResponseEntity<?> deletePolice (Integer id) {
		
		try {
			policeRepository.deleteById(id);
			Map<String, String> response = Collections.singletonMap("message","Police removed" );
	        return ResponseEntity.ok(response);
		} catch (Exception e) {
			String errorMessage = "Police not found with id: " + id;
	        Map<String, String> errorResponse = Collections.singletonMap("message", errorMessage);
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
		}
	}
	
	
	public ResponseEntity<?> listRangoPolice (String range){
		
		try {			
			List<PoliceModel> polices = policeRepository.listRangePolice(range);
			Map<String, Object> response = new HashMap<>();
	        response.put("total", polices.size());
	        response.put("Range", range);
	        response.put("polices", polices);
	        return ResponseEntity.ok(response);
		} catch (Exception e) {
			String errorMessage = "Range not found.";
	        Map<String, String> errorResponse = Collections.singletonMap("message", errorMessage);
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
		}
		
	}
	
	
	public ResponseEntity<?> getSupervisorPolice(Integer id){
		PoliceModel police = policeRepository.findById(id).get();
		Map<String, Object> response = new HashMap<>();
		List<PoliceModel> data = policeRepository.listSupervisorPolice(police);
        response.put("total", data.size());
        response.put("supervisor", police);
        response.put("polices", data);
        return ResponseEntity.ok(response);
		
	}
	
	public ResponseEntity<?> getArrestedCriminalForPolice(Integer id){
		try {
			PoliceModel police = policeRepository.findById(id).get();
			List<CriminalModel> datos = criminalRepository.getArrestedCriminalForPolice(police);
			Map<String, Object> response = new HashMap<>();
	        response.put("total", datos.size());
	        response.put("criminals", datos);
	        return ResponseEntity.ok(response);
		} catch (Exception e) {
			String errorMessage = "Police not found with id: " + id;
	        Map<String, String> errorResponse = Collections.singletonMap("message", errorMessage);
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
		}
	}
	
	public ResponseEntity<?> addSupervisorPolice(Integer idPolice, Integer idSupervisor){		
		try {
			PoliceModel police = policeRepository.findById(idPolice).get();
			PoliceModel supervisor = policeRepository.findById(idSupervisor).get();
			police.setSupervisor(supervisor);
			policeRepository.save(police);
			Map<String, Object> response = new HashMap<>();
	        response.put("message", "Police officer "+police.getName()+ " "+ police.getSurname()+" with ID "+police.getId()+" has supervisor "+supervisor.getName()+" "+ supervisor.getSurname() +" with ID "+supervisor.getId());
	        response.put("police", police);
	        return ResponseEntity.ok(response);
		} catch (Exception e) {
			String errorMessage = "Polices not found with id: " + idPolice + " or " + idSupervisor;
	        Map<String, String> errorResponse = Collections.singletonMap("message", errorMessage);
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
		}
		
	}
	
	public ResponseEntity<?> rankUp(Integer idPolice, String range){		
		try {
			
			List<String> validRanges = Arrays.asList(
					"police officer",
				    "sergeant",
				    "lieutenant",
				    "captain",
				    "inspector",
				    "detective",
				    "chief inspector",
				    "superintendent",
				    "chief superintendent",
				    "assistant commissioner",
				    "deputy commissioner",
				    "commissioner");
			if (validRanges.contains(range.toLowerCase())) {
				PoliceModel police = policeRepository.findById(idPolice).get();			
				police.setPrivateOffice(range.toUpperCase());
				policeRepository.save(police);
				Map<String, Object> response = new HashMap<>();
		        response.put("message", "Police officer "+police.getName()+ " "+ police.getSurname()+" is promoted to " + range);
		        response.put("police", police);
		        return ResponseEntity.ok(response);
				
			}else {
				String errorMessage = "Range not available: " + idPolice;
		        Map<String, String> errorResponse = Collections.singletonMap("message", errorMessage);
		        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
				
			}					
		} catch (Exception e) {
			String errorMessage = "Polices not found with id: " + idPolice;
	        Map<String, String> errorResponse = Collections.singletonMap("message", errorMessage);
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
		}
	}
	
	public ResponseEntity<?> filterPrivateOffice(String name){
		try {
			List<PoliceModel> datos = policeRepository.filterPrivateOffice(name.toUpperCase());
			Map<String, Object> response = new HashMap<>();
	        response.put("total", datos.size());
	        response.put("PrivateOffices", datos);
	        return ResponseEntity.ok(response);
		} catch (Exception e) {
			String errorMessage = "no search results: " + name;
	        Map<String, String> errorResponse = Collections.singletonMap("message", errorMessage);
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
		}
	}
}
