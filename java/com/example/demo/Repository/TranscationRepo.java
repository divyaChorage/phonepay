package com.example.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.modules.Transcation;
import com.example.demo.projection.UserDetailsProjection;
import com.example.demo.projection.*;

public interface TranscationRepo  extends JpaRepository<Transcation, Integer>{

//	
//    @Query(value = "SELECT t.receiver_name AS receiverName, t.amount, t.sender_previous_bal AS senderPreviousBal, " +
//            "t.sender_now_bal AS senderNowBal, t.cr_db AS crDb, t.date " +
//            "FROM phone_payuser p " +
//            "JOIN linked_account l ON p.id = l.phonepayid " +
//            "JOIN bankuser b ON b.id = l.bankid " +
//            "JOIN transcation t ON t.sender_account_no = b.accountno " +
//            "WHERE p.id = :phonepayid", nativeQuery = true)
//List<History> findUserDetailsByPhonepayid(@Param("phonepayid") int phonepayid);
//	 
	

	@Query(value = "SELECT t.receiver_name AS receiverName, t.amount, t.sender_previous_bal AS senderPreviousBal, " +
	        "t.sender_now_bal AS senderNowBal, t.cr_db AS crDb, t.date " +
	        "FROM transcation t " +
	        "JOIN bankuser b ON t.sender_account_no = b.accountno OR t.receiver_account_no = b.accountno " +
	        "JOIN linked_account l ON b.id = l.bankid " +
	        "JOIN phone_payuser p ON l.phonepayid = p.id " +
	        "WHERE p.id = :phonepayid", nativeQuery = true)
	List<History> findUserDetailsByPhonepayid(@Param("phonepayid") int phonepayid);
	 
}
