package com.api.policeStation.services;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.policeStation.models.CriminalModel;
import com.api.policeStation.models.PoliceModel;
import com.api.policeStation.repositories.IUCriminalRepository;
import com.api.policeStation.repositories.IUPoliceRepository;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.List;
import com.itextpdf.layout.element.ListItem;

@Service
public class PdfService {
	
	@Autowired
	IUPoliceRepository policeRepository;
	
	@Autowired
	IUCriminalRepository criminalRepository;
	
	public byte[] getListPolices () {
		try {
			java.util.List<PoliceModel> polices =policeRepository.findAll();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            PdfWriter pdfWriter = new PdfWriter(outputStream);
            PdfDocument pdfDocument = new PdfDocument(pdfWriter);
            Document document = new Document(pdfDocument);
            List listPolice = new List();
            document.add(new Paragraph("List of Polices.\n\n\n").setTextAlignment(TextAlignment.CENTER));
            for (PoliceModel police : polices) {
            	listPolice.add(new ListItem("Name: " + police.getName() + ", Surname: " + police.getSurname() + "DNI: " +police.getDni() +" Range: "+police.getPrivateOffice() +"\n\n"));            
            }
            document.add(listPolice);
            document.close();
            pdfWriter.close();

            return outputStream.toByteArray();
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
	}
	
	public byte[] getListPolicePrivateOffice (String PrivateOffice) {
		try {
			java.util.List<PoliceModel> polices = policeRepository.filterPrivateOffice(PrivateOffice);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            PdfWriter pdfWriter = new PdfWriter(outputStream);
            PdfDocument pdfDocument = new PdfDocument(pdfWriter);
            Document document = new Document(pdfDocument);
            List listPolice = new List();
            document.add(new Paragraph("List of polices.\n\n\n").setTextAlignment(TextAlignment.CENTER));
            if(polices.size() == 0) {
            	listPolice.add(new ListItem("the "+ PrivateOffice+" position does not exist."));            	
            }else {
            	for (PoliceModel police : polices) {
                	listPolice.add(new ListItem("Name: " + police.getName() + ", Surname: " + police.getSurname() + "DNI: " +police.getDni() +" Range: "+police.getPrivateOffice() +"\n\n"));            
                }
            }            
            document.add(listPolice);
            document.close();
            pdfWriter.close();

            return outputStream.toByteArray();
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
	}
	
	public byte[] getSupervisorPolicePdf (Integer id) {
		try {
			PoliceModel police = policeRepository.findById(id).get();
			java.util.List<PoliceModel> polices = policeRepository.listSupervisorPolice(police);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            PdfWriter pdfWriter = new PdfWriter(outputStream);
            PdfDocument pdfDocument = new PdfDocument(pdfWriter);
            Document document = new Document(pdfDocument);
            List listPolice = new List();            
            if(polices.size() == 0) {
            	listPolice.add(new ListItem("police not found."));            	
            }else {
            	document.add(new Paragraph("Supervisor.\n\n").setTextAlignment(TextAlignment.CENTER));
            	document.add(new Paragraph("Name: " + police.getName() + ", Surname: " + police.getSurname() + "DNI: " +police.getDni() +" Range: "+police.getPrivateOffice() +"\n\n"));
            	document.add(new Paragraph("List of polices.\n\n").setTextAlignment(TextAlignment.CENTER));
            	for (PoliceModel p : polices) {
                	listPolice.add(new ListItem("Name: " + p.getName() + ", Surname: " + p.getSurname() + "DNI: " +p.getDni() +" Range: "+p.getPrivateOffice() +"\n\n"));            
                }
            }            
            document.add(listPolice);
            document.close();
            pdfWriter.close();

            return outputStream.toByteArray();
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
	}
	
	//criminales
	
	public byte[] getListCriminals () {
		try {
			java.util.List<CriminalModel> criminals =criminalRepository.findAll();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            PdfWriter pdfWriter = new PdfWriter(outputStream);
            PdfDocument pdfDocument = new PdfDocument(pdfWriter);
            Document document = new Document(pdfDocument);
            List listCriminal = new List();
            document.add(new Paragraph("List of criminals.\n\n\n").setTextAlignment(TextAlignment.CENTER));
            for (CriminalModel criminal : criminals) {
            	listCriminal.add(new ListItem("Name: " + criminal.getName() + ", Surname: " + criminal.getSurname() + "DNI: " +criminal.getDni() +" Arrest: "+ criminal.getArrests() +"\n\n"));            
            }
            document.add(listCriminal);
            document.close();
            pdfWriter.close();

            return outputStream.toByteArray();
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
	}
	
	public byte[] getListCriminalsArrests (Integer minNum, Integer maxMun) {
		try {
			java.util.List<CriminalModel> criminals =criminalRepository.filterArrests(minNum, maxMun);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            PdfWriter pdfWriter = new PdfWriter(outputStream);
            PdfDocument pdfDocument = new PdfDocument(pdfWriter);
            Document document = new Document(pdfDocument);
            List listCriminal = new List();
            document.add(new Paragraph("List of criminals.\n\n\n").setTextAlignment(TextAlignment.CENTER));
            if(criminals.size() == 0) {
            	listCriminal.add(new ListItem("Criminals not found with arrests between "+minNum+" and "+ maxMun));            	
            }else {
            	for (CriminalModel criminal : criminals) {
                	listCriminal.add(new ListItem("Name: " + criminal.getName() + ", Surname: " + criminal.getSurname() + "DNI: " +criminal.getDni() +" Arrest: "+ criminal.getArrests() +"\n\n"));            
                }
            }            
            document.add(listCriminal);
            document.close();
            pdfWriter.close();

            return outputStream.toByteArray();
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
	}

}
