package com.ems.ServiceImplementions;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ems.Entity.DepartmentEntity;
import com.ems.Entity.RoleEntity;
import com.ems.Entity.SubDepartmentEntity;
import com.ems.Exception.DepartmentNotFoundException;
import com.ems.Exception.RoleNotFoundException;
import com.ems.Exception.SubDepartmentNotFoundException;
import com.ems.Repo.DepartmentRepository;
import com.ems.Repo.RoleRepository;
import com.ems.Repo.SubDepartmentRepository;
import com.ems.Service.RoleService;
import com.ems.dto.RoleDto;
 
@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleRepository roleRepo;
	
	@Autowired
	private DepartmentRepository departmentRepo;
	
	@Autowired
	private SubDepartmentRepository subDepartmentRepository;
	
	
	@Override
	public RoleEntity AddRole(RoleEntity roleEntity, Long departrmentId, Long subDepartmentId) {
		DepartmentEntity department = departmentRepo.findById(departrmentId)
				.orElseThrow(() -> new DepartmentNotFoundException(departrmentId));
				
		SubDepartmentEntity subDepartment = subDepartmentRepository.findById(subDepartmentId)
				.orElseThrow(()  -> new SubDepartmentNotFoundException(subDepartmentId));
		
		roleEntity.setDepartment(department);
		roleEntity.setSubdepartment(subDepartment);
		return roleRepo.save(roleEntity);
	}

	
	@Override
	public RoleDto getRoleById(Long roleId) {
		RoleEntity roleEntity = roleRepo.findById(roleId)
				.orElseThrow(() -> new RoleNotFoundException(roleId));
		
		RoleDto roleDTO =  new RoleDto();
		roleDTO.setRoleId(roleEntity.getRoleId());
		roleDTO.setRoleName(roleEntity.getRoleName());
		roleDTO.setRoleDescription(roleEntity.getRoleDescription());
		roleDTO.setDepartmentId(roleEntity.getDepartment().getDepartmentId());
		roleDTO.setSubDepartmentId(roleEntity.getSubdepartment().getSubDepartmentId());
		
		return roleDTO;
		
			}

	@Override
	public List<RoleEntity> getAllRole() {
		
		return roleRepo.findAll();
	}
 
	@Override
	public String deleteById(Long roleId) {
		Optional<RoleEntity> optionalRole = roleRepo.findById(roleId);
		if (optionalRole.isPresent()) {
			
			roleRepo.deleteById(roleId);
			return "deleted successfully";
			
		}
		return "not deleted";
	}

	@Override
	public RoleEntity updateRole(Long roleId, RoleEntity updateRole) {
		RoleEntity role = roleRepo.findById(roleId)
				.orElseThrow(() -> new RoleNotFoundException(roleId));
		role.setRoleName(updateRole.getRoleName());
		role.setRoleDescription(updateRole.getRoleDescription());
		role.setDepartment(updateRole.getDepartment());
		role.setSubdepartment(updateRole.getSubdepartment());
		return roleRepo.save(role);
		
	}

	

	
	}
	
	
	
	



