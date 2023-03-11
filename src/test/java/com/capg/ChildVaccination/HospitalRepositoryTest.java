package com.capg.ChildVaccination;


import static org.junit.jupiter.api.Assertions.assertEquals;


import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.capg.ChildVaccination.Entity.Hospital;
import com.capg.ChildVaccination.Repository.HospitalRepository;


@SpringBootTest
class HospitalRepositoryTest {
	
	
		
		@Autowired
		HospitalRepository hospitalRepo;

		@BeforeAll
		static void setUpBeforeClass() throws Exception {
		}

		@AfterAll
		static void tearDownAfterClass() throws Exception {
		}

		@BeforeEach
		void setUp() throws Exception {
		}

		@AfterEach
		void tearDown() throws Exception {
		}
		
		@Test
		void findHospitalByIdTest() {
			Optional<Hospital> hospital=hospitalRepo.findById(1);
			Hospital hospital1=hospital.get();
			assertEquals("Vydehi Hospital",hospital1.getHospitalName());
		}
		
		
		
	@Test
	void getAllHospitalTest() {
			List<Hospital> hospital = (List<Hospital>) hospitalRepo.findAll();
			assertEquals(3,hospital.size());
		Hospital hospital1=hospital.get(1);
		assertEquals("Brookfield Hospital",hospital1.getHospitalName());
		}
}
