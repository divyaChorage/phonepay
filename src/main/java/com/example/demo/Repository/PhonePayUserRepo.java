package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.modules.PhonePayuser;

public interface PhonePayUserRepo extends JpaRepository<PhonePayuser, Integer> {
	int countByMobileno(long mobileno);
	PhonePayuser findByMobileno(long mobileno);
 
}
