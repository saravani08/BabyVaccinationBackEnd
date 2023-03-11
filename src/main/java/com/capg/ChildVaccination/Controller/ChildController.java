package com.capg.ChildVaccination.Controller;

import java.util.List;

import org.springframework.data.domain.Page;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
import org.springframework.web.bind.annotation.RestController;

import com.capg.ChildVaccination.Dto.ChildDto;
import com.capg.ChildVaccination.Dto.ParentDto;
import com.capg.ChildVaccination.Entity.Child;
import com.capg.ChildVaccination.Entity.Parent;
import com.capg.ChildVaccination.Service.ChildService;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/child")
public class ChildController {
	
	@Autowired
	ChildService childService;
	private static Logger logger=LogManager.getLogger();
	
	@PostMapping("/insertChild/{email}")
	public ResponseEntity<Child> addChild(@PathVariable("email")String parentEmail ,@RequestBody ChildDto child){
		logger.info("adding child");
		System.out.println(child.toString());
		Child c1 = childService.addChild(parentEmail,child);
		logger.info("Child added");
		System.out.println(c1.toString());
		return new ResponseEntity<>(c1,HttpStatus.CREATED);
	}
	
	@PutMapping("/updateChild/{id}")
	public ResponseEntity<Child> updateChild(@PathVariable("id")int id , @RequestBody ChildDto child){
		logger.info("posting parent details");
		Child p1 = childService.updateChild(id,child);
		logger.info("Parent posted");
		return new ResponseEntity<>(p1,HttpStatus.OK);
	}
	

	@DeleteMapping("/deleteChild/{id}")
	public ResponseEntity<Child> deleteChild(@PathVariable("id")int id){
		logger.info("deleting parent details");
		Child p1 = childService.delChild(id);
		logger.info("Parent deleted");
		return new ResponseEntity<>(p1,HttpStatus.OK);
	}
	
	@GetMapping("/viewChild/{id}")
	public ResponseEntity<Child> viewChild(@PathVariable("id")int id){
		logger.info("getting child details");
		Child p1 = childService.viewChild(id);
		logger.info("child details received");
		return new ResponseEntity<>(p1,HttpStatus.OK);
	}
	
	@GetMapping("/viewAllChild/{id}")
	public ResponseEntity<List<Child>> viewAllChild(@PathVariable int id){
		logger.info("getting child details");
		List<Child> p1 = childService.viewAllChild(id);
		logger.info("child details received");
		return new ResponseEntity<>(p1,HttpStatus.OK);
	}
	
	@GetMapping("/viewAllChildByEmail/{email}")
	public ResponseEntity<List<Child>> viewAllChildByEmail(@PathVariable String email){
		logger.info("getting child details");
		List<Child> p1 = childService.viewAllChildByEmail(email);
		logger.info("child details received");
		return new ResponseEntity<>(p1,HttpStatus.OK);
	}
	@GetMapping("/pagingAndSortingChild/{pageNumber}/{pageSize}/{sortProperty}")
    public Page<Child> childPagination(@PathVariable Integer pageNumber,
                                             @PathVariable Integer pageSize,
                                             @PathVariable String sortProperty) {
        return childService.getChildPagination(pageNumber, pageSize, sortProperty);
    }
}
