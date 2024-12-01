package com.example.demo.modules;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import jakarta.persistence.Entity;

@Entity
@Getter@Setter@ToString@NoArgsConstructor@AllArgsConstructor
public class PhonePayuser {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	String name;
	Long mobileno;
	int pin;
	int Otp;
	
	@Override
	public String toString() {
		return "PhonePayuser [id=" + id + ", name=" + name + ", mobileno=" + mobileno + ", pin=" + pin + ", Otp=" + Otp
				+ "]";
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
	

}
