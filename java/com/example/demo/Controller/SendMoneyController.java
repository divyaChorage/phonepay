package com.example.demo.Controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Repository.BankUserRepo;
import com.example.demo.Repository.LinkedAccountRepo;
import com.example.demo.Repository.PhonePayUserRepo;
import com.example.demo.Repository.TranscationRepo;
import com.example.demo.modules.Bankuser;
import com.example.demo.modules.PhonePayuser;
import com.example.demo.modules.Transcation;
import com.example.demo.projection.History;
import com.example.demo.projection.LinkedAcc;
import com.example.demo.projection.UserDetailsProjection;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class SendMoneyController
{

	
	@Autowired
	PhonePayUserRepo phonepayUserRepo;
	@Autowired
	BankUserRepo  bankUserRepo;
	@Autowired
	LinkedAccountRepo linkedAccountRepo;
	
	@Autowired
	TranscationRepo transcationRepo;
	
	
	@RequestMapping("getUserDetails/{id}")
	public UserDetailsProjection getUserDetails(@PathVariable int id) 
	{
		System.out.println(id);
		
		UserDetailsProjection user= linkedAccountRepo.findUserDetailsByPhonepayid(id);
		System.out.println(user+"getting data");
		return user;
    }
	
	
	@RequestMapping("validateRecipient/{mobileno}/{receiverName}")
	public Bankuser getUserDetails(@PathVariable Long mobileno ,@PathVariable String receiverName) 
	{
	    System.out.println(mobileno + "___bankuser");

	


	    List<Bankuser> bu = bankUserRepo.findByMobileno(mobileno);

	    for (Bankuser user : bu) {
	        System.out.println(user.getMobileno() + "___bankuser");
	        if (user.getMobileno().equals(mobileno) && user.getName().equals(receiverName)) {
	            System.out.println("User found: " + user);
	            return user;
	        }
	    }

	    return null; // 404 Not Found
	}


	@PostMapping("sendMoney")
	public ResponseEntity<String> sendMoney(@RequestBody Transcation transferRequest) {
	    int senderAccountNo = transferRequest.getSenderAccountNo();
	    int receiverAccountNo = transferRequest.getReceiverAccountNo();

	    int amount = transferRequest.getAmount();

	    // Fetch sender and receiver details from the database
	    List<Bankuser> senderList = bankUserRepo.findByAccountno(senderAccountNo);
	    List<Bankuser> receiverList = bankUserRepo.findByAccountno(receiverAccountNo);

	    if (senderList.isEmpty() || receiverList.isEmpty()) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Sender or Receiver not found");
	    }

	    Bankuser sender = senderList.get(0);
	    Bankuser receiver = receiverList.get(0);

	    // Check if the sender has enough balance
	    if (sender.getBalance() < amount) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Insufficient balance");
	    }

	    // Record previous balances
	    transferRequest.setSenderPreviousBal(sender.getBalance());
	    transferRequest.setReceiverPreviourBal(receiver.getBalance());
	    transferRequest.setReceiverName(receiver.getName());

	    // Update balances
	    sender.setBalance(sender.getBalance() - amount);
	    receiver.setBalance(receiver.getBalance() + amount);

	    // Record new balances
	    transferRequest.setSenderNowBal(sender.getBalance());
	    transferRequest.setReceiverNowBal(receiver.getBalance());

	    // Set the credit/debit status and date
	    transferRequest.setCrDb("db"); // Debit for sender
	    // Create a copy for receiver transaction record
	    Transcation receiverTransaction = new Transcation();
	    receiverTransaction.setSenderAccountNo(receiverAccountNo);
	    receiverTransaction.setReceiverAccountNo(senderAccountNo);
	    receiverTransaction.setReceiverName(sender.getName());
	    receiverTransaction.setAmount(amount);
	    receiverTransaction.setSenderPreviousBal(receiver.getBalance() - amount);
	    receiverTransaction.setReceiverPreviourBal(receiver.getBalance() + amount);
	    receiverTransaction.setSenderNowBal(receiver.getBalance() - amount);
	    receiverTransaction.setReceiverNowBal(receiver.getBalance() + amount);
	    receiverTransaction.setCrDb("cr"); // Credit for receiver
	    receiverTransaction.setDate(new Date());

	    // Save updated details to the database
	    
	    System.out.println("sender__"+sender);
	    System.out.println("receiver"+receiver);
	    bankUserRepo.save(sender);
	    bankUserRepo.save(receiver);

	    // Save both transactions in the history
	    transcationRepo.save(transferRequest);
	    transcationRepo.save(receiverTransaction);

	    return ResponseEntity.ok("Transfer successful");
	}
  

	@RequestMapping("transactionHistory/{id}")
	public List<History> transactionHistory(@PathVariable int id) {
	    System.out.println("Fetching transaction history for user ID: " + id);

	    List<History> userTransactionHistory = transcationRepo.findUserDetailsByPhonepayid(id);

	    for (History transaction : userTransactionHistory) {
	        System.out.println(transaction.getReceiverName());
	        System.out.println(transaction.getSenderNowBal());
	        System.out.println(transaction.getSenderPreviousBal());
	    }

	    if (userTransactionHistory != null && !userTransactionHistory.isEmpty()) {
	        return userTransactionHistory;
	    } else {
	        // Handle the case where no transactions are found for the given ID
	        System.out.println("No transaction history found for user ID: " + id);
	        return new ArrayList<>(); 
	    }
	}

}

