package com.ems.Controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ems.Entity.SubDepartmentEntity;
import com.ems.Service.SubDepartmentService;
import com.ems.dto.SubDepartmentDTO;


@RestController
@RequestMapping("/api/SubDepartment")
public class SubDepartmentController {

	@Autowired
	private SubDepartmentService subDepartmentService;
	
	   @PostMapping("/{departmentId}")
	   public String saveSubDepartment(@PathVariable Long departmentId,
			   @RequestParam String subDepartmentName,
			   @RequestParam String subDepartmentDescription ){
		   
		   SubDepartmentEntity subdeptentity = new SubDepartmentEntity();
		   subdeptentity.setSubDepartmentName(subDepartmentName);
		   subdeptentity.setSubDepartmentDescription(subDepartmentDescription);
		   return subDepartmentService.saveSubDepartment(subdeptentity, departmentId);
	   }

	    @PutMapping("/{subDepartmentId}")
	    public ResponseEntity<SubDepartmentEntity> updateSubDepartment(@PathVariable Long subDepartmentId,
	                                                                   @RequestParam String subDepartmentName,
	                                                                   @RequestParam String subDepartmentDescription) {
	        SubDepartmentDTO subDepartmentDto = new SubDepartmentDTO();
	        subDepartmentDto.setSubDepartmentName(subDepartmentName);
	        subDepartmentDto.setSubDepartmentDescription(subDepartmentDescription);

	        SubDepartmentEntity updatedSubDepartment = subDepartmentService.updateSubDepartment(subDepartmentId, subDepartmentDto);
	        return new ResponseEntity<>(updatedSubDepartment, HttpStatus.OK);
	    }


	    @DeleteMapping("/{subDepartmentId}")
	    public ResponseEntity<String> deleteSubDepartment(@PathVariable Long subDepartmentId) {
	        String result = subDepartmentService.deleteSubDepartment(subDepartmentId);
	        return new ResponseEntity<>(result, HttpStatus.OK);
	    }

	    @GetMapping("/GetSubDepartmentList")
	    public ResponseEntity<List<SubDepartmentDTO>> getAllSubDepartments() {
	        List<SubDepartmentDTO> subDepartments = subDepartmentService.getAllSubDepartments();
	        return new ResponseEntity<>(subDepartments, HttpStatus.OK);
	    }

	    @GetMapping("/{subDepartmentId}")
	    public ResponseEntity<SubDepartmentDTO> getSubDepartmentById(@PathVariable Long subDepartmentId) {
	        SubDepartmentDTO subDepartmentDto = subDepartmentService.getSubDepartmentDtoById(subDepartmentId);
	        return new ResponseEntity<>(subDepartmentDto, HttpStatus.OK);
	    }
	

}
