package com.example.demo.modules;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class LinkedAccount {

	@Override
	public String toString() {
		return "LinkedAccount [id=" + id + ", phonepayid=" + phonepayid + ", bankid=" + bankid + ", pin=" + pin
				+ ", otp=" + otp + ", upi=" + upi + ", isdeleted=" + isdeleted + "]";
	}
	public LinkedAccount() {
		super();
		// TODO Auto-generated constructor stub
	}
	public LinkedAccount(int id, int phonepayid, int bankid, int pin, int otp, String upi, int isdeleted) {
		super();
		this.id = id;
		this.phonepayid = phonepayid;
		this.bankid = bankid;
		this.pin = pin;
		this.otp = otp;
		this.upi = upi;
		this.isdeleted = isdeleted;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	int phonepayid;
	int bankid;
	int pin;
	int otp;
	public int getOtp() {
		return otp;
	}
	public void setOtp(int otp) {
		this.otp = otp;
	}
	String upi;
	int isdeleted;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPhonepayid() {
		return phonepayid;
	}
	public void setPhonepayid(int phonepayid) {
		this.phonepayid = phonepayid;
	}
	public int getBankid() {
		return bankid;
	}
	public void setBankid(int bankid) {
		this.bankid = bankid;
	}
	public int getPin() {
		return pin;
	}
	public void setPin(int pin) {
		this.pin = pin;
	}
	public String getUpi() {
		return upi;
	}
	public void setUpi(String upi) {
		this.upi = upi;
	}
	public int getIsdeleted() {
		return isdeleted;
	}
	public void setIsdeleted(int isdeleted) {
		this.isdeleted = isdeleted;
	}
	
	
	
}
