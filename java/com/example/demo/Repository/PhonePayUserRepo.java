package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.modules.PhonePayuser;
import com.example.demo.projection.Profile;
import com.example.demo.projection.UserDetailsProjection;

public interface PhonePayUserRepo extends JpaRepository<PhonePayuser, Integer> {
	int countByMobileno(long mobileno);
	PhonePayuser findByMobileno(long mobileno);
	
	@Query(value = "SELECT p.mobileno, b.name, b.ifsccode, b.accountno, l.upi " +
            "FROM phone_payuser p " +
            "JOIN linked_account l ON l.phonepayid = p.id " +
            "JOIN bankuser b ON b.id = l.bankid " +
            "WHERE p.id = :phid", nativeQuery = true)
Profile findUserDetailsByPhonepayid(@Param("phid") int phonepayid);




	
 
}
