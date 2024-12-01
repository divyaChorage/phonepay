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
import com.example.demo.Repository.LinkedAccountRepo;
import com.example.demo.Repository.PhonePayUserRepo;
import com.example.demo.modules.Bankuser;
import com.example.demo.modules.LinkedAccount;
import com.example.demo.modules.PhonePayuser;
import com.example.demo.modules.verify;
import com.example.demo.projection.LinkedAcc;

@RestController
@CrossOrigin
public class UserController {
	
	@Autowired
	PhonePayUserRepo phonepayUserRepo;
	@Autowired
	BankUserRepo  bankUserRepo;
	@Autowired
	LinkedAccountRepo accountRepo; 
	
	@RequestMapping("getnonLinkedAcc/{id}")
	public List<LinkedAcc> getAccount(@PathVariable int id){
		PhonePayuser user= phonepayUserRepo.findById(id).get();
		List<LinkedAcc> acc=accountRepo.findNonLinkedAccounts(user.getMobileno());
		return acc;
	}
	
	@RequestMapping("getLinkedAcc/{id}")
	public List<LinkedAcc> getAccounts(@PathVariable int id){
		List<LinkedAcc> acc=accountRepo.findLinkedAccounts(id);
		return acc;
	}
	
	@RequestMapping("getPin/{id}/{bid}")
	public int getAccounts(@PathVariable int id,@PathVariable int bid){
		try {
			int count=accountRepo.countByPhonepayidAndBankid(id, bid);
			if (count==0) {
				Random obj=new Random();
				int otp=1000+obj.nextInt(9000);
				System.out.println(otp);
				
				LinkedAccount linAcc=new LinkedAccount();
				linAcc.setBankid(bid);
				linAcc.setPhonepayid(id);
				linAcc.setIsdeleted(0);
				linAcc.setOtp(otp);
				accountRepo.save(linAcc);
				return linAcc.getId();
			}else if(count==1){
				LinkedAccount acc= accountRepo.findByPhonepayidAndBankid(id,bid);
				Random obj=new Random();
				int otp=1000+obj.nextInt(9000);
				System.out.println(otp);
				
				acc.setOtp(otp);
				accountRepo.save(acc);
				return acc.getId();
			}else {
				return -1;
			}	
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return -1;
		}
	}

	@RequestMapping("verfylinking")
	public int verifylinking(@RequestBody verify obj) {
		int otp=obj.getOtp();
		int id=obj.getId();		
		LinkedAccount linkacc= accountRepo.findById(id).get();		
		int cnt=accountRepo.countByUpi(obj.getUpi());	
		if (linkacc.getOtp()==otp && cnt==0) {
			linkacc.setPin(obj.getPin());
			linkacc.setUpi(obj.getUpi());
			accountRepo.save(linkacc);
			return 1;
		} else {
			return -1;
		}	
	}
	@RequestMapping("deletelinking")
	public  int deletelinking(@PathVariable int id)
	{
		return 0;
	}
	
	
}

 