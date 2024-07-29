package com.Ems.Controller;

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

import com.Ems.Entity.DepartmentEntity;
import com.Ems.Exception.DepartmentNotFoundException;
import com.Ems.Service.DepartmentService;
import com.Ems.dto.DepartmentDto;



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

	    
	   
	    
	   
	    
	    @PutMapping("/update/{organizationId}/{branchId}/{departmentId}")
	    public ResponseEntity<DepartmentEntity> updateDepartment(
	            @PathVariable Long organizationId,
	            @PathVariable Integer branchId,
	            @PathVariable Integer departmentId,
	            @RequestBody DepartmentDto departmentDto) {

	        try {
	            DepartmentEntity updatedDepartment = departmentService.updateDepartment(organizationId, branchId, departmentId, departmentDto);
	            return new ResponseEntity<>(updatedDepartment, HttpStatus.OK);
	        } catch (Exception e) {
	            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }
	    
	    
	    @GetMapping("GetDeptBYID1/{departmentId}")
	    public ResponseEntity<DepartmentEntity> getDepartmentById1(@PathVariable Integer departmentId) {
	        try {
	            DepartmentEntity departmentEntity = departmentService.getDepartmentById1(departmentId);
	            return ResponseEntity.ok(departmentEntity);
	        } catch (DepartmentNotFoundException e) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	        }
	    }

//	    @GetMapping("/DeptList")
//	    public ResponseEntity<List<DepartmentEntity>> getAllDepartments() {
//	        List<DepartmentEntity> departmentEntities = departmentService.getAllDepartments();
//	        return ResponseEntity.ok(departmentEntities);
//	    }
	    
	    @GetMapping("GetDeptBYID/{departmentId}")
		public DepartmentDto getDepartmentById(@PathVariable int departmentId){
			
			return departmentService.getDepartmentById(departmentId);
		}

	    @GetMapping("/DeptList")
		public List<DepartmentDto> getAllDepartments(){
			return departmentService.getAllDepartments();
			
		}
	    
}
