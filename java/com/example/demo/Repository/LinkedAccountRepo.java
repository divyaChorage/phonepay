package com.example.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.modules.LinkedAccount;
import com.example.demo.projection.LinkedAcc;
import com.example.demo.projection.UserDetailsProjection;

public interface LinkedAccountRepo extends JpaRepository<LinkedAccount, Integer>{

	List<LinkedAccount> findByPhonepayid(int id);
	
//	@Query(value = "SELECT b.id,accountno,ifsccode,upi\r\n"
//			+ "FROM bankuser b\r\n"
//			+ "LEFT JOIN phonepay.linked_account l ON b.id = l.bankid\r\n"
//			+ "WHERE l.bankid IS NULL AND b.mobileno LIKE :mo;",nativeQuery = true)
//	List<LinkedAcc> findNonLinkedAccounts(@Param("mo") long mobile);
	
	@Query(value = "SELECT b.id, accountno, ifsccode, upi " +
            "FROM bankuser b " +
            "LEFT JOIN phonepay.linked_account l ON b.id = l.bankid " +
            "WHERE l.bankid IS NULL AND b.mobileno LIKE :mo", 
    nativeQuery = true)
List<LinkedAcc> findNonLinkedAccounts(@Param("mo") long mobile);

	@Query(value = "SELECT b.id,accountno,ifsccode,upi FROM phonepay.bankuser as b join linked_account as l on l.bankid=b.id where l.phonepayid=:id",nativeQuery = true)
	List<LinkedAcc> findLinkedAccounts(@Param("id") int id);

	LinkedAccount findByPhonepayidAndBankid(int id, int bid);
	
	int countByPhonepayidAndBankid(int id, int bid);

	int countByUpi(String upi);
	
	
	
	
	 @Query(value = "SELECT p.mobileno,b.accountno,l.upi FROM phone_payuser  p join linked_account l on l.phonepayid=p.id join bankuser b on b.id=l.bankid where p.id= :phonepayid", nativeQuery = true)
	    UserDetailsProjection findUserDetailsByPhonepayid(@Param("phonepayid") int phonepayid);



	    LinkedAccount findByBankid(int bankId);

	
	 

	
}
