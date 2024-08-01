package com.Ems.ServiceImplementions;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Ems.Entity.Entitylogin;
import com.Ems.Entity.OrganizationEntity;
import com.Ems.Exception.DuplicateDataException;
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
	
	

	public OrganizationEntity addEntity(Long logId, OrganizationEntity entity) {
        Entitylogin login = entityloginRepository.findById(logId)
                .orElseThrow(() -> new RuntimeException("Login not found"));

        if (!"SuperAdmin".equals(login.getRole())) {
            throw new NotSuperAdminException("Only SuperAdmin can create an organization");
        }

        StringBuilder duplicateFields = new StringBuilder();

        if (repo.existsByOrganizationName(entity.getOrganizationName())) {
            duplicateFields.append("Organization name ");
        }

        if (repo.existsByContactPersonName(entity.getContactPersonName())) {
            if (duplicateFields.length() > 0) duplicateFields.append(", ");
            duplicateFields.append("Contact person name ");
        }
        if (repo.existsByContactPersonEmail(entity.getContactPersonEmail())) {
            if (duplicateFields.length() > 0) duplicateFields.append(", ");
            duplicateFields.append("Contact person email ");
        }
        if (repo.existsByContactPersonPhoneNumber(entity.getContactPersonPhoneNumber())) {
            if (duplicateFields.length() > 0) duplicateFields.append(", ");
            duplicateFields.append("Contact person phone number ");
        }

        if (duplicateFields.length() > 0) {
            throw new DuplicateDataException("Organization with the provided details (" + duplicateFields.toString() + ") already exists");
        }

        entity.setLogin(login);
        return repo.save(entity);
    }

	@Override
	public OrganizationEntity getById(Long Id) {
		Optional<OrganizationEntity> optional = repo.findById(Id);
		return optional.orElse(null);
	}
	@Override
	public List<OrganizationEntity> getAllOrganizations() {
		
		return repo.findAll();
	}
	@Override
	public String deleteById(Long Id) {
		Optional<OrganizationEntity> optional = repo.findById(Id);
		if(optional.isPresent()) {
			repo.deleteById(Id);
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
