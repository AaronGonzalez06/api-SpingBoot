package com.api.policeStation.controllers;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.policeStation.services.PdfService;

@RestController
@RequestMapping("/api")
public class PdfController {
	
	@Autowired
	PdfService pdfService;
	
	
	//polices
	
	@GetMapping("/getListPolicesPdf")
    public ResponseEntity<byte[]> getListPolices() {
		byte[] pdfBytes = pdfService.getListPolices();

        if (pdfBytes != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentLength(pdfBytes.length);
            headers.add("Content-Disposition", "attachment; filename=ListPolicess.pdf");

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(pdfBytes);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    
    //criminals
    
    @GetMapping("/getListCriminalsPdf")
    public ResponseEntity<byte[]> getListCriminals() {
		byte[] pdfBytes = pdfService.getListCriminals();

        if (pdfBytes != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentLength(pdfBytes.length);
            headers.add("Content-Disposition", "attachment; filename=ListCriminals.pdf");

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(pdfBytes);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/getListCriminalsArrestsPdf/{min}/{max}")
    public ResponseEntity<byte[]> getListCriminalsArrestsPdf(@PathVariable Integer min,@PathVariable Integer max) {
		byte[] pdfBytes = pdfService.getListCriminalsArrests(min,max);

        if (pdfBytes != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentLength(pdfBytes.length);
            headers.add("Content-Disposition", "attachment; filename=ListCriminals.pdf");

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(pdfBytes);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/getListPolicePrivateOffice/{PrivateOffice}")
    public ResponseEntity<byte[]> getListCriminalsArrestsPdf(@PathVariable String PrivateOffice) {
		byte[] pdfBytes = pdfService.getListPolicePrivateOffice(PrivateOffice.toUpperCase());

        if (pdfBytes != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentLength(pdfBytes.length);
            headers.add("Content-Disposition", "attachment; filename=ListCriminals.pdf");

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(pdfBytes);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/getSupervisorPolicePdf/{id}")
    public ResponseEntity<byte[]> getSupervisorPolicePdf(@PathVariable Integer id) {
		byte[] pdfBytes = pdfService.getSupervisorPolicePdf(id);

        if (pdfBytes != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentLength(pdfBytes.length);
            headers.add("Content-Disposition", "attachment; filename=ListCriminals.pdf");

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(pdfBytes);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
