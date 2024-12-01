package com.example.demo.Controller;




import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Repository.BankUserRepo;
import com.example.demo.Repository.PhonePayUserRepo;
import com.example.demo.modules.Bankuser;
import com.example.demo.modules.PhonePayuser;
import com.example.demo.projection.Profile;
import com.example.demo.projection.UserDetailsProjection;
@RestController
@CrossOrigin
public class LoginRegister {
	
	
	@Autowired
 PhonePayUserRepo phonepayUserRepo;
	@Autowired
	BankUserRepo  bankUserRepo ;
	
    @Autowired
    JavaMailSender mailSender;
	
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
	System.out.println(mobile+" "+pin);
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











@RequestMapping("register{number}/{email}")
public int register( @PathVariable long number,@PathVariable String email)
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
	user.setEmail(email);
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
	sendPasswordToEmail(user.getEmail(), user.getOtp());
	return 1;

		
		
	}




private void sendPasswordToEmail(String email, int password) {
    SimpleMailMessage message = new SimpleMailMessage();
    message.setTo(email);
    message.setSubject("Your Registration Password");
   String otpis=String.valueOf(password);
    message.setText("Your OTP for registering phone pay  " + otpis);
    mailSender.send(message);
}


@RequestMapping("getProfile/{id}")
public Profile getUserDetails(@PathVariable int id) {
    System.out.println("id for getting profile --" + id);
    Profile user = phonepayUserRepo.findUserDetailsByPhonepayid(id);
    if (user != null) {
        System.out.println(user + " getting data for profile");
    } else {
        System.out.println("No profile found for id: " + id);
    }
    return user;
}



}



