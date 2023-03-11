package com.capg.ChildVaccination.Controller;
import java.util.List;

import javax.validation.Valid;

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

import com.capg.ChildVaccination.Dto.BookingDto;
import com.capg.ChildVaccination.Entity.Booking;
import com.capg.ChildVaccination.Exceptions.BookingNotFoundException;
import com.capg.ChildVaccination.Exceptions.HospitalNotFoundException;
import com.capg.ChildVaccination.Exceptions.ParentNotFoundException;
import com.capg.ChildVaccination.Service.BookingService;

import org.springframework.data.domain.Page;

@RestController
@RequestMapping("/vaccineapp")
@CrossOrigin("http://localhost:3000")
public class BookingController {

	@Autowired
	private BookingService bookingService;
	
	@PostMapping("/addbooking")
	public ResponseEntity<Booking> addBooking(@Valid @RequestBody BookingDto booking)
	{
		Booking data = bookingService.addBooking(booking);
		return new ResponseEntity<Booking>(data, HttpStatus.OK);
	}
	
	@PutMapping("/updatebooking/{id}")
	public ResponseEntity<Booking> updateBooking(@Valid @PathVariable int id,@RequestBody BookingDto booking)
			throws BookingNotFoundException {
		Booking data = bookingService.updateBooking(id,booking);
		return new ResponseEntity<Booking>(data, HttpStatus.OK);
	}
	
	@GetMapping("/getbookingbyid/{bookingId}")
    public ResponseEntity<Booking> getBookingById(@PathVariable("bookingId") int bookingId) throws BookingNotFoundException {
        Booking booking = bookingService.getBookingById(bookingId);
        return new ResponseEntity<Booking>(booking, HttpStatus.OK);
    }

    @GetMapping("/getallbookings")
    public ResponseEntity<List<Booking>> getAllBookings() throws BookingNotFoundException{
        List<Booking> bookings = bookingService.getAllBookings();
        return new ResponseEntity<List<Booking>>(bookings, HttpStatus.OK);
    }

    @DeleteMapping("/deletebooking/{bookingId}")
    public ResponseEntity<Booking> deleteBooking(@PathVariable("bookingId") int bookingId) throws BookingNotFoundException{
        Booking booking = bookingService.deleteBooking(bookingId);
        return new ResponseEntity<Booking>(booking, HttpStatus.OK);
    }
    
    
    @GetMapping("/getbookingsbyhospital/{hospitalId}")
    public ResponseEntity<List<Booking>> getAllBookingsByHospitalId(@PathVariable("hospitalId") int hospitalId) throws HospitalNotFoundException, BookingNotFoundException{
        List<Booking> bookings = bookingService.getAllBookingsByHospitalId(hospitalId);
        return new ResponseEntity<List<Booking>>(bookings, HttpStatus.OK);
    }
    
    @GetMapping("/getbookingsbyuser/{email}")
    public ResponseEntity<List<Booking>> getAllBookingsByUserId(@PathVariable("email") String email) throws ParentNotFoundException{
        List<Booking> bookings = bookingService.getAllBookingsByUserId(email);
        return new ResponseEntity<List<Booking>>(bookings, HttpStatus.OK);
    }

    @GetMapping("/getbillbyid")
    public ResponseEntity<Float> getBill(int id) throws BookingNotFoundException{
        float bill = bookingService.getBill(id);
        return new ResponseEntity<Float>(bill, HttpStatus.OK);
    }
	
    @GetMapping("/pagingAndSortingBooking/{pageNumber}/{pageSize}/{sortProperty}")
    public Page<Booking> bookingPagination(@PathVariable Integer pageNumber,
                                             @PathVariable Integer pageSize,
                                             @PathVariable String sortProperty) {
        return bookingService.getBookingPagination(pageNumber, pageSize, sortProperty);
    }

}