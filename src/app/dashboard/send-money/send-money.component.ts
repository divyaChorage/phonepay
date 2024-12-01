import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { AppComponent } from 'src/app/app.component';

@Component({
  selector: 'app-send-money',
  templateUrl: './send-money.component.html',
  styleUrls: ['./send-money.component.css']
})
export class SendMoneyComponent implements OnInit {

  openhistory:number=0;
  senderupi:string='';
  senderAccount:number=0;
  senderMobile:number=0;
  receiverMobileNo: number = 0;
  receiveraccountNumber: number = 0;
  amountToPay: number = 0;
  receiverName: string = '';

  constructor(private http: HttpClient, private app: AppComponent) { }
  ngOnInit(): void {
   this.loadUserDetails();

  }






  loadUserDetails() {
    // Replace 'getUserDetails' with the actual endpoint to fetch user details
    
    console.log(this.app.id)
    let url=this.app.baseUrl+"getUserDetails/"+this.app.id;

    this.http.get(url).subscribe((data: any) => {
      console.log(data)
      this.senderupi = data.upi;
      this.senderAccount = data.accountno;
      this.senderMobile=data.mobileno

    });
  }


  validaterecipientDetails() {
    const mobileNoStr = this.receiverMobileNo.toString();
    console.log("string", mobileNoStr);

    if (mobileNoStr.length === 10) {
        const mobileno = Number(mobileNoStr);
        console.log(mobileno);

        let url = this.app.baseUrl + "validateRecipient/" + mobileno + '/' + this.receiverName;
     
        this.http.get(url).subscribe(
            (data: any) => {
        
                if (data && data.accountno && data.name) {
                    console.log(data);
                    this.receiveraccountNumber = data.accountno;
                  

                } 
            }
        );

    } 
}








sendMoney() {
  const transactionData = {
    senderAccountNo: this.senderAccount,
    receiverAccountNo: this.receiveraccountNumber,
    amount: this.amountToPay
  };

  let url = this.app.baseUrl + "sendMoney";
console.log(transactionData)
  this.http.post(url, transactionData, { responseType: 'text' }).subscribe(
    (response: string) => {
      console.log(response);
      alert('Money sent successfully!');
    },
    (error) => {
      console.error(error);
      alert('Failed to send money. ' + error.error);
    }
  );
}







}
