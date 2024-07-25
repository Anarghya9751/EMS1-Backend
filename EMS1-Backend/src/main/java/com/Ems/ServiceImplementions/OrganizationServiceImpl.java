package com.EmployeeManagementSystem.ServiceImplementions;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.EmployeeManagementSystem.Entity.OrganizationEntity;
import com.EmployeeManagementSystem.Exception.OrganizationNotFoundException;
import com.EmployeeManagementSystem.Repository.OrganizationRepository;
import com.EmployeeManagementSystem.Service.OrganizationService;
import com.EmployeeManagementSystem.dto.OrganizationForm;



@Service
public class OrganizationServiceImpl implements OrganizationService {
	
	@Autowired
	private OrganizationRepository repo;

	@Override
	public OrganizationEntity AddEntity(OrganizationEntity entity) {
		
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
