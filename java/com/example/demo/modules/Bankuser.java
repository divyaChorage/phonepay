package com.example.demo.modules;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class Bankuser {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	String name;
	Long mobileno;
	int accountno;
	int ifsccode;
	int balance;
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
	public int getAccountno() {
		return accountno;
	}
	public void setAccountno(int accountno) {
		this.accountno = accountno;
	}
	public int getIfsccode() {
		return ifsccode;
	}
	public void setIfsccode(int ifsccode) {
		this.ifsccode = ifsccode;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public Bankuser(int id, String name, Long mobileno, int accountno, int ifsccode, int balance) {
		super();
		this.id = id;
		this.name = name;
		this.mobileno = mobileno;
		this.accountno = accountno;
		this.ifsccode = ifsccode;
		this.balance = balance;
	}
	public Bankuser() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Bankuser [id=" + id + ", name=" + name + ", mobileno=" + mobileno + ", accountno=" + accountno
				+ ", ifsccode=" + ifsccode + ", balance=" + balance + "]";
	}

	

}
