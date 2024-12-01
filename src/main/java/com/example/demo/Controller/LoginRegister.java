package com.example.demo.Controller;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Repository.BankUserRepo;
import com.example.demo.Repository.PhonePayUserRepo;
import com.example.demo.modules.Bankuser;
import com.example.demo.modules.PhonePayuser;
@RestController
@CrossOrigin
public class LoginRegister {
	
	
	@Autowired
 PhonePayUserRepo phonepayUserRepo;
	@Autowired
	BankUserRepo  bankUserRepo ;
	
	@RequestMapping("set{mobile}")
	public int setPin(@PathVariable long mobile, @RequestBody int pin)
	{
//		System.out.println("working");
		int count=phonepayUserRepo.countByMobileno(mobile);
//		System.out.println(count);
		if(count!=1)
			return -1;
		PhonePayuser user=phonepayUserRepo.findByMobileno(mobile);
		user.setPin(pin);
		System.out.println(user);
		phonepayUserRepo.save(user);
		return 1;
		
	}
	
@RequestMapping("verify")
public  int verifyOtp(@RequestBody long[] a)
{
	long mobileno=a[0];
	int otp=(int)a[1];
	int count=phonepayUserRepo.countByMobileno(mobileno);
	if(count!=1)

		return -1;
		
		PhonePayuser user =phonepayUserRepo.findByMobileno(mobileno);
		
		if(user.getOtp()==otp)
			return 1;
		return-1;
		
		
	
}


@RequestMapping("login")
public int login(@RequestBody long[] a)
{
	long mobile=a[0];
	int pin=(int)a[1];
	
	int count=phonepayUserRepo.countByMobileno(mobile);
	if(count!=1)
	{
		return -1;
	}
	PhonePayuser user =phonepayUserRepo.findByMobileno(mobile);

	if(user.getPin()==pin)
	{
		return user.getId();
	}
	return -1;
}











@RequestMapping("register{number}")
public int register( @PathVariable long number)
{
	PhonePayuser user;
	
	int count=phonepayUserRepo.countByMobileno(number);
	
	if(count==1)
	{
		
		user=phonepayUserRepo.findByMobileno(number);
		Random obj=new Random();
		int otp=1000+obj.nextInt(9000);
		user.setOtp(otp);
		phonepayUserRepo.save(user);
	}
	else if(count ==0)
	{
		user=new PhonePayuser();
	user.setMobileno(number);
	List<Bankuser>list =bankUserRepo.findByMobileno(number);
	if(list==null|| list.size()==0)
		
	{
		user.setName("user");
		
	}
	else
	{
		Bankuser bankuser=list.get(0);
		user.setName(bankuser.getName());
		
		
	}
	Random obj=new Random();
	int otp=1000+obj.nextInt(9000);
	user.setOtp(otp);
	phonepayUserRepo.save(user);

	
	
	}
	else
		return -1;
	System.out.println(user.getOtp());
	return 1;

		
		
	}
}



