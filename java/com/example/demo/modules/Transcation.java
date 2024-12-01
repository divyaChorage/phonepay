package com.example.demo.modules;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Transcation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int transationID;
	
     int senderAccountNo;
     int  receiverAccountNo;
     String  receiverName;
     int amount;
     int senderPreviousBal;
     int senderNowBal;
     int receiverPreviourBal;   
     int receiverNowBal;
     String crDb;  
     //cr =credit db  =debit
     
     Date date;

	public Transcation(int transationID, int senderAccountNo, int receiverAccountNo, String receiverName, int amount,
			int senderPreviousBal, int senderNowBal, int receiverPreviourBal, int receiverNowBal, String crDb,
			Date date) {
		super();
		this.transationID = transationID;
		this.senderAccountNo = senderAccountNo;
		this.receiverAccountNo = receiverAccountNo;
		this.receiverName = receiverName;
		this.amount = amount;
		this.senderPreviousBal = senderPreviousBal;
		this.senderNowBal = senderNowBal;
		this.receiverPreviourBal = receiverPreviourBal;
		this.receiverNowBal = receiverNowBal;
		this.crDb = crDb;
		this.date = date;
	}

	public Transcation() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getTransationID() {
		return transationID;
	}

	public void setTransationID(int transationID) {
		this.transationID = transationID;
	}

	public int getSenderAccountNo() {
		return senderAccountNo;
	}

	public void setSenderAccountNo(int senderAccountNo) {
		this.senderAccountNo = senderAccountNo;
	}

	public int getReceiverAccountNo() {
		return receiverAccountNo;
	}

	public void setReceiverAccountNo(int receiverAccountNo) {
		this.receiverAccountNo = receiverAccountNo;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getSenderPreviousBal() {
		return senderPreviousBal;
	}

	public void setSenderPreviousBal(int senderPreviousBal) {
		this.senderPreviousBal = senderPreviousBal;
	}

	public int getSenderNowBal() {
		return senderNowBal;
	}

	public void setSenderNowBal(int senderNowBal) {
		this.senderNowBal = senderNowBal;
	}

	public int getReceiverPreviourBal() {
		return receiverPreviourBal;
	}

	public void setReceiverPreviourBal(int receiverPreviourBal) {
		this.receiverPreviourBal = receiverPreviourBal;
	}

	public int getReceiverNowBal() {
		return receiverNowBal;
	}

	public void setReceiverNowBal(int receiverNowBal) {
		this.receiverNowBal = receiverNowBal;
	}

	public String getCrDb() {
		return crDb;
	}

	public void setCrDb(String crDb) {
		this.crDb = crDb;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Transcation [transationID=" + transationID + ", senderAccountNo=" + senderAccountNo
				+ ", receiverAccountNo=" + receiverAccountNo + ", receiverName=" + receiverName + ", amount=" + amount
				+ ", senderPreviousBal=" + senderPreviousBal + ", senderNowBal=" + senderNowBal
				+ ", receiverPreviourBal=" + receiverPreviourBal + ", receiverNowBal=" + receiverNowBal + ", crDb="
				+ crDb + ", date=" + date + "]";
	}

}
