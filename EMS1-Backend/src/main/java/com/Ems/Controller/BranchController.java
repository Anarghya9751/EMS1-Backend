 package com.Ems.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.Ems.Entity.BranchEntity;
import com.Ems.Service.BranchService;
import com.Ems.dto.BranchForm;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/branches")
public class BranchController {

    @Autowired
    private BranchService branchService;

   
    @PostMapping("/save/{organizationId}")
    public String createBranch(@PathVariable Long organizationId, @Valid @RequestParam String branchName, 
                               @RequestParam String branchDescription, @RequestParam String branchAddress, 
                               @RequestParam String branchContactNumber) {
        BranchEntity branchEntity = new BranchEntity();
        branchEntity.setBranchName(branchName);
        branchEntity.setBranchDescription(branchDescription);
        branchEntity.setBranchAddress(branchAddress);
        branchEntity.setBranchContactNumber(branchContactNumber);
        return branchService.createBranch(organizationId, branchEntity);
    }

    

    @DeleteMapping("/{branchId}")
    public String deleteBranch(@PathVariable Integer branchId)
    {
        return branchService.deleteBranch(branchId);
    }

   



  
	
	 @PutMapping("/update/{organizationId}/{branchId}")
	    public ResponseEntity<BranchEntity> updateBranch(
	            @PathVariable Long organizationId,
	            @PathVariable Integer branchId,
	            @RequestBody BranchForm branchForm) {

	        BranchEntity updatedBranch = branchService.updateBranch(organizationId, branchId, branchForm);
	        return new ResponseEntity<>(updatedBranch, HttpStatus.OK);
	    }
	

	 

	 @GetMapping("/BranchDept/{branchId}")
	    public ResponseEntity<BranchEntity> getBranchWithDepartments(@PathVariable Integer branchId) {
	        BranchEntity branch = branchService.getBranchWithDepartments(branchId);
	        return ResponseEntity.ok(branch);
	    }
	 @GetMapping("/ViewtheList")
	    public ResponseEntity<List<BranchEntity>> getAllBranches() {
	        List<BranchEntity> branches = branchService.getAllBranches();
	        return ResponseEntity.ok(branches);
	    }
}
