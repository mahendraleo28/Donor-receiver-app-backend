package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Donation;

public interface DonationRepository extends JpaRepository<Donation, Long> {
	
}
