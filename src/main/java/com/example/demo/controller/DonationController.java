package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.Repository.DonationRepository;
import com.example.demo.model.Donation;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/donations")
public class DonationController {

	private final DonationRepository donationRepository;

	@Autowired
	public DonationController(DonationRepository donationRepository) {
		this.donationRepository = donationRepository;
	}

	// This method is used to save the donation item
	@PostMapping("/submit")
	public ResponseEntity<String> submitDonation(@RequestBody Donation donation) {
		String phone = donation.getPhonenumber();
		String postal = donation.getPostalCode();
		if ((phone.trim().length() < 10 || phone.trim().length() > 10 || !phone.matches("^[6-9]\\d{9}$"))) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error in Mobile");
		} else if ((postal.trim().length() < 6 || postal.trim().length() > 6)) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error in Postalcode");
		} else {
			Donation savedDonation = donationRepository.save(donation);
			return ResponseEntity.ok("Donation submitted successfully with ID: " + savedDonation.getId());
		}
	}

	// This method is used to retrieve all the present donating items
	@GetMapping("/all")
	public ResponseEntity<List<Donation>> getAllDonations() {
		List<Donation> donations = donationRepository.findAll();
		return ResponseEntity.ok(donations);
	}

	// for deleting the donated items when it is completed
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteDonationById(@PathVariable Long id) {
		try {
			Optional<Donation> optionalDonation = donationRepository.findById(id);
			if (optionalDonation.isPresent()) {
				donationRepository.deleteById(id);
				return ResponseEntity.ok("Donation with ID: " + id + " deleted successfully");
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			return ResponseEntity.status(500)
					.body("Failed to delete donation with ID: " + id + ", Error: " + e.getMessage());
		}
	}

	// This method is used to update the fields based on id
	@PutMapping("/{id}")
	public ResponseEntity<String> updateDonation(@PathVariable Long id, @RequestBody Donation updatedDonation) {
		try {
			Optional<Donation> optionalDonation = donationRepository.findById(id);
			if (optionalDonation.isPresent()) {
				Donation existingDonation = optionalDonation.get();

				// Update all fields with the values from the updated donation
				existingDonation.setPhonenumber(updatedDonation.getPhonenumber());
				existingDonation.setPostalCode(updatedDonation.getPostalCode());
				existingDonation.setAddressLine1(updatedDonation.getAddressLine1());
				existingDonation.setAddressLine2(updatedDonation.getAddressLine2());
				existingDonation.setComment(updatedDonation.getComment());
				existingDonation.setDonationType(updatedDonation.getDonationType());
				existingDonation.setState(updatedDonation.getState());
				existingDonation.setCountry(updatedDonation.getCountry());
				
				Donation updatedEntity = donationRepository.save(existingDonation);
				System.out.println(updatedEntity);
				return ResponseEntity.ok("Donation with ID: " + id + " updated successfully");
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			return ResponseEntity.status(500)
					.body("Failed to update donation with ID: " + id + ", Error: " + e.getMessage());
		}
	}

}
