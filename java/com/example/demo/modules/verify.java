package com.example.demo.modules;

import org.springframework.stereotype.Service;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class verify {
	
	int pin;
	String upi;
	int otp;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public int getOtp() {
		return otp;
	}
	public void setOtp(int otp) {
		this.otp = otp;
	}
	public verify(int pin, String upi, int otp, int id) {
		super();
		this.pin = pin;
		this.upi = upi;
		this.otp = otp;
		this.id = id;
	}
	public verify() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "verify [pin=" + pin + ", upi=" + upi + ", otp=" + otp + ", id=" + id + "]";
	}
	
	
}
