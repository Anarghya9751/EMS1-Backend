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

import com.ems.Entity.BranchEntity;
import com.ems.Service.BranchService;
import com.ems.dto.BranchForm;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/branches")
public class BranchController {

    @Autowired
    private BranchService branchService;

   
    @PostMapping("/give/{organizationId}")
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
            @RequestParam String branchName,
            @RequestParam String branchDescription,
            @RequestParam String branchAddress,
            @RequestParam String branchContactNumber) {

        BranchForm branchDto = new BranchForm();
        branchDto.setBranchName(branchName);
        branchDto.setBranchDescription(branchDescription);
        branchDto.setBranchAddress(branchAddress);
        branchDto.setBranchContactNumber(branchContactNumber);

        BranchEntity updatedBranch = branchService.updateBranch(organizationId, branchId, branchDto);
        return new ResponseEntity<>(updatedBranch, HttpStatus.OK);
    }


	@GetMapping("/{branchId}")
	public ResponseEntity<BranchForm> getBranchById(@PathVariable Integer branchId) {
		BranchForm branch = branchService.getBranchById(branchId);
		if (branch != null) {
			return ResponseEntity.ok(branch);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/ViewtheList")
	public ResponseEntity<List<BranchForm>> getAllBranches() {
		List<BranchForm> branches = branchService.getAllBranches();
		return ResponseEntity.ok(branches);
	}

}
