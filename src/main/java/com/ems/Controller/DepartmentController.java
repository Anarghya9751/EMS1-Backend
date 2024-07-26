package com.ems.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.ems.Entity.DepartmentEntity;
import com.ems.Service.DepartmentService;
import com.ems.dto.DepartmentDto;

@RestController
@RequestMapping("/api/department")
public class DepartmentController {
	
	@Autowired
	private DepartmentService departmentService;
	
	
	    @PostMapping("create/{organizationId}/{branchId}")
	    public DepartmentEntity AddDepartment(
	            @PathVariable Long organizationId,
	            @PathVariable Integer branchId,
	            @RequestParam String departmentName,
	            @RequestParam String departmentDescription) {
	        
	        DepartmentEntity departmentEntity = new DepartmentEntity();
	        departmentEntity.setDepartmentName(departmentName);
	        departmentEntity.setDepartmentDescription(departmentDescription);
	        
	        return departmentService.AddDepartment(departmentEntity, branchId, organizationId);
	    }

	    @DeleteMapping("/{departmentId}")
	    public String deleteDepartmentById(@PathVariable Long departmentId) {
	        return departmentService.deleteById(departmentId);
	    }

	
	@GetMapping("/get/{departmentId}")
	public DepartmentDto getDepartmentById(@PathVariable Long departmentId){
		
		return departmentService.getDepartmentById(departmentId);
	}

	@GetMapping("/getAll")
	public List<DepartmentDto> getAllDepartments(){
		return departmentService.getAllDepartments();
		
	}
	
	@DeleteMapping("/delete/{departmentId}")
	public String deleteById(@PathVariable Long departmentId) {
		return departmentService.deleteById(departmentId);
	}
	
	@PutMapping("/update/{departmentId}")
	public ResponseEntity<DepartmentEntity>updateDepartment(@RequestBody DepartmentEntity departmentEntity,@PathVariable Long departmentId){
		DepartmentEntity updateDepartment = departmentService.updateDepartment(departmentEntity, departmentId);
		return ResponseEntity.ok(updateDepartment);
	}
}
