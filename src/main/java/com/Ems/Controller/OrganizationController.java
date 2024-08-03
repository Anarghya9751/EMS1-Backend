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
import com.Ems.Exception.DuplicateDataException;
import com.Ems.Exception.NotSuperAdminException;
import com.Ems.Service.OrganizationService;
import com.Ems.dto.OrganizationForm;





@RestController
@RequestMapping("/api/Organization")
public class OrganizationController {
	
	@Autowired
	private OrganizationService service;
	
	
	@PostMapping("/create")
	public ResponseEntity<String> addOrganization(
	        @RequestParam String organizationName,
	        @RequestParam String organizationType,
	        @RequestParam String location,
	        @RequestParam String contactPersonName,
	        @RequestParam String contactPersonEmail,
	        @RequestParam String contactPersonPhoneNumber,
	        @RequestParam String websiteURL,
	        @RequestParam String registrationNumber,
	        @RequestParam String description,
	        @RequestParam String logoURL,
	        @RequestParam String role) {

	    try {
	        OrganizationForm organizationForm = new OrganizationForm();
	        organizationForm.setOrganizationName(organizationName);
	        organizationForm.setOrganizationType(organizationType);
	        organizationForm.setLocation(location);
	        organizationForm.setContactPersonName(contactPersonName);
	        organizationForm.setContactPersonEmail(contactPersonEmail);
	        organizationForm.setContactPersonPhoneNumber(contactPersonPhoneNumber);
	        organizationForm.setWebsiteURL(websiteURL);
	        organizationForm.setRegistrationNumber(registrationNumber);
	        organizationForm.setDescription(description);
	        organizationForm.setLogoURL(logoURL);
	        organizationForm.setRole(role);

	        OrganizationEntity addedEntity = service.addEntity(organizationForm);

	        return new ResponseEntity<>("Organization added successfully", HttpStatus.CREATED);

	    } catch (DuplicateDataException e) {
	        return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
	    } catch (Exception e) {
	        return new ResponseEntity<>("An error occurred while adding the organization", HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}

	
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
