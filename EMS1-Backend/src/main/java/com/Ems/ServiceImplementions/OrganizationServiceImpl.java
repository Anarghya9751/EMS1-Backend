package com.Ems.ServiceImplementions;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Ems.Entity.Entitylogin;
import com.Ems.Entity.OrganizationEntity;
import com.Ems.Exception.DuplicateDataException;
import com.Ems.Exception.InvalidInputException;
import com.Ems.Exception.NotSuperAdminException;
import com.Ems.Exception.OrganizationNotFoundException;
import com.Ems.Repository.OrganizationRepository;
import com.Ems.Repository.Reprologin;
import com.Ems.Service.OrganizationService;
import com.Ems.dto.OrganizationForm;



@Service
public class OrganizationServiceImpl implements OrganizationService {
	
	@Autowired
	private OrganizationRepository repo;
	
	@Autowired
	private Reprologin entityloginRepository;
	
	  

//	 public OrganizationEntity addEntity(OrganizationForm form) {
//	        StringBuilder duplicateFields = new StringBuilder();
//
//	        if (repo.existsByOrganizationNameAndOrganizationTypeAndLocationAndContactPersonNameAndContactPersonEmailAndContactPersonPhoneNumber(
//	                form.getOrganizationName(),
//	                form.getOrganizationType(),
//	                form.getLocation(),
//	                form.getContactPersonName(),
//	                form.getContactPersonEmail(),
//	                form.getContactPersonPhoneNumber())) {
//	            duplicateFields.append("Organization name, type, location, contact person name, email, or phone number");
//	        }
//
//	        if (duplicateFields.length() > 0) {
//	            throw new DuplicateDataException("Organization with the provided details (" + duplicateFields.toString() + ") already exists");
//	        }
//
//	        OrganizationEntity entity = new OrganizationEntity();
//	        entity.setOrganizationName(form.getOrganizationName());
//	        entity.setOrganizationType(form.getOrganizationType());
//	        entity.setLocation(form.getLocation());
//	        entity.setContactPersonName(form.getContactPersonName());
//	        entity.setContactPersonEmail(form.getContactPersonEmail());
//	        entity.setContactPersonPhoneNumber(form.getContactPersonPhoneNumber());
//	        entity.setWebsiteURL(form.getWebsiteURL());
//	        entity.setRegistrationNumber(form.getRegistrationNumber());
//	        entity.setDescription(form.getDescription());
//	        entity.setLogoURL(form.getLogoURL());
//	        entity.setRole(form.getRole());
//	        
//
//
//	        return repo.save(entity);
//	    }
//	



	
	 @Override
	    public OrganizationEntity addEntity(OrganizationForm form) {
	        StringBuilder duplicateFields = new StringBuilder();

	        if (repo.existsByOrganizationNameAndOrganizationTypeAndLocationAndContactPersonNameAndContactPersonEmailAndContactPersonPhoneNumber(
	                form.getOrganizationName(),
	                form.getOrganizationType(),
	                form.getLocation(),
	                form.getContactPersonName(),
	                form.getContactPersonEmail(),
	                form.getContactPersonPhoneNumber())) {
	            duplicateFields.append("Organization name, type, location, contact person name, email, or phone number");
	        }

	        if (duplicateFields.length() > 0) {
	            throw new DuplicateDataException("Organization with the provided details (" + duplicateFields.toString() + ") already exists");
	        }

	        StringBuilder nullFields = new StringBuilder();

	        if (form.getOrganizationName() == null || form.getOrganizationName().trim().isEmpty()) {
	            nullFields.append("Organization Name, ");
	        }
	        if (form.getOrganizationType() == null || form.getOrganizationType().trim().isEmpty()) {
	            nullFields.append("Organization Type, ");
	        }
	        if (form.getLocation() == null || form.getLocation().trim().isEmpty()) {
	            nullFields.append("Location, ");
	        }
	        if (form.getContactPersonName() == null || form.getContactPersonName().trim().isEmpty()) {
	            nullFields.append("Contact Person Name, ");
	        }
	        if (form.getContactPersonEmail() == null || form.getContactPersonEmail().trim().isEmpty()) {
	            nullFields.append("Contact Person Email, ");
	        }
	        if (form.getContactPersonPhoneNumber() == null || form.getContactPersonPhoneNumber().trim().isEmpty()) {
	            nullFields.append("Contact Person Phone Number, ");
	        }
	        if (form.getWebsiteURL() == null || form.getWebsiteURL().trim().isEmpty()) {
	            nullFields.append("Website URL, ");
	        }
	        if (form.getRegistrationNumber() == null || form.getRegistrationNumber().trim().isEmpty()) {
	            nullFields.append("Registration Number, ");
	        }
	        if (form.getDescription() == null || form.getDescription().trim().isEmpty()) {
	            nullFields.append("Description, ");
	        }
	        if (form.getLogoURL() == null || form.getLogoURL().trim().isEmpty()) {
	            nullFields.append("Logo URL, ");
	        }
	        if (form.getRole() == null || form.getRole().trim().isEmpty()) {
	            nullFields.append("Role, ");
	        }

	        if (nullFields.length() > 0) {
	            nullFields.setLength(nullFields.length() - 2);
	            throw new InvalidInputException("The following fields cannot be null or blank: " + nullFields.toString());
	        }

	        OrganizationEntity entity = new OrganizationEntity();
	        entity.setOrganizationName(form.getOrganizationName());
	        entity.setOrganizationType(form.getOrganizationType());
	        entity.setLocation(form.getLocation());
	        entity.setContactPersonName(form.getContactPersonName());
	        entity.setContactPersonEmail(form.getContactPersonEmail());
	        entity.setContactPersonPhoneNumber(form.getContactPersonPhoneNumber());
	        entity.setWebsiteURL(form.getWebsiteURL());
	        entity.setRegistrationNumber(form.getRegistrationNumber());
	        entity.setDescription(form.getDescription());
	        entity.setLogoURL(form.getLogoURL());
	        entity.setRole(form.getRole());

	        return repo.save(entity);
	    }
	


	@Override
	public OrganizationEntity getById(Long organizationId) {
		Optional<OrganizationEntity> optional = repo.findById(organizationId);
		return optional.orElse(null);
	}
	@Override
	public List<OrganizationEntity> getAllOrganizations() {
		
		return repo.findAll();
	}
	@Override
	public String deleteById(Long organizationId) {
		Optional<OrganizationEntity> optional = repo.findById(organizationId);
		if(optional.isPresent()) {
			repo.deleteById(organizationId);
			return "deleted successfully.";
		}
		return "not deleted";
	}

	
	@Override
	public OrganizationEntity updateOrganization(Long organizationId, OrganizationForm updatedOrganizationDTO) {
		OrganizationEntity organization = repo.findById(organizationId)
                .orElseThrow(() -> new OrganizationNotFoundException(organizationId));
         
		 
        organization.setOrganizationName(updatedOrganizationDTO.getOrganizationName());
        organization.setOrganizationType(updatedOrganizationDTO.getOrganizationType());
        organization.setLocation(updatedOrganizationDTO.getLocation());
        organization.setContactPersonName(updatedOrganizationDTO.getContactPersonName());
        organization.setContactPersonEmail(updatedOrganizationDTO.getContactPersonEmail());
        organization.setContactPersonPhoneNumber(updatedOrganizationDTO.getContactPersonPhoneNumber());
        organization.setWebsiteURL(updatedOrganizationDTO.getWebsiteURL());
        organization.setLogoURL(updatedOrganizationDTO.getLogoURL());
//        organization.setLogo(updatedOrganizationDTO.getLogo());
//        organization.setDocumentPath(updatedOrganizationDTO.getDocumentPath());
        organization.setRegistrationNumber(updatedOrganizationDTO.getRegistrationNumber());
        organization.setDescription(updatedOrganizationDTO.getDescription());
        
				return repo.save(organization);
	}

}
