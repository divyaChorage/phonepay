import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { AppComponent } from 'src/app/app.component';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent {

  constructor(private http: HttpClient, private app: AppComponent) { }
  ngOnInit(): void {
   this.loadUserDetails();

  }

  mobileNumber: string = '';
username: string = '';
bankName: string = '';
accountNumber: string = '';
upiId: string = '';
ifscCode: string = '';



  loadUserDetails() {
    // Replace 'getUserDetails' with the actual endpoint to fetch user details
    
    console.log(this.app.id)
    let url=this.app.baseUrl+"getProfile/"+this.app.id;

    this.http.get(url).subscribe((data: any) => {
      console.log(data);
      this.mobileNumber = data.mobileno.toString();
      this.username = data.name;
      this.bankName = data.name;
      this.accountNumber = data.accountno.toString();
      this.ifscCode = data.ifsccode.toString();
      this.upiId = data.upi;
    });
    
  }


}
