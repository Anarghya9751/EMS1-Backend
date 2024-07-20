package com.ems.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ems.Entity.RoleEntity;
import com.ems.Service.RoleService;
import com.ems.dto.RoleDto;

@RestController
@RequestMapping("/api/role")
public class RoleController {
	
	@Autowired
	private RoleService roleService;
	
	@PostMapping("/have/{departmentId}/{subDepartmentId}")
	public RoleEntity AddRole(
			@PathVariable Long departmentId,
			@PathVariable Long subDepartmentId,
			@RequestParam String roleName,
			@RequestParam String roleDescription) {
		RoleEntity roleEntity = new RoleEntity();
		roleEntity.setRoleName(roleName);
		roleEntity.setRoleDescription(roleDescription);
		
		return roleService.AddRole(roleEntity, departmentId, subDepartmentId);
	}

	@GetMapping("/get/{roleId}")
	public RoleDto  getRoleById(@PathVariable Long roleId){
		return roleService.getRoleById(roleId);
	}
	
 @GetMapping("/getAll")
 List<RoleEntity> getAllRole(){
	return roleService.getAllRole();
 }
	
	@DeleteMapping("/delete/{roleId}")
	public String deleteById(@PathVariable Long roleId) {
		return roleService.deleteById(roleId);
	}
	
	
@PutMapping("/update/{roleId}")	
public ResponseEntity<RoleEntity> updateRole(Long roleId,RoleEntity updateRole){
	RoleEntity update = roleService.updateRole(roleId, updateRole);
	return ResponseEntity.ok(update);
}
}
