import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AppComponent } from '../app.component';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  constructor(public http:HttpClient, public app:AppComponent){}

showOtp:number=0;
number:number=0;
email:string=''
register()
{
let url=this.app.baseUrl+'register'+this.number+'/'+this.email;
this.http.get(url).subscribe((data:any)=>
{
  if(data==null|| data==-1)
  {
    window.alert('something is wrong');
  }
  else{
    this.showOtp=1;
  }
});
}
  otp:number=0;
  verifyOtp()
  {
    let url=this.app.baseUrl+'verify'
    let obj=[this.number,this.otp];
    console.log(this.otp)
    this.http.post(url,obj).subscribe((data:any)=>
    {
      if(data==null|| data==-1)
      {
        window.alert('wrong otp')
      }
      else{
        this.showOtp=2;
      }
    });
  }

    pin:number=0;
    pin2=this.number=0;
    set()
    {
      if(this.pin==this.pin2)
      {
        // console.log("otp is same")
       let url=this.app.baseUrl+'set'+this.number;
       console.log(url);
       this.http.post(url,this.pin).subscribe((data:any)=>
       {
         if(data==null ||data !=1)
          {
            window.alert('something is wrong')
          }
          else{
            this.app.WhatToShow=3;
          }
       });
  
      }
  

  else{
window.alert('pins are not maching')
  }
}
  }
