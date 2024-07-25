package com.EmployeeManagementSystem.ServiceImplementions;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.EmployeeManagementSystem.Entity.DepartmentEntity;
import com.EmployeeManagementSystem.Entity.RoleEntity;
import com.EmployeeManagementSystem.Entity.SubDepartmentEntity;
import com.EmployeeManagementSystem.Exception.DepartmentNotFoundException;
import com.EmployeeManagementSystem.Exception.RoleNotFoundException;
import com.EmployeeManagementSystem.Exception.SubDepartmentNotFoundException;
import com.EmployeeManagementSystem.Repository.DepartmentRepository;
import com.EmployeeManagementSystem.Repository.RoleRepository;
import com.EmployeeManagementSystem.Repository.SubDepartmentRepository;
import com.EmployeeManagementSystem.Service.RoleService;
import com.EmployeeManagementSystem.dto.RoleDto;


 
@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleRepository roleRepo;
	
	@Autowired
	private DepartmentRepository departmentRepo;
	
	@Autowired
	private SubDepartmentRepository subDepartmentRepository;
	
	
	@Override
	public RoleEntity AddRole(RoleEntity roleEntity, Integer departrmentId, Long subDepartmentId) {
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
	