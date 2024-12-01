package com.example.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.modules.Bankuser;

public interface BankUserRepo extends JpaRepository<Bankuser, Integer> {
	
	
	int countByMobileno(long mobileno);
	List<Bankuser> findByMobileno(long mobileno);
	List<Bankuser> findByAccountno(int senderAccountNo);


}
