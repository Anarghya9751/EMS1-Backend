package com.EmployeeManagementSystem.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.EmployeeManagementSystem.Entity.BranchEntity;
import com.EmployeeManagementSystem.Entity.DepartmentEntity;
import com.EmployeeManagementSystem.Entity.SubDepartmentEntity;
import com.EmployeeManagementSystem.Service.SubDepartmentService;
import com.EmployeeManagementSystem.dto.SubDepartmentDto;



@RestController
@RequestMapping("/api/SubDepartment")
public class SubDepartmentController {

	@Autowired
	private SubDepartmentService subDepartmentService;
	
	    @PostMapping("/{departmentId}")
	    public ResponseEntity<SubDepartmentEntity> createSubDepartment(@PathVariable int departmentId,
	                                                                   @RequestParam String subDepartmentName,
	                                                                   @RequestParam String subDepartmentDescription) {
	        SubDepartmentDto subDepartmentDto = new SubDepartmentDto();
	        subDepartmentDto.setSubDepartmentName(subDepartmentName);
	        subDepartmentDto.setSubDepartmentDescription(subDepartmentDescription);

	        SubDepartmentEntity createdSubDepartment = subDepartmentService.saveSubDepartment(subDepartmentDto, departmentId);
	        return new ResponseEntity<>(createdSubDepartment, HttpStatus.CREATED);
	    }

	   

	    @DeleteMapping("/{subDepartmentId}")
	    public ResponseEntity<String> deleteSubDepartment(@PathVariable Long subDepartmentId) {
	        String result = subDepartmentService.deleteSubDepartment(subDepartmentId);
	        return new ResponseEntity<>(result, HttpStatus.OK);
	    }

	    @GetMapping("/GetSubDepartmentList")
	    public ResponseEntity<List<SubDepartmentDto>> getAllSubDepartments() {
	        List<SubDepartmentDto> subDepartments = subDepartmentService.getAllSubDepartments();
	        return new ResponseEntity<>(subDepartments, HttpStatus.OK);
	    }

	    @GetMapping("/{subDepartmentId}")
	    public ResponseEntity<SubDepartmentDto> getSubDepartmentById(@PathVariable Long subDepartmentId) {
	        SubDepartmentDto subDepartmentDto = subDepartmentService.getSubDepartmentDtoById(subDepartmentId);
	        return new ResponseEntity<>(subDepartmentDto, HttpStatus.OK);
	    }
	    
	    @PutMapping("/{subDepartmentId}")
	    public SubDepartmentEntity updateSubDepartment(
	            @PathVariable Long subDepartmentId,
	            @RequestParam String subDepartmentName,
	            @RequestParam String subDepartmentDescription
	            ) {

	        SubDepartmentEntity subDepartmentEntity = new SubDepartmentEntity();
	        subDepartmentEntity.setSubDepartmentName(subDepartmentName);
	        subDepartmentEntity.setSubDepartmentDescription(subDepartmentDescription);

	        DepartmentEntity department = new DepartmentEntity();

	        BranchEntity branch = new BranchEntity();

	        return subDepartmentService.updateSubDepartment(subDepartmentId, subDepartmentEntity);
	    }

}
