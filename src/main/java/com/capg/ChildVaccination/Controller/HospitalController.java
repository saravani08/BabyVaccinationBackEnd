package com.capg.ChildVaccination.Controller;

import java.util.List;

import org.springframework.data.domain.Page;
import java.util.Set;

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

import com.capg.ChildVaccination.Dto.HospitalDto;
import com.capg.ChildVaccination.Entity.Hospital;
import com.capg.ChildVaccination.Service.HospitalService;



@RestController
@RequestMapping("/Hospital")
@CrossOrigin("http://localhost:3000")
public class HospitalController {
	@Autowired
	HospitalService ihospitalService;
	private static Logger logger=LogManager.getLogger();
	
	@PostMapping("/insertHospital") //Here we have to post the data in post mapping
	public ResponseEntity<Hospital>addHospital(@RequestBody HospitalDto hospital){
		logger.info("sending request to add Hospital");
		Hospital h1=ihospitalService.addHospital(hospital);
		logger.info("request added");
		return new ResponseEntity<>(h1,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/hospital/delete/{hospitalId}")// Here we have to delete the hosptial by using hospitalId
	ResponseEntity<Hospital> removeHospitalById(@PathVariable("hospitalId") int hospitalId)  {
		logger.info("Sending request to delete a Hospital");
		Hospital deletedHospital=ihospitalService.removeHospitalById(hospitalId);
		logger.info("Removed an Hospital from the Database");
		return new ResponseEntity<>(deletedHospital, HttpStatus.OK);
	}
	
	@GetMapping("/hospital/getAllHospital")//Here  using getmapping by using that we can retrive getallhospital
	ResponseEntity<List<Hospital>> getAllHospital(){
		logger.info("Sending request to get all hospitals");
		List<Hospital> allHospital=ihospitalService.getAllHospital();
		logger.info("hospitals recieved");
		return new ResponseEntity<>(allHospital, HttpStatus.OK);
	}
	@GetMapping("/hospital/view/{hospitalId}")//here using getmapping to view all hospitals by using hospitalId
	ResponseEntity<Hospital> viewHospital(@PathVariable("hospitalId") int hospitalId)  {
		logger.info("Sending request to view all hospitals");
		Hospital hospital = ihospitalService.viewHospital(hospitalId);
		logger.info("view all hospitals");
		return new ResponseEntity<>(hospital, HttpStatus.OK);
		
	}
	@PutMapping("/updateHospital/{id}")//here using putmapping for upadtehosptal by using hospitalid
	public ResponseEntity<Hospital>updateHospitalById(@PathVariable("id") int id , @RequestBody HospitalDto hospital)  {
		logger.info("sending request to update Hospital");
		Hospital h1=ihospitalService.updateHospital(id,hospital);
		logger.info("request updated");
		return new ResponseEntity<>(h1,HttpStatus.CREATED);
	}
	
	@GetMapping("/getAllHospitalsNames")//here getmapping for getallhospitalsnames
	public Set<String> getAllHospitals(){
		return ihospitalService.getAllHospitalNames();
	}
	
	@GetMapping("/pagingAndSortingHospital/{pageNumber}/{pageSize}/{sortProperty}")//here pagingandsortinghospital is usedfor find the pagenumber,pagesize//
    public Page<Hospital> hospitalPagination(@PathVariable Integer pageNumber,
                                             @PathVariable Integer pageSize,
                                             @PathVariable String sortProperty) {
        return ihospitalService.getHospitalPagination(pageNumber, pageSize, sortProperty);
    }
	
	
		
}

/*
 * Author:Mounika Alla
 * Creation Date:18-02-2023
 * Module:Hospital
 * Description:This controller methods handles HTTP requests from client(UI)
 */




	