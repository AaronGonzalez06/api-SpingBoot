package com.api.policeStation.services;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.api.policeStation.models.CriminalModel;
import com.api.policeStation.models.PoliceModel;
import com.api.policeStation.repositories.IUCriminalRepository;
import com.api.policeStation.repositories.IUPoliceRepository;

@Service
public class CriminalService {
	
	@Autowired
	IUCriminalRepository criminalRepository;
	
	@Autowired
	IUPoliceRepository policeRepository;
	
	public ResponseEntity<?> saveCriminal( CriminalModel criminal) {
		try {
			//policia que lo detiene
			Optional<PoliceModel> police = policeRepository.findById(criminal.getIdentifier());
			criminal.setPolice(police.get());
			criminalRepository.save(criminal);
			Map<String, CriminalModel> response = Collections.singletonMap("Criminal",criminal );
	        return ResponseEntity.ok(response);	
		} catch (Exception e) {
			String errorMessage = "Criminal not added to database.";
	        Map<String, String> errorResponse = Collections.singletonMap("message", errorMessage);
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
		}
	}
	
	public ResponseEntity<?> getCriminals(){
		Map<String, Object> response = new HashMap<>();
        response.put("total", criminalRepository.findAll().size());
        response.put("criminals", criminalRepository.findAll());
        return ResponseEntity.ok(response);
		
	}
	
	public ResponseEntity<?> getCriminal(Integer id) {
	    Optional<CriminalModel> criminal = criminalRepository.findById(id);
	    
	    if (criminal.isPresent()) {
	    	Map<String, CriminalModel> response = Collections.singletonMap("criminal", criminal.get());
	        return ResponseEntity.ok(response);
	    } else {
	    	String errorMessage = "Criminal not found with id: " + id;
	        Map<String, String> errorResponse = Collections.singletonMap("message", errorMessage);
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
	    }
	}
	
	public ResponseEntity<?> deleteCriminal (Integer id) {
		
		try {
			criminalRepository.deleteById(id);
			Map<String, String> response = Collections.singletonMap("message","Criminal removed" );
	        return ResponseEntity.ok(response);
		} catch (Exception e) {
			String errorMessage = "Criminal not found with id: " + id;
	        Map<String, String> errorResponse = Collections.singletonMap("message", errorMessage);
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
		}
	}
	
	public ResponseEntity<?> addArrestsCriminal(Integer id) {
	    Optional<CriminalModel> criminal = criminalRepository.findById(id);
	    
	    if (criminal.isPresent()) {
	    	Integer numArrest = criminal.get().getArrests();
	    	numArrest+=1;
	    	CriminalModel updateCriminal = criminal.get();
	    	updateCriminal.setArrests(numArrest);
	    	criminalRepository.save(updateCriminal);
	    	Map<String, CriminalModel> response = Collections.singletonMap("criminal", updateCriminal);
	        return ResponseEntity.ok(response);
	    } else {
	    	String errorMessage = "Criminal not found with id: " + id;
	        Map<String, String> errorResponse = Collections.singletonMap("message", errorMessage);
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
	    }
	}
	
	public ResponseEntity<?> updateCriminal(CriminalModel request, Integer id){
		try {
			CriminalModel criminal = criminalRepository.findById(id).get();
			criminal.setPhone(request.getPhone());
			criminal.setProvince(request.getProvince());
			criminal.setLocation(request.getLocation());
			criminalRepository.save(criminal);
			Map<String, CriminalModel> response = Collections.singletonMap("Criminal",criminal );
	        return ResponseEntity.ok(response);	
		} catch (Exception e) {
			String errorMessage = "Criminal not found with id: " + id;
	        Map<String, String> errorResponse = Collections.singletonMap("message", errorMessage);
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
		}					
	}
	
	public ResponseEntity<?> filterArrests(Integer min, Integer max){
		try {
			List<CriminalModel> datos = criminalRepository.filterArrests(min, max);
			Map<String, Object> response = new HashMap<>();
	        response.put("total", datos.size());
	        response.put("filterArrests", datos);
	        return ResponseEntity.ok(response);
		} catch (Exception e) {
			String errorMessage = "no search results.";
	        Map<String, String> errorResponse = Collections.singletonMap("message", errorMessage);
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
		}
	}
}
