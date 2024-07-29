package com.Ems.Controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import org.springframework.web.multipart.MultipartFile;

import com.Ems.Entity.OrganizationEntity;
import com.Ems.Service.OrganizationService;
import com.Ems.dto.OrganizationForm;





@RestController
@RequestMapping("/api/Organization")
public class OrganizationController {
	
	@Autowired
	private OrganizationService service;
	
	
	@PostMapping("/save")
    public ResponseEntity<OrganizationEntity> addOrganization(
            @RequestParam String organizationName,
            @RequestParam String organizationType,
            @RequestParam String location,
            @RequestParam String contactPersonName,
            @RequestParam String contactPersonEmail,
            @RequestParam String contactPersonPhoneNumber,
            @RequestParam String websiteURL,
            @RequestParam String registrationNumber,
            @RequestParam String description,
            @RequestParam String logoURL) {

        OrganizationEntity organizationEntity = new OrganizationEntity();
        organizationEntity.setOrganizationName(organizationName);
        organizationEntity.setOrganizationType(organizationType);
        organizationEntity.setLocation(location);
        organizationEntity.setContactPersonName(contactPersonName);
        organizationEntity.setContactPersonEmail(contactPersonEmail);
        organizationEntity.setContactPersonPhoneNumber(contactPersonPhoneNumber);
        organizationEntity.setWebsiteURL(websiteURL);
        organizationEntity.setRegistrationNumber(registrationNumber);
        organizationEntity.setDescription(description);
        organizationEntity.setLogoURL(logoURL);

        OrganizationEntity addedEntity = service.addEntity(organizationEntity);
        return new ResponseEntity<>(addedEntity, HttpStatus.CREATED);
    }

	
	
//	@PostMapping("/save")
//	  public ResponseEntity<OrganizationEntity> addEntity (
//			  @RequestParam("organizationName") String organizationName,
//			  @RequestParam("organizationType") String organizationType,
//			  @RequestParam("location") String location,
//			  @RequestParam("contactPersonName") String contactPersonName,
//			  @RequestParam("contactPersonEmail") String contactPersonEmail,
//			  @RequestParam("contactPersonPhoneNumber") String contactPersonPhoneNumber,
//			  @RequestParam("websiteURL") String websiteURL,
//			  @RequestParam("description")String  description,
//			  @RequestParam("registrationNumber") String registrationNumber,
//			  @RequestParam("logoURL") String logoURL) {
////		 try {
////	            byte[] documentBytes = logo.getBytes();
////	            String documentFilePath = saveFileToDisk(logo);
////	            if (documentFilePath == null) {
////	                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
////	            }
//	            
//	            OrganizationEntity orgEntity = new OrganizationEntity();
//	            orgEntity.setOrganizationName(organizationName);
//	            orgEntity.setOrganizationType(organizationType);
//	            orgEntity.setLocation(location);
//	            orgEntity.setContactPersonName(contactPersonName);
//	            orgEntity.setContactPersonEmail(contactPersonEmail);
//	            orgEntity.setContactPersonPhoneNumber(contactPersonPhoneNumber);
//	            orgEntity.setWebsiteURL(websiteURL);
//	            orgEntity.setLogoURL(logoURL);
////	            orgEntity.setLogo(documentBytes);
////	            orgEntity.setDocumentPath(documentFilePath);
//	            orgEntity.setRegistrationNumber(registrationNumber);
//	            orgEntity.setDescription(description);
//	            
//	            
//	            OrganizationEntity Entity = service.addEntity(orgEntity);
//	            return ResponseEntity.status(HttpStatus.CREATED).body(Entity);
//	            
//		 }
////		 catch (Exception e) {
////	            e.printStackTrace();
////	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
////	        }
////				
////		 
////	  }
////	 private String saveFileToDisk(MultipartFile file) {
////	        try {
////	            String uploadDir = "D:\\Users\\My Files";
////	            File dir = new File(uploadDir);
////	            if (!dir.exists()) {
////	                dir.mkdirs();
////	            }
////
////	            String filePath = uploadDir + "/" + file.getOriginalFilename();
////	            file.transferTo(new File(filePath));
////	            return filePath;
////	        } catch (IOException e) {
////	            e.printStackTrace();
////	            return null;
////	        }
//	    
	
	
	@GetMapping("/get/{Id}")
	public ResponseEntity<OrganizationEntity>getById(@PathVariable Long Id){
		OrganizationEntity organization = service.getById(Id);
		return ResponseEntity.ok().body(organization);
	}
	
	
	
	@GetMapping("/findAll")
	List<OrganizationEntity>getAll(){
		return service.getAllOrganizations();
	}
	
	@DeleteMapping("/delete/{Id}")
	public String deleteById(@PathVariable Long Id){
		return service.deleteById(Id);
	}
	
	
//	@PutMapping("/Update/{organizationId}")
//	public ResponseEntity<OrganizationEntity> updateOrganization(
//	        @PathVariable Long organizationId,
//	        @RequestBody OrganizationForm organizationForm) {
//
//	    OrganizationEntity updatedOrganization = service.updateOrganization(organizationId, organizationForm);
//	    return new ResponseEntity<>(updatedOrganization, HttpStatus.OK);
//	}
	
	@PutMapping("/Update/{organizationId}")
	public ResponseEntity<OrganizationEntity> updateOrganization(
			@PathVariable Long organizationId,
			@RequestParam String organizationName,
			@RequestParam String organizationType,
			@RequestParam String location,
			@RequestParam String contactPersonName,
			@RequestParam String contactPersonEmail,
			@RequestParam String contactPersonPhoneNumber,
			@RequestParam String websiteURL,
			@RequestParam String registrationNumber,
			@RequestParam String description,
			@RequestParam String logoURL){OrganizationForm orgentity = new OrganizationForm();
		
		orgentity.setOrganizationName(organizationName);
		orgentity.setOrganizationType(organizationType);
		orgentity.setLocation(location);
		orgentity.setContactPersonName(contactPersonName);
		orgentity.setContactPersonEmail(contactPersonEmail);
		orgentity.setContactPersonPhoneNumber(contactPersonPhoneNumber);
		orgentity.setWebsiteURL(websiteURL);
		orgentity.setLogoURL(logoURL);
//		orgentity.setLogo(documentBytes);
//		orgentity.setDocumentPath(documentFilePath);
		orgentity.setRegistrationNumber(registrationNumber);
		orgentity.setDescription(description);
		
		OrganizationEntity updateOrganization = service.updateOrganization(organizationId, orgentity);
		return new ResponseEntity<>(updateOrganization,HttpStatus.OK);
		}
		 

}
