package com.EmployeeManagementSystem.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.EmployeeManagementSystem.Entity.DepartmentEntity;
import com.EmployeeManagementSystem.Service.DepartmentService;
import com.EmployeeManagementSystem.dto.DepartmentDto;



@RestController
@CrossOrigin("*****")
@RequestMapping("/api/departments")
public class DepartmentController {

	    @Autowired
	    private DepartmentService departmentService;

	    @PostMapping("/{organizationId}/{branchId}")
	    public DepartmentEntity createDepartment(
	            @PathVariable Long organizationId,
	            @PathVariable Integer branchId,
	            @RequestParam String departmentName,
	            @RequestParam String departmentDescription) {
	        
	        DepartmentEntity departmentEntity = new DepartmentEntity();
	        departmentEntity.setDepartmentname(departmentName);
	        departmentEntity.setDepartmentDescription(departmentDescription);
	        
	        return departmentService.saveDepartment(departmentEntity, organizationId, branchId);
	    }

	    @DeleteMapping("/{departmentId}")
	    public String deleteDepartmentById(@PathVariable int departmentId) {
	        return departmentService.deleteById(departmentId);
	    }

	    
	    @GetMapping("/GetDeptBYID/{departmentId}")
	    public DepartmentDto getDepartmentDtoById(@PathVariable int departmentId) {
	        return departmentService.getDepartmentDtoById(departmentId);
	    }

	    @GetMapping("/DeptList")
	    public List<DepartmentDto> getAllDepartments() {
	        return departmentService.getAllDepartments();
	    }
	    
	    
	   
	    
	    @PutMapping("/update/{organizationId}/{branchId}/{departmentId}")
	    public ResponseEntity<DepartmentEntity> updateDepartment(
	            @PathVariable Long organizationId,
	            @PathVariable Integer branchId,
	            @PathVariable Integer departmentId,
	            @RequestParam String departmentName,
	            @RequestParam String departmentDescription) {

	        DepartmentDto departmentDto = new DepartmentDto();
	        departmentDto.setDepartmentname(departmentName);
	        departmentDto.setDepartmentDescription(departmentDescription);

	        try {
	            DepartmentEntity updatedDepartment = departmentService.updateDepartment(organizationId, branchId, departmentId, departmentDto);
	            return new ResponseEntity<>(updatedDepartment, HttpStatus.OK);
	        } catch (Exception e) {
	            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }

	    @GetMapping("/GetDeptByBranchID/{branchId}")
	    public List<DepartmentDto> getDepartmentsByBranchId(@PathVariable Integer branchId) {
	        return departmentService.getDepartmentsByBranchId(branchId);
	    }
	    
}
