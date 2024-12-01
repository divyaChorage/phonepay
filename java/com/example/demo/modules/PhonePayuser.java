package com.example.demo.modules;

import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;

@Entity
public class PhonePayuser {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	String name;
	Long mobileno;
	int pin;
	int Otp;
	
	String email;

	public PhonePayuser() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PhonePayuser(int id, String name, Long mobileno, int pin, int otp, String email) {
		super();
		this.id = id;
		this.name = name;
		this.mobileno = mobileno;
		this.pin = pin;
		Otp = otp;
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getMobileno() {
		return mobileno;
	}

	public void setMobileno(Long mobileno) {
		this.mobileno = mobileno;
	}

	public int getPin() {
		return pin;
	}

	public void setPin(int pin) {
		this.pin = pin;
	}

	public int getOtp() {
		return Otp;
	}

	public void setOtp(int otp) {
		Otp = otp;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "PhonePayuser [id=" + id + ", name=" + name + ", mobileno=" + mobileno + ", pin=" + pin + ", Otp=" + Otp
				+ ", email=" + email + "]";
	}
	
	

}
