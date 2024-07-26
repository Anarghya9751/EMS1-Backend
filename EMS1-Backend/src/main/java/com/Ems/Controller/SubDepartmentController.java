package com.Ems.Controller;

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

import com.Ems.Entity.BranchEntity;
import com.Ems.Entity.DepartmentEntity;
import com.Ems.Entity.SubDepartmentEntity;
import com.Ems.Service.SubDepartmentService;
import com.Ems.dto.SubDepartmentDto;



@RestController
@RequestMapping("/api/SubDepartment")
public class SubDepartmentController {

	@Autowired
	private SubDepartmentService subDepartmentService;
	
	    @PostMapping("/save/{departmentId}")
	    public ResponseEntity<SubDepartmentEntity> createSubDepartment(@PathVariable int departmentId,
	                                                                   @RequestParam String subDepartmentName,
	                                                                   @RequestParam String subDepartmentDescription) {
	        SubDepartmentDto subDepartmentDto = new SubDepartmentDto();
	        subDepartmentDto.setSubDepartmentName(subDepartmentName);
	        subDepartmentDto.setSubDepartmentDescription(subDepartmentDescription);

	        SubDepartmentEntity createdSubDepartment = subDepartmentService.saveSubDepartment(subDepartmentDto, departmentId);
	        return new ResponseEntity<>(createdSubDepartment, HttpStatus.CREATED);
	    }

	   

	    @DeleteMapping("/Delete/{subDepartmentId}")
	    public ResponseEntity<String> deleteSubDepartment(@PathVariable Long subDepartmentId) {
	        String result = subDepartmentService.deleteSubDepartment(subDepartmentId);
	        return new ResponseEntity<>(result, HttpStatus.OK);
	    }


	    
	    @PutMapping("update/{subDepartmentId}")
	    public SubDepartmentEntity updateSubDepartment(
	            @PathVariable Long subDepartmentId,
	            @RequestBody SubDepartmentDto subDepartmentDto) {

	        SubDepartmentEntity subDepartmentEntity = new SubDepartmentEntity();
	        subDepartmentEntity.setSubDepartmentName(subDepartmentDto.getSubDepartmentName());
	        subDepartmentEntity.setSubDepartmentDescription(subDepartmentDto.getSubDepartmentDescription());

	        return subDepartmentService.updateSubDepartment(subDepartmentId, subDepartmentEntity);
	    }
	    
//	    @GetMapping("/GetSubDepartmentList")
//	    public ResponseEntity<List<SubDepartmentDto>> getAllSubDepartments() {
//	        List<SubDepartmentDto> subDepartments = subDepartmentService.getAllSubDepartments();
//	        return new ResponseEntity<>(subDepartments, HttpStatus.OK);
//	    }
//
//	    @GetMapping("/SubDepartment/{subDepartmentId}")
//	    public ResponseEntity<SubDepartmentDto> getSubDepartmentById(@PathVariable Long subDepartmentId) {
//	        SubDepartmentDto subDepartmentDto = subDepartmentService.getSubDepartmentDtoById(subDepartmentId);
//	        return new ResponseEntity<>(subDepartmentDto, HttpStatus.OK);
//	    }
//	
	    
	    @GetMapping("/GetSubDepartmentList")
	    public ResponseEntity<List<SubDepartmentEntity>> getAllSubDepartments() {
	        List<SubDepartmentEntity> subDepartments = subDepartmentService.getAllSubDepartments();
	        return ResponseEntity.ok(subDepartments);
	    }

	    @GetMapping("/SubDepartment/{subDepartmentId}")
	    public ResponseEntity<SubDepartmentEntity> getSubDepartmentById(@PathVariable Long subDepartmentId) {
	        SubDepartmentEntity subDepartment = subDepartmentService.getSubDepartmentById(subDepartmentId);
	        return ResponseEntity.ok(subDepartment);
	    }

}
